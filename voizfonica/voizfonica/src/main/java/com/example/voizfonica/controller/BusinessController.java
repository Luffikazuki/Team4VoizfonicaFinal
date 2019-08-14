package com.example.voizfonica.controller;

        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class BusinessController {

    @GetMapping("/business")
    public String businessfunc() {
        return "business";
    }

}
