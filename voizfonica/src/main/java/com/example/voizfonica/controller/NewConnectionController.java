package com.example.voizfonica.controller;

import com.example.voizfonica.data.UserCredentialRepository;
import com.example.voizfonica.model.Login;
import com.example.voizfonica.model.UserCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller

@SessionAttributes("login")
public class NewConnectionController {

    private UserCredentialRepository userCredentialRepository;

    @Autowired
    public NewConnectionController(UserCredentialRepository userCredentialRepository){
        this.userCredentialRepository = userCredentialRepository;
    }

    @ModelAttribute(name="login")
    public Login login(){return new Login();}
    @GetMapping("/newConnection")
    public String showNewConnection(@ModelAttribute Login login, Model model){
        Optional<UserCredential> userCredential = userCredentialRepository.findById(login.getId());
        model.addAttribute("prePaid",userCredential.get().getPrePaidPlan());
        model.addAttribute("postPaid",userCredential.get().getPostPaidPlan());
        model.addAttribute("dongle",userCredential.get().getDonglePlan());
        return"/newConnection";
    }

}
