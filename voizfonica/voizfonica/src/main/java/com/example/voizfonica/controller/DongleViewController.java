package com.example.voizfonica.controller;


import com.example.voizfonica.data.DongleRepository;
import com.example.voizfonica.model.Dongle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;
import java.util.List;



@Controller
@RequestMapping("/dongleView")
public class DongleViewController {

    private DongleRepository dongleRepo;
    @Autowired
    public DongleViewController(DongleRepository dongleRepo) {
        this.dongleRepo=dongleRepo;
    }


    @GetMapping
    public String donglePlanFunc(Model model) {
        List<Dongle> THREEG=dongleRepo.findByType("3G");
        List<Dongle> FOURG=dongleRepo.findByType("4G");
        model.addAttribute("THREEG",THREEG);
        model.addAttribute("FOURG",FOURG);
        return  "dongleView";
    }

}
