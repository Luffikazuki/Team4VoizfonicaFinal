package com.example.blog.Controller;
        import com.example.blog.Model.*;

        import com.example.blog.Data.UserCredentialsRepository;
        import lombok.extern.slf4j.Slf4j;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.validation.Errors;
        import org.springframework.web.bind.annotation.*;

        import javax.validation.Valid;
        import java.util.List;

@Slf4j
@Controller
@RequestMapping("/login")
@SessionAttributes({"register","login"})
public class LoginController {
    private UserCredentialsRepository userCredentialsRepository;
    @Autowired
    public LoginController(UserCredentialsRepository userCredentialsRepository) {
        this.userCredentialsRepository = userCredentialsRepository;
    }


    @ModelAttribute(name="register")
    public Register order1()
    {
        return new Register();
    }
    @GetMapping
    public String show_register(Model model) {
        model.addAttribute("register", new Register());
        return "login";
    }


    @PostMapping
    public String processRegister(@Valid Register register, Errors errors,Model model) {
        if (errors.hasErrors()) {
            return "login";
        } else {
            List<Register1> user = userCredentialsRepository.findByNameAndPassword(register.getName(), register.getPassword());

            if (user.isEmpty()) {
                errors.rejectValue("password", "password.invalid", "Invalid password or username");
                return "login";
            } else {
             model.addAttribute("user", user);
                return "redirect:/blog";
            }


        }
    }

}