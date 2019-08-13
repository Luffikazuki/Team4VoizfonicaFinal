package com.example.voizfonica.controller;

import com.example.voizfonica.data.UserCredentialRepository;
import com.example.voizfonica.model.Login;
import com.example.voizfonica.model.UserCredential;
import com.example.voizfonica.model.Verification;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.constraints.PastOrPresent;
import java.util.List;

@Controller
public class ForgotPasswordController {
    private UserCredentialRepository userCredentialRepository;
    private String verification1 = "no";
    private String verification2 = "no";
    private String mailId;
    private String question;

    @Autowired
    public ForgotPasswordController(UserCredentialRepository userCredentialRepository){
        this.userCredentialRepository = userCredentialRepository;
    }
    @GetMapping("/forgotPassword")
    public String getVerification(Model model){
        model.addAttribute("login",new Login());
        return "forgotPassword";
    }

    @PostMapping("/forgotPassword")
    public String processVerification(Login login, Model model){
        List<UserCredential> userCredentials = userCredentialRepository.findByEmailId(login.getEmailId());
        if(userCredentials.isEmpty()){
            model.addAttribute("emailExist","no");
            return "forgotPassword";

        }else{
            model.addAttribute("emailExist","yes");
            verification1 = "verified";
            mailId = login.getEmailId();
            question = userCredentials.get(0).getSecurityQuestion();
            return "redirect:/verify";
        }
    }


    // Verification 2 Starts here
    @GetMapping("verify")
    public String getVerification2(Model model){
        if(verification1.equals("verified")){
            model.addAttribute("question",question);
            model.addAttribute("verification",new Verification());
            return "forgotPasswordSecurity";
        }else{
            return "login";
        }
    }

    @PostMapping("verify")
    public String processVerification2(Verification verification,Model model){
        List<UserCredential> userCredentials = userCredentialRepository.findByEmailId(mailId);
        if(verification.getVerificationAnswer().equals(userCredentials.get(0).getSecurityAnswer())){
            verification2 = "verified";
            return "redirect:/confirmPassword";
        }else{
            model.addAttribute("correct","no");
            model.addAttribute("question",question);
            model.addAttribute("verification",new Verification());
            return "forgotPasswordSecurity";
        }
    }

    @GetMapping("confirmPassword")
    public String getNewPassword(Verification verification, Model model){
        if(verification1.equals(verification2)){
            model.addAttribute("resetPassword",new Verification());
            return "forgotPasswordReset";
        }else{
            return "login";
        }
    }

    @PostMapping("confirmPassword")
    public String processNewPassword(Verification verification, Model model){
        List<UserCredential> userCredentials = userCredentialRepository.findByEmailId(mailId);
        userCredentials.get(0).setPassword(verification.getVerificationAnswer());
        userCredentialRepository.save(userCredentials.get(0));
        return "redirect:/resetSuccessful";
    }

    @GetMapping("resetSuccessful")
    public String showSuccess(Model model){
        model.addAttribute("reset","yes");
        model.addAttribute("resetPassword",new Verification());
        return "forgotPasswordReset";
    }
}
