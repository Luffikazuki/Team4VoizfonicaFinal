package com.example.voizfonica.controller;

import com.example.voizfonica.data.DongleSampleRepository;
import com.example.voizfonica.data.PlanDetailRepository;
import com.example.voizfonica.data.SubscriptionDetailRepository;
import com.example.voizfonica.model.DongleSample;
import com.example.voizfonica.model.PlanDetail;
import com.example.voizfonica.model.SubscriptionDetail;
import com.example.voizfonica.model.UserCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.List;

@Controller
@SessionAttributes("subscriptionDetail")
public class ChoosePlanController {

    private PlanDetailRepository planDetailRepository;
    private SubscriptionDetailRepository subscriptionDetailRepository;
    private DongleSampleRepository dongleSampleRepository;

    @Autowired
    public ChoosePlanController(PlanDetailRepository planDetailRepository,
                                SubscriptionDetailRepository subscriptionDetailRepository,
                                DongleSampleRepository dongleSampleRepository){
        this.planDetailRepository = planDetailRepository;
        this.subscriptionDetailRepository = subscriptionDetailRepository;
        this.dongleSampleRepository = dongleSampleRepository;
    }

    @ModelAttribute(name = "subscriptionDetail")
    public SubscriptionDetail subscriptionDetail(){
        return new SubscriptionDetail();
    }



//    @GetMapping("/chooseplan")
//    public String showPlan(Model model){
//        //List<PlanDetail> planDetails = planDetailRepository.findAll();
//        List<PlanDetail> threeG = planDetailRepository.findByType("3G");
//        List<PlanDetail> fourG = planDetailRepository.findByType("4G");
//        model.addAttribute("threeG",threeG);
//        model.addAttribute("fourG",fourG);
////        model.addAttribute("planDetail",new PlanDetail());
//        return "planSample";
//    }

//    @RequestMapping(value = "/chooseplan/{planId}", method=RequestMethod.GET)
//    @ResponseBody
//    public String updateSubscription(@PathVariable String planId, @ModelAttribute SubscriptionDetail subscriptionDetail){
//        subscriptionDetail.setPlandId(planId);
//        subscriptionDetailRepository.save(subscriptionDetail);
//        return "redirect:successful";
//    }

//    @PostMapping("/chooseplan")
//    public String processPlan(@Valid PlanDetail planDetail, @ModelAttribute SubscriptionDetail subscriptionDetail, Errors erros, SessionStatus sessionStatus){
//
//        if(erros.hasErrors()){
//            return "/planSample";
//        }
//        else {
//            subscriptionDetail.setPlandId(planDetail.getPlanName());
//            subscriptionDetailRepository.save(subscriptionDetail);
//            sessionStatus.setComplete();
//            return "redirect:/successful";
//        }




//    @PostMapping("/chooseplan3")
//    public String processDonglePlan(@Valid DongleSample dongleSample, @ModelAttribute SubscriptionDetail subscriptionDetail,
//                                    Errors erros, SessionStatus sessionStatus){
//
//    }
}
