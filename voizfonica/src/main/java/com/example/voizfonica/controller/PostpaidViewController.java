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
@RequestMapping("/postpaidView")
public class PostpaidViewController {

    private PostPaidRepository postRepo;
    @Autowired
    public PostpaidViewController(PostPaidRepository postRepo) {
        this.postRepo=postRepo;
    }


    @GetMapping
    public String postPlanFunc(Model model) {
        List<PostPaid> THREEG=postRepo.findByType("3G");
        List<PostPaid> FOURG=postRepo.findByType("4G");
        model.addAttribute("THREEG",THREEG);
        model.addAttribute("FOURG",FOURG);
        return  "postpaidView";

    }

}
