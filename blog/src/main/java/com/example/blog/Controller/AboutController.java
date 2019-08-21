package com.example.blog.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AboutController {

    @GetMapping("/about")
    public String aboutfunc() {
        return "about";
    }

}
