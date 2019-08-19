package com.example.voizfonica.controller;

import com.example.voizfonica.data.UserCredentialRepository;
import com.example.voizfonica.model.Login;
import com.example.voizfonica.model.SubscriptionDetail;
import com.example.voizfonica.model.UserCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
    private JavaMailSender javaMail;

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
//            Checking whether the usercredentials object is empty or not!
            if(userCredentials.isEmpty()) {
                userCredential.setPrePaidPlan("null");
                userCredential.setPostPaidPlan("null");
                userCredential.setDonglePlan("null");
                userCredential.setPrePaidPlanId("null");
                userCredential.setPostPaidPlanId("null");
                userCredential.setDonglePlanId("null");
                userCredential.setPrePaidPlanNumber("null");
                userCredential.setPostPaidPlanNumber("null");
                userCredential.setDonglePlanNumber("null");
                userCredentialRepository.save(userCredential);

                userCredentialRepository.save(userCredential);


                model.addAttribute("registrationSuccess", "yes");
                //Mail is Sent
                SimpleMailMessage msg=new SimpleMailMessage();
                msg.setTo(userCredential.getEmailId());
                msg.setSubject("Welcome to voizfonica");
                msg.setText("Hi "+userCredential.getUserName()+",\n\nWe welcome you to the world's mightiest network! " +
                        "Voizfonica.\n\n"+"View our world's mightiest network's plans in www.voizfonica.com \n" +
                        "Purchase your world's mightiest network plan."+
                        "\n\n\nThanks and regards,\nTeam VoizFonica.");
                javaMail.send(msg);
                //Mail function ends here
                return "customerRegister";
            }else{
                model.addAttribute("mailExist","yes");
                return "customerRegister";
            }
        }
    }

}

