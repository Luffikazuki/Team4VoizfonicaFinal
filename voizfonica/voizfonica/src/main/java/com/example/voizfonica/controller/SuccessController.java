package com.example.voizfonica.controller;

import com.example.voizfonica.data.DongleProductRepository;
import com.example.voizfonica.data.PlanDetailRepository;
import com.example.voizfonica.data.PrePaidRepository;
import com.example.voizfonica.data.UserCredentialRepository;
import com.example.voizfonica.model.DongleProduct;
import com.example.voizfonica.model.Login;
import com.example.voizfonica.model.PlanDetail;
import com.example.voizfonica.model.UserCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes("login")
public class SuccessController {
    private PlanDetailRepository planDetailRepository;
    private UserCredentialRepository userCredentialRepository;
    private DongleProductRepository dongleProductRepository;
    @Autowired
    private JavaMailSender javaMail;
    @Autowired
    public SuccessController(PlanDetailRepository planDetailRepository,
                             UserCredentialRepository userCredentialRepository,
                             DongleProductRepository dongleProductRepository){
        this.planDetailRepository = planDetailRepository;
        this.userCredentialRepository = userCredentialRepository;
        this.dongleProductRepository = dongleProductRepository;
    }
    @GetMapping("/success")
    public String showSuccess(@ModelAttribute Login login, Model model){

        Optional<UserCredential> userCredential = userCredentialRepository.findById(login.getId());
        Optional<PlanDetail> planDetail = planDetailRepository.findById(login.getPassword());
        login.setPassword("null");
        model.addAttribute("login",login);
        model.addAttribute("userCredential",userCredential.get());
        model.addAttribute("planDetail",planDetail.get());
        if(login.getEmailId() == null){
            model.addAttribute("hasproduct","no");
            //Mail is Sent
            SimpleMailMessage msg=new SimpleMailMessage();
            msg.setTo(userCredential.get().getEmailId());
            msg.setSubject("Invoice from Voizfonica");
            msg.setText("Hi "+userCredential.get().getUserName()+",\n\n" + "Your payment to "+planDetail.get().getProductId()+
                    " plan is successful.\nPlan Name:"+planDetail.get().getAmountPaid()+"/"+planDetail.get().getPlanType()+
                    "\nMobile number:"+planDetail.get().getGeneratedNumber()+"\nAmount paid: "+planDetail.get().getAmountPaid()+
                    "\n\n\nThanks and regards,\nTeam VoizFonica.");
            javaMail.send(msg);
            //Mail function ends here
        } else{
            model.addAttribute("hasproduct","yes");
            Optional<DongleProduct> dongleProduct = dongleProductRepository.findById(login.getEmailId());
            model.addAttribute("dongleProduct",dongleProduct.get());
            //Mail is Sent
            SimpleMailMessage msg=new SimpleMailMessage();
            msg.setTo(userCredential.get().getEmailId());
            msg.setSubject("Invoice from Voizfonica");
            msg.setText("Hi "+userCredential.get().getUserName()+",\n\n" + "Your payment to dongle plan "+
                    "and Dongle"+ " is successful.\n\nPlan Name:"+planDetail.get().getAmountPaid()+
                    "/"+planDetail.get().getPlanType()+"\nDongle Name: "+dongleProduct.get().getDongleName()+
                    "\nMobile number:"+planDetail.get().getGeneratedNumber()+"\nAmount paid: "+dongleProduct.get().getTotalprice()+
                    " INR\n\n\nThanks and regards,\nTeam VoizFonica.");
            javaMail.send(msg);
            //Mail function ends here
        }
        return "/success";
    }
}
