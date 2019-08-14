package com.example.voizfonica.controller;

import com.example.voizfonica.data.UserCredentialRepository;
import com.example.voizfonica.model.Login;
import com.example.voizfonica.model.UserCredential;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Optional;

@Controller
@SessionAttributes("login")
public class PrePaidtoPostPaidController {
    private UserCredentialRepository userCredentialRepository;

    @Autowired
    public PrePaidtoPostPaidController(UserCredentialRepository userCredentialRepository){
        this.userCredentialRepository = userCredentialRepository;
    }

    @GetMapping("prepaidtopostpaid")
    public String processPrepaidtoPostpaid(@ModelAttribute Login login, Model model){
        Optional<UserCredential> userCredential = userCredentialRepository.findById(login.getId());
        if(userCredential.get().getPostPaidPlan().equals("null")){
            return "redirect:changetopostpaid";
        }else{
            model.addAttribute("postpaidexist","yes");
            return "redirect:dashboard";
        }
    }
}
