package com.example.voizfonica.controller;

import com.example.voizfonica.data.UserCredentialRepository;
import com.example.voizfonica.model.Login;
import com.example.voizfonica.model.UserCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/login")
@SessionAttributes("login")
public class LoginController {
    private UserCredentialRepository userCredentialRepository;

    @Autowired
    private LoginController(UserCredentialRepository userCredentialRepository) {
        this.userCredentialRepository = userCredentialRepository;
    }

    @GetMapping
    public String showLogin(Model model) {
        model.addAttribute("login", new Login());
        return "loginPage";
    }

    @PostMapping
    public String processLogin(@Valid Login login, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "loginPage";
        } else {
            List<UserCredential> userCredential = userCredentialRepository.findByEmailIdAndPassword(login.getEmailId(), login.getPassword());
            if (userCredential.isEmpty()) {
                model.addAttribute("errorMessage","Wrong Email / Password");
                return "loginPage";
            } else {
                login.setUserName(userCredential.get(0).getUserName());
                login.setId(userCredential.get(0).getId());
                login.setEmailId(null);
                login.setPassword(null);
                model.addAttribute("login",login);
                return "redirect:/dashboard";
            }
        }

    }
}
