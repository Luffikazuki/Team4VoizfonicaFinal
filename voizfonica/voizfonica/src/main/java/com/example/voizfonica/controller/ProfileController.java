package com.example.voizfonica.controller;

import com.example.voizfonica.data.UserCredentialRepository;
import com.example.voizfonica.model.Login;
import com.example.voizfonica.model.UserCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


import java.util.Optional;

@Controller
@SessionAttributes("login")
@RequestMapping("profile")
public class ProfileController {

    @ModelAttribute(name ="login")
    public Login login(){
        return new Login();
    }

    private UserCredentialRepository userCredentialRepository;
    @Autowired
    private ProfileController(UserCredentialRepository userCredentialRepository) {
        this.userCredentialRepository = userCredentialRepository;
    }

    @GetMapping
    public String showProfile(Model model,
                              @ModelAttribute Login login) {
        Optional<UserCredential> userCredential = userCredentialRepository.findById(login.getId());
        UserCredential user = userCredential.get();
        model.addAttribute("user", user);
        return "/profile";
    }
}
