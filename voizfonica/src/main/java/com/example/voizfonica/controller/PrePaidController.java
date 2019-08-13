package com.example.voizfonica.controller;

import com.example.voizfonica.data.DongleRepository;
import com.example.voizfonica.data.PostPaidRepository;
import com.example.voizfonica.data.PrePaidRepository;
import com.example.voizfonica.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes({"subscriptionDetail","login"})
public class PrePaidController {
    private PrePaidRepository prePaidRepository;
    private PostPaidRepository postPaidRepository;
    private DongleRepository dongleRepository;


    @Autowired
    public PrePaidController(PrePaidRepository prePaidRepository,
                             PostPaidRepository postPaidRepository,
                             DongleRepository dongleRepository){
        this.prePaidRepository = prePaidRepository;
        this.postPaidRepository = postPaidRepository;
        this.dongleRepository = dongleRepository;
    }

    @ModelAttribute(name ="login")
    public Login login(){
        return new Login();
    }

    @GetMapping("/newConnection/prePaid")
    public String showPrePaidPlans(Model model){
        List<PrePaid> threeG = prePaidRepository.findByType("3G");
        List<PrePaid> fourG = prePaidRepository.findByType("4G");
        model.addAttribute("threeG",threeG);
        model.addAttribute("fourG",fourG);
        SubscriptionDetail subscriptionDetail = new SubscriptionDetail();
        subscriptionDetail.setProductId("prePaid");
        model.addAttribute("subscriptionDetail",subscriptionDetail);
        return "prePaid";
    }

    @GetMapping("/prePaid")
    public String showPrePaidPlanss(Model model){
        List<PrePaid> threeG = prePaidRepository.findByType("3G");
        List<PrePaid> fourG = prePaidRepository.findByType("4G");
        model.addAttribute("threeG",threeG);
        model.addAttribute("fourG",fourG);
        return "prePaid";
    }

    @GetMapping("/newConnection/postPaid")
    public String showPostPaidPlans(Model model){
        List<PostPaid> threeG = postPaidRepository.findByType("3G");
        List<PostPaid> fourG = postPaidRepository.findByType("4G");
        model.addAttribute("threeG",threeG);
        model.addAttribute("fourG",fourG);
        SubscriptionDetail subscriptionDetail = new SubscriptionDetail();
        subscriptionDetail.setProductId("postPaid");
        model.addAttribute("subscriptionDetail",subscriptionDetail);
        model.addAttribute("changetopostpaid","no");
        return "postPaid";
    }

    @GetMapping("changetopostpaid")
    public String processRequest(Model model){
        List<PostPaid> threeG = postPaidRepository.findByType("3G");
        List<PostPaid> fourG = postPaidRepository.findByType("4G");
        model.addAttribute("threeG",threeG);
        model.addAttribute("fourG",fourG);
        SubscriptionDetail subscriptionDetail = new SubscriptionDetail();
        subscriptionDetail.setProductId("postPaid");
        model.addAttribute("subscriptionDetail",subscriptionDetail);
        model.addAttribute("changetopostpaid","yes");
        return "postPaid";
    }

    @GetMapping("/postPaid")
    public String showPostPaidPlanss(Model model){
        List<PostPaid> threeG = postPaidRepository.findByType("3G");
        List<PostPaid> fourG = postPaidRepository.findByType("4G");
        model.addAttribute("threeG",threeG);
        model.addAttribute("fourG",fourG);
        model.addAttribute("changetopostpaid","no");
        return "postPaid";
    }


    @GetMapping("/newConnection/dongle")
    public String showDonglePlans(Model model){
        List<Dongle> threeG = dongleRepository.findByType("3G");
        List<Dongle> fourG = dongleRepository.findByType("4G");
        model.addAttribute("threeG",threeG);
        model.addAttribute("fourG",fourG);
        SubscriptionDetail subscriptionDetail = new SubscriptionDetail();
        subscriptionDetail.setProductId("dongle");
        model.addAttribute("subscriptionDetail",subscriptionDetail);
        return "dongle";
    }

    @GetMapping("/dongle")
    public String showDonglePlanss(Model model){
        List<Dongle> threeG = dongleRepository.findByType("3G");
        List<Dongle> fourG = dongleRepository.findByType("4G");
        model.addAttribute("threeG",threeG);
        model.addAttribute("fourG",fourG);
        return "dongle";
    }

    @RequestMapping(value = "/newConnection/prePaid/{planId}", method=RequestMethod.GET)
    public String updateSubscriptionPostPaid(@PathVariable String planId,
                                             @ModelAttribute SubscriptionDetail subscriptionDetail, Model model){
        subscriptionDetail.setPlandId(planId);
        model.addAttribute("subscriptionDetail",subscriptionDetail);
        return "redirect:/payment";
    }

    @RequestMapping(value = "/newConnection/postPaid/{planId}", method= RequestMethod.GET)
    public String updateSubscriptionPrePaid(@PathVariable String planId, @ModelAttribute SubscriptionDetail subscriptionDetail,Model model){
        subscriptionDetail.setPlandId(planId);
        model.addAttribute("subscriptionDetail",subscriptionDetail);
        return "redirect:/payment";
    }

    @RequestMapping(value = "/changetopostpaid/{planId}", method= RequestMethod.GET)
    public String updateSubscriptionChangeToPostPaid(@PathVariable String planId,
                                                     @ModelAttribute SubscriptionDetail subscriptionDetail,
                                                     Model model){
        subscriptionDetail.setPlandId(planId);
        model.addAttribute("subscriptionDetail",subscriptionDetail);
        return "redirect:/payments";
    }

    @RequestMapping(value = "/newConnection/dongle/{planId}", method= RequestMethod.GET)
    public String updateSubscriptionDongle(@PathVariable String planId, @ModelAttribute SubscriptionDetail subscriptionDetail,
                                           Model model){
        subscriptionDetail.setPlandId(planId);
        model.addAttribute("subscriptionDetail",subscriptionDetail);
        return "redirect:/payment";
    }





}
