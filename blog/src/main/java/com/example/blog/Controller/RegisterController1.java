package com.example.blog.Controller;
import com.example.blog.Model.Register1;
import com.example.blog.Data.UserCredentialsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/register")
public class RegisterController1 {
    private UserCredentialsRepository userCredentialsRepository;
    @Autowired
    public RegisterController1(UserCredentialsRepository userCredentialsRepository){

        this.userCredentialsRepository=userCredentialsRepository;
    }


    @ModelAttribute(name="reg")
    public Register1 reg(){
        return new Register1();
    }
    @ModelAttribute
    @GetMapping
    public String show_register(Model model){
        model.addAttribute("register1",new Register1());
        return "register";
    }

    @PostMapping
    public String processRegister(@Valid Register1 register1, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "register";
        }
        else{
            userCredentialsRepository.save(register1);
            model.addAttribute("showDetails",register1);
            model.addAttribute("registrationSuccess", "yes");
            return "register";
        }

    }
}
