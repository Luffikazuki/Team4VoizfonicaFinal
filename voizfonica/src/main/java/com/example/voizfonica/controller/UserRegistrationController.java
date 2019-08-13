package com.example.voizfonica.controller;

import com.example.voizfonica.data.UserCredentialRepository;
import com.example.voizfonica.model.Login;
import com.example.voizfonica.model.SubscriptionDetail;
import com.example.voizfonica.model.UserCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/customerRegister")
public class UserRegistrationController {
    private UserCredentialRepository userCredentialRepository;


    @Autowired
    public UserRegistrationController(UserCredentialRepository userCredentialRepository){
        this.userCredentialRepository = userCredentialRepository;
    }

    @ModelAttribute(name = "userCredentials")
    public UserCredential userCredential(){
        return new UserCredential();
    }



    @GetMapping
    public String showRegistrationForm(Model model){
        UserCredential userCredential = new UserCredential();
        model.addAttribute("userCredential",userCredential);
        return "customerRegister";
    }

    @PostMapping
    public String processRegistrationFrom(@Valid UserCredential userCredential, Errors errors,Model model){
        if(errors.hasErrors()){
            return "customerRegister";
        }else{
            List<UserCredential> userCredentials = userCredentialRepository.findByEmailId(userCredential.getEmailId());

            if(userCredentials.isEmpty()) {
                userCredential.setPrePaidPlan("null");
                userCredential.setPostPaidPlan("null");
                userCredential.setDonglePlan("null");
                userCredential.setPrePaidPlanId("null");
                userCredential.setPostPaidPlanId("null");
                userCredential.setDonglePlanId("null");

                userCredentialRepository.save(userCredential);


                model.addAttribute("registrationSuccess", "yes");
                return "customerRegister";
            }else{
                model.addAttribute("mailExist","yes");
                return "customerRegister";
            }
        }
    }

}

