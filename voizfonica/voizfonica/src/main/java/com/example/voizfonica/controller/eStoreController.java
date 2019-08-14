package com.example.voizfonica.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/eStore")
public class eStoreController {
    @GetMapping
    public String showStore() {

        return "eStore";
    }

}
