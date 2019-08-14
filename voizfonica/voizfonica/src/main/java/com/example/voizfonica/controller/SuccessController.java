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
        }else{
            model.addAttribute("hasproduct","yes");
            Optional<DongleProduct> dongleProduct = dongleProductRepository.findById(login.getEmailId());
            model.addAttribute("dongleProduct",dongleProduct.get());
        }
        return "/success";
    }
}
