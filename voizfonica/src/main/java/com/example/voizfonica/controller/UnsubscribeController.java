package com.example.voizfonica.controller;

import com.example.voizfonica.data.PlanDetailHistoryRepository;
import com.example.voizfonica.data.PlanDetailRepository;
import com.example.voizfonica.data.SubscriptionDetailRepository;
import com.example.voizfonica.data.UserCredentialRepository;
import com.example.voizfonica.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes("login")
public class UnsubscribeController {
    private SubscriptionDetailRepository subscriptionDetailRepository;
    private UserCredentialRepository userCredentialRepository;
    private PlanDetailRepository planDetailRepository;
    private PlanDetailHistoryRepository planDetailHistoryRepository;


    @Autowired
    public UnsubscribeController(PlanDetailHistoryRepository planDetailHistoryRepository1, PlanDetailRepository planDetailRepository1, SubscriptionDetailRepository subscriptionDetailRepository1, UserCredentialRepository userCredentialRepository1){
        this.planDetailRepository = planDetailRepository1;
        this.subscriptionDetailRepository=subscriptionDetailRepository1;
        this.userCredentialRepository=userCredentialRepository1;
        this.planDetailHistoryRepository=planDetailHistoryRepository1;
    }


    @ModelAttribute(name = "login")
    public Login login(){
        return new Login();
    }

    @ModelAttribute(name="subscriptionDetail")
    public SubscriptionDetail subscriptionDetail()
    {return new SubscriptionDetail();}

    @GetMapping("/unsubscribe")
    public String showUnSubscirbe(Model model, @ModelAttribute Login login){
        Optional<UserCredential> userCredential = userCredentialRepository.findById(login.getId());
        UserCredential user=userCredential.get();
        model.addAttribute("user",user);
        if(userCredential.get().getPrePaidPlan().equals("prePaid")){
            model.addAttribute("PrePaidPlanUnsubscribe","yes");
            Optional<PlanDetail> planDetail = planDetailRepository.findById(userCredential.get().getPrePaidPlanId());
            model.addAttribute("prePaid",planDetail.get());
        }else{
            model.addAttribute("PrePaidPlanUnsubscribe","no");
        }

        if(userCredential.get().getPostPaidPlan().equals("postPaid")){
            model.addAttribute("PostPaidPlanUnsubscribe","yes");
            Optional<PlanDetail> planDetail = planDetailRepository.findById(userCredential.get().getPostPaidPlanId());
            model.addAttribute("postPaid",planDetail.get());
        }else{
            model.addAttribute("PostPaidPlanUnsubscribe","no");
        }

        if(userCredential.get().getDonglePlan().equals("dongle")){
            model.addAttribute("DonglePlanUnsubscribe","yes");
            Optional<PlanDetail> planDetail = planDetailRepository.findById(userCredential.get().getDonglePlanId());
            model.addAttribute("dongle",planDetail.get());
        }else{
            model.addAttribute("DonglePlanUnsubscribe","no");
        }

        return "/unsubscribe";
    }



    @GetMapping("unsubscribe/postpaid")
    public String unsubscribePostpaidPlan(@ModelAttribute Login login,Model model)
    {
        Optional<UserCredential> userCredential = userCredentialRepository.findById(login.getId());
        UserCredential user = userCredential.get();
        Optional<PlanDetail> planDetail=planDetailRepository.findById(user.getPostPaidPlanId());
        savePlanHistory(planDetail.get());
        planDetailRepository.delete(planDetail.get());
        user.setPostPaidPlan("null");
        user.setPostPaidPlanId("null");
        userCredentialRepository.save(user);
        login.setId(user.getId());
        login.setUserName(user.getUserName());
        login.setEmailId(null);
        login.setPassword(null);
        model.addAttribute("login",login);
        return "redirect:/profile";

    }

    @GetMapping("unsubscribe/prepaid")
    public String unsubscribePrepaidPlan(@ModelAttribute Login login,Model model)
    {
        Optional<UserCredential> userCredential = userCredentialRepository.findById(login.getId());
        UserCredential user = userCredential.get();
        Optional<PlanDetail> planDetail=planDetailRepository.findById(user.getPrePaidPlanId());
        savePlanHistory(planDetail.get());
        planDetailRepository.delete(planDetail.get());
        user.setPrePaidPlan("null");
        user.setPrePaidPlanId("null");
        userCredentialRepository.save(user);
        login.setId(user.getId());
        login.setUserName(user.getUserName());
        login.setEmailId(null);
        login.setPassword(null);
        model.addAttribute("login",login);
        return "redirect:/profile";

    }

    @GetMapping("unsubscribe/dongle")
    public String unsubscribeDonglePlan(@ModelAttribute Login login,Model model)
    {
        Optional<UserCredential> userCredential = userCredentialRepository.findById(login.getId());
        UserCredential user = userCredential.get();
        Optional<PlanDetail> planDetail=planDetailRepository.findById(user.getDonglePlanId());
        savePlanHistory(planDetail.get());
        planDetailRepository.delete(planDetail.get());
        user.setDonglePlan("null");
        user.setDonglePlanId("null");
        userCredentialRepository.save(user);
        login.setId(user.getId());
        login.setUserName(user.getUserName());
        login.setEmailId(null);
        login.setPassword(null);
        model.addAttribute("login",login);
        return "redirect:/profile";

    }

    public void savePlanHistory(PlanDetail plan)
    {
        PlanDetailHistory planHistory=new PlanDetailHistory();
        planHistory.setAmountPaid(plan.getAmountPaid());
        planHistory.setData(plan.getData());
        planHistory.setEndDate(plan.getEndDate());
        planHistory.setGeneratedNumber(plan.getGeneratedNumber());
        planHistory.setId(plan.getId());
        planHistory.setPlanId(plan.getPlanId());
        planHistory.setPlanType(plan.getPlanType());
        planHistory.setProductId(plan.getProductId());
        planHistory.setRemainingData(plan.getRemainingData());
        planHistory.setStartDate(plan.getStartDate());
        planHistory.setUserId(plan.getUserId());
        planHistory.setValidity(plan.getValidity());
        planDetailHistoryRepository.save(planHistory);
    }

}
