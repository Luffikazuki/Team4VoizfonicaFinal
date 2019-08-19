package com.example.voizfonica.controller;




import com.example.voizfonica.data.MNPRepository;
import com.example.voizfonica.model.MNPDomain;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
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
@RequestMapping("/MNP")
public class MNPController {

    private MNPRepository mnprepository;

    @Autowired
    public MNPController(MNPRepository mnprepository) {
        this.mnprepository = mnprepository;
    }

    @ModelAttribute(name = "MNP")
    public MNPDomain register() {
        return new MNPDomain();
    }


    @GetMapping
    public String showRegistrationForm(Model model) {
        MNPDomain mnpdomain = new MNPDomain();
        model.addAttribute("mnpdomain", mnpdomain);
        return "MNP";
    }


    @PostMapping
    public String processRegistrationFrom(@Valid MNPDomain mnpdomain, Errors errors, Model model) {
            mnprepository.save(mnpdomain);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+91"+mnpdomain.getMobNumber()),
                new PhoneNumber("+12052739633"),
                "Welcome to voizfonica..! Your request for mobile number portability will be processed soon")
                .create();
            //model.addAttribute("showDetails", mnpdomain);
        return "MNPSuccess";
        }
    }





