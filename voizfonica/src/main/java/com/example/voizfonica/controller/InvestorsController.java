package com.example.voizfonica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class InvestorsController {

    @GetMapping("/investors")
    public String investorfunc() {
        return "investors";
    }

}
