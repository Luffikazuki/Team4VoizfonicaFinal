package com.example.voizfonica.controller;


import com.example.voizfonica.data.ComplaintRepository;
import com.example.voizfonica.model.Complaint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

@Controller
@RequestMapping("/customerComplaint")
public class CustomerComplaintController {

    private ComplaintRepository complaintrepository;

    @Autowired
    public  CustomerComplaintController(ComplaintRepository complaintrepository) {
        this.complaintrepository = complaintrepository;
    }

    @ModelAttribute(name = "customerComplaint")
    public Complaint register() {
        return new Complaint();
    }


    @GetMapping
    public String showForm(Model model) {
        Complaint complaint = new Complaint();
        model.addAttribute("complaint", complaint);
        return "customerComplaint";
    }


    @PostMapping
    public String processQuery(@Valid  Complaint complaint, Errors errors, Model model) {
        if(errors.hasErrors()){
            return "customerComplaint";
        }else{
            System.out.println(complaint);
            complaint.setTicket(complaint.getTicket()+1);
            System.out.println(complaint);
            complaintrepository.save(complaint);
            // model.addAttribute("showDetails",userCredentials);
            return "ComplaintSubmit";
        }

    }
}
