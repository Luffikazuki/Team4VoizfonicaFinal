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

/*#############Controllers for selecting the plans  ##############################*/

@Controller
@SessionAttributes({"subscriptionDetail","login","dongleProduct"})
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

//    GetMapping controller or new prepaid connection

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
//  Controller for new postpaid connection
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

//    Controller to change from pre paid to post paid
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

//  Controller for new dongle connection
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

//   controller for buying a dongle
    @GetMapping("buydongle")
    public String showbuydongle(Model model){
        return "buydongle";
    }

//    Controller for selecting dongle plans after purchasing the dongle
    @GetMapping("/newConnection/dongleplans")
    public String showDonglePlansss(Model model){
        List<Dongle> threeG = dongleRepository.findByType("3G");
        List<Dongle> fourG = dongleRepository.findByType("4G");
        model.addAttribute("threeG",threeG);
        model.addAttribute("fourG",fourG);
        SubscriptionDetail subscriptionDetail = new SubscriptionDetail();
        subscriptionDetail.setProductId("dongle");
        model.addAttribute("subscriptionDetail",subscriptionDetail);
        DongleProduct dongleProduct = new DongleProduct();
        dongleProduct.setDongleName("VF_Ultimate");
        model.addAttribute("dongleProduct",dongleProduct);
        model.addAttribute("hasproduct","yes");
        return "dongle";

    }

//    Getting the dongle plans id
    @RequestMapping(value = "/newConnection/dongleplans/{planId}", method= RequestMethod.GET)
    public String updateSubscriptionDongleProduct(@PathVariable String planId, @ModelAttribute SubscriptionDetail subscriptionDetail,
                                           Model model,
                                                  @ModelAttribute DongleProduct dongleProduct){
        subscriptionDetail.setPlandId(planId);
        dongleProduct.setDonglePrice("2499 INR");
        model.addAttribute("subscriptionDetail",subscriptionDetail);
        model.addAttribute("dongleProduct",dongleProduct);
        return "redirect:/paymentdongle";
    }

    @GetMapping("/dongle")
    public String showDonglePlanss(Model model){
        List<Dongle> threeG = dongleRepository.findByType("3G");
        List<Dongle> fourG = dongleRepository.findByType("4G");
        model.addAttribute("threeG",threeG);
        model.addAttribute("fourG",fourG);
        return "dongle";
    }

//    Getting the prepaid plan id
    @RequestMapping(value = "/newConnection/prePaid/{planId}", method=RequestMethod.GET)
    public String updateSubscriptionPostPaid(@PathVariable String planId,
                                             @ModelAttribute SubscriptionDetail subscriptionDetail, Model model){
        subscriptionDetail.setPlandId(planId);
        model.addAttribute("subscriptionDetail",subscriptionDetail);
        return "redirect:/payment";
    }
//    Getting the postpaid plan id

    @RequestMapping(value = "/newConnection/postPaid/{planId}", method= RequestMethod.GET)
    public String updateSubscriptionPrePaid(@PathVariable String planId, @ModelAttribute SubscriptionDetail subscriptionDetail,Model model){
        subscriptionDetail.setPlandId(planId);
        model.addAttribute("subscriptionDetail",subscriptionDetail);
        return "redirect:/payment";
    }

//    Getting the post paid plan after opting to change from prepaid
    @RequestMapping(value = "/changetopostpaid/{planId}", method= RequestMethod.GET)
    public String updateSubscriptionChangeToPostPaid(@PathVariable String planId,
                                                     @ModelAttribute SubscriptionDetail subscriptionDetail,
                                                     Model model){
        subscriptionDetail.setPlandId(planId);
        model.addAttribute("subscriptionDetail",subscriptionDetail);
        return "redirect:/payments";
    }

//    Getting the dongle plan id without a dongle
    @RequestMapping(value = "/newConnection/dongle/{planId}", method= RequestMethod.GET)
    public String updateSubscriptionDongle(@PathVariable String planId, @ModelAttribute SubscriptionDetail subscriptionDetail,
                                           Model model){
        subscriptionDetail.setPlandId(planId);
        model.addAttribute("subscriptionDetail",subscriptionDetail);
        return "redirect:/payment";
    }





}
