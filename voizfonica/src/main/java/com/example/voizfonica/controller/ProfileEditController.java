package com.example.voizfonica.controller;

import com.example.voizfonica.data.UserCredentialRepository;
import com.example.voizfonica.model.Login;
import com.example.voizfonica.model.UserCredential;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/profileEdit")
@SessionAttributes("login")
public class ProfileEditController {


    private UserCredentialRepository userCredentialRepository;
    private String userId;
    @Autowired
    private ProfileEditController(UserCredentialRepository userCredentialRepository) {
        this.userCredentialRepository = userCredentialRepository;

    }

    @ModelAttribute(name = "userCredential")
    public UserCredential userCredential() {
        return new UserCredential();
    }

    @ModelAttribute(name = "login")
    public Login login(){
        return new Login();
    }
    @GetMapping
    public String getEdit(Model model,
                          @ModelAttribute Login login) {
        Optional<UserCredential> userCredential = userCredentialRepository.findById(login.getId());
        UserCredential user = userCredential.get();
        model.addAttribute("user", user);
        return "profileEdit";
    }
    @PostMapping
    public String setChanges(@Valid UserCredential userCredential, Errors errors, Model model,
                             @ModelAttribute Login login){
        Optional<UserCredential> user=userCredentialRepository.findById(login.getId());

        UserCredential user1=new UserCredential();
        user1.setId(user.get().getId());
        user1.setUserName(userCredential.getUserName());
        user1.setContactNumber(userCredential.getContactNumber());
        user1.setAadharNumber(user.get().getAadharNumber());
        user1.setAddress(userCredential.getAddress());
        user1.setEmailId(userCredential.getEmailId());
        user1.setPanNumber(user.get().getPanNumber());
        user1.setPassword(userCredential.getPassword());
        user1.setPrePaidPlan(user.get().getPrePaidPlan());
        user1.setPrePaidPlanId(user.get().getPrePaidPlanId());
        user1.setPostPaidPlan(user.get().getPostPaidPlan());
        user1.setPostPaidPlanId(user.get().getPostPaidPlanId());
        user1.setDonglePlan(user.get().getDonglePlan());
        user1.setDonglePlanId(user.get().getDonglePlanId());
        user1.setSecurityQuestion(user.get().getSecurityQuestion());
        user1.setSecurityAnswer(user.get().getSecurityAnswer());
//        user1.setRequiredPlan(user.get().getRequiredPlan());
        userCredentialRepository.save(user1);
        return "redirect:/profile";

    }

}


