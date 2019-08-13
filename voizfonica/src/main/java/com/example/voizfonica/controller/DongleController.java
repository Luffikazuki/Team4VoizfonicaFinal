package com.example.voizfonica.controller;


import com.example.voizfonica.data.DongleRepository;
import com.example.voizfonica.model.Dongle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DongleController {

//    private DongleRepository dongleRepository;
//
//    @Autowired
//    public DongleController(DongleRepository dongleRepository) {
//
//        this.dongleRepository = dongleRepository;
//
//    }
//
//    @GetMapping("/dongle")
//    public String showDongle(Model model) {
//        List<Dongle> threeG = dongleRepository.findByType("3G");
//        List<Dongle> fourG = dongleRepository.findByType("4G");
//        model.addAttribute("threeG",threeG);
//        model.addAttribute("fourG",fourG);
//        return "dongle";
//    }
//
//

}

