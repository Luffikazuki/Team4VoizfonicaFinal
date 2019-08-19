package com.example.voizfonica.controller;

import com.example.voizfonica.data.UserCredentialRepository;
import com.example.voizfonica.model.Login;
import com.example.voizfonica.model.UserCredential;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
    private JavaMailSender javaMail;
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
        user1.setAddress(user.get().getAddress());
        user1.setEmailId(userCredential.getEmailId());
        user1.setPanNumber(user.get().getPanNumber());
        user1.setPassword(user.get().getPassword());
        user1.setPrePaidPlan(user.get().getPrePaidPlan());
        user1.setPrePaidPlanId(user.get().getPrePaidPlanId());
        user1.setPrePaidPlanNumber(user.get().getPrePaidPlanNumber());
        user1.setPostPaidPlan(user.get().getPostPaidPlan());
        user1.setPostPaidPlanId(user.get().getPostPaidPlanId());
        user1.setPostPaidPlanNumber(user.get().getPostPaidPlanNumber());
        user1.setDonglePlan(user.get().getDonglePlan());
        user1.setDonglePlanId(user.get().getDonglePlanId());
        user1.setDonglePlanNumber(user.get().getDonglePlanNumber());
        user1.setSecurityQuestion(user.get().getSecurityQuestion());
        user1.setSecurityAnswer(user.get().getSecurityAnswer());
//        user1.setRequiredPlan(user.get().getRequiredPlan());
        //Mail is Sent
        SimpleMailMessage msg=new SimpleMailMessage();
        msg.setTo(userCredential.getEmailId());
        msg.setSubject("Acknowledgement from voizfonica");
        msg.setText("Hi "+userCredential.getUserName()+",\n\nYour changes has been updated successfully " +

                "\n\n\nThanks and regards,\nTeam VoizFonica.");
        javaMail.send(msg);
        //Mail function ends here
        userCredentialRepository.save(user1);
        return "redirect:/profile";

    }

}


