package com.example.voizfonica.controller;


import com.example.voizfonica.data.PrePaidRepository;
import com.example.voizfonica.model.PostPaid;
import com.example.voizfonica.model.PrePaid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;
import java.util.List;



@Controller
@RequestMapping("/prepaidView")
public class PrepaidViewController {

    private PrePaidRepository preRepo;
    @Autowired
    public PrepaidViewController(PrePaidRepository preRepo) {
        this.preRepo=preRepo;
    }


    @GetMapping
    public String prePlanFunc(Model model) {
        List<PrePaid> THREEG=preRepo.findByType("3G");
        List<PrePaid> FOURG=preRepo.findByType("4G");
        model.addAttribute("THREEG",THREEG);
        model.addAttribute("FOURG",FOURG);
        return  "prepaidView";

    }

}
