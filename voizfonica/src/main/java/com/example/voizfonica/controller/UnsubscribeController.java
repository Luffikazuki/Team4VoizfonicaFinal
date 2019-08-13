package com.example.voizfonica.controller;

import com.example.voizfonica.data.PlanDetailRepository;
import com.example.voizfonica.model.Login;
import com.example.voizfonica.model.PlanDetail;
import com.example.voizfonica.model.UserCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("login")
public class UnsubscribeController {
    private PlanDetailRepository planDetailRepository;

    @Autowired
    public UnsubscribeController(PlanDetailRepository planDetailRepository){
        this.planDetailRepository = planDetailRepository;
    }

    @GetMapping("/unsubscribe")
    public String showUnsubscirbe(Model model, @ModelAttribute Login login){
        model.addAttribute("user", new UserCredential());
        return "/unsubscribe";
    }


}
