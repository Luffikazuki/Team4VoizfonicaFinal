package com.example.voizfonica.controller;

import com.example.voizfonica.data.PostPaidRepository;
import com.example.voizfonica.model.PostPaid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;
import java.util.List;



@Controller
@RequestMapping("/postPaid")
public class PostPaidController {

    private PostPaidRepository postRepo;
    @Autowired
    public PostPaidController(
            PostPaidRepository postRepo) {
        this.postRepo=postRepo;
    }

/*
    @GetMapping
    public String postPlanFunc(Model model) {
        List<PostPaid> THREEG=postRepo.findByType("THREEG");
        List<PostPaid> FOURG=postRepo.findByType("FOURG");
        model.addAttribute("THREEG",THREEG);
        model.addAttribute("FOURG",FOURG);
        return  "postPaid";

    }

    @PostMapping
    public String processPlanFrom(@Valid PostPaidController postpaidcontroller, Model model){


            model.addAttribute("payment",postpaidcontroller);
            return "redirect:/payment";

    }*/
}
