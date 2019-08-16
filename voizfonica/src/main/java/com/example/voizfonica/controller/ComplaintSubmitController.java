package com.example.voizfonica.controller;

import com.example.voizfonica.data.ComplaintRepository;
import com.example.voizfonica.model.Complaint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ComplaintSubmit")
public class ComplaintSubmitController {
    private ComplaintRepository complaintrepository;

    @Autowired
    public  ComplaintSubmitController(ComplaintRepository complaintrepository) {
        this.complaintrepository = complaintrepository;
    }

    @ModelAttribute(name = "ComplaintSubmit")
    public Complaint register() {
        return new Complaint();
    }


    @GetMapping
    public String showSubmitForm(Model model) {
        Complaint complaint = new Complaint();
        model.addAttribute("complaint", complaint);
        return "ComplaintSubmit";
    }

}
