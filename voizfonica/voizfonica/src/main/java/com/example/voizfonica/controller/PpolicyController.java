package com.example.voizfonica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PpolicyController {

    @GetMapping("/Ppolicy")
    public String policyfunc() {
        return "Ppolicy";
    }

}
