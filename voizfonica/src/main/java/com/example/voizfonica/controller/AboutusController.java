package com.example.voizfonica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AboutusController {

    @GetMapping("/aboutus")
    public String aboutusfunc() {
        return "aboutus";
    }

}
