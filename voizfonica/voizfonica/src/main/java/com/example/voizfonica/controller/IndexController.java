package com.example.voizfonica.controller;

import com.example.voizfonica.model.Login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;


@Controller
@SessionAttributes("login")
public class IndexController {

    @ModelAttribute(name ="login")
    public Login login(){
        return new Login();
    }

    @GetMapping("/")
    public String investorfunc(@ModelAttribute Login login, SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "index";
    }

}
