package com.example.voizfonica.controller;

import com.example.voizfonica.data.PlanDetailRepository;
import com.example.voizfonica.data.UserCredentialRepository;
import com.example.voizfonica.model.*;
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
public class DashBoardController {

    private PlanDetailRepository planDetailRepository;
    private UserCredentialRepository userCredentialRepository;


    @Autowired
    public DashBoardController(PlanDetailRepository planDetailRepository,
                               UserCredentialRepository userCredentialRepository){
        this.planDetailRepository = planDetailRepository;
        this.userCredentialRepository = userCredentialRepository;
    }


    @ModelAttribute(name ="login")
    public Login login(){
        return new Login();
    }

    @GetMapping("dashboard")
    public String showDashBoard(@ModelAttribute Login login,Model model) {
        if (login.getUserName().isEmpty()) {
            return "/error101";
        }else{
            model.addAttribute("login", login);
            Optional<UserCredential> userCredential = userCredentialRepository.findById(login.getId());
            if(userCredential.get().getPrePaidPlan().equals("null") &&
                    userCredential.get().getPostPaidPlan().equals("null") &&
                    userCredential.get().getDonglePlan().equals("null")){
                model.addAttribute("noplans","noplans");
                return "/dashboard";
            }else {

                model.addAttribute("plans","plans");

                if(userCredential.get().getPrePaidPlan().equals("prePaid")){
                    model.addAttribute("hasPrePaidPlan","yes");
                    Optional<PlanDetail> planDetail = planDetailRepository.findById(userCredential.get().getPrePaidPlanId());
                    model.addAttribute("prePaid",planDetail.get());
                }else{
                    model.addAttribute("hasPrePaidPlan","no");
                }

                if(userCredential.get().getPostPaidPlan().equals("postPaid")){
                    model.addAttribute("hasPostPaidPlan","yes");
                    Optional<PlanDetail> planDetail = planDetailRepository.findById(userCredential.get().getPostPaidPlanId());
                    model.addAttribute("postPaid",planDetail.get());
                }else{
                    model.addAttribute("hasPostPaidPlan","no");
                }

                if(userCredential.get().getDonglePlan().equals("dongle")){
                    model.addAttribute("hasDonglePlan","yes");
                    Optional<PlanDetail> planDetail = planDetailRepository.findById(userCredential.get().getDonglePlanId());
                    model.addAttribute("dongle",planDetail.get());
                }else{
                    model.addAttribute("hasDonglePlan","no");
                }


                return "/dashboard";
            }
        }
    }

}
