package com.example.voizfonica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MNPSuccessController {

    @GetMapping("MNPSuccess")
    public String mnpsuccessfunc() {
        return "MNPSuccess";
    }

}
