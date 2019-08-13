package com.example.voizfonica.controller;



import com.example.voizfonica.data.*;
import com.example.voizfonica.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Optional;

@Controller
@RequestMapping("/payment")
@SessionAttributes({"subscriptionDetail","login"})
public class PaymentController {

    private PaymentRepository paymentRepository;
    private SubscriptionDetailRepository subscriptionDetailRepository;
    private PrePaidRepository prePaidRepository;
    private PostPaidRepository postPaidRepository;
    private PlanDetailRepository planDetailRepository;
    private DongleRepository dongleRepository;
    private UserCredentialRepository userCredentialRepository;

    @Autowired
    public PaymentController(PaymentRepository paymentRepository,
                             SubscriptionDetailRepository subscriptionDetailRepository,
                             PrePaidRepository prePaidRepository,
                             PostPaidRepository postPaidRepository,
                             PlanDetailRepository planDetailRepository,
                             DongleRepository dongleRepository,
                             UserCredentialRepository userCredentialRepository) {
        this.paymentRepository = paymentRepository;
        this.subscriptionDetailRepository = subscriptionDetailRepository;
        this.prePaidRepository = prePaidRepository;
        this.postPaidRepository = postPaidRepository;
        this.planDetailRepository = planDetailRepository;
        this.dongleRepository = dongleRepository;
        this.userCredentialRepository = userCredentialRepository;
    }

    @ModelAttribute(name = "payment")
    public Payment pay() {
        return new Payment();
    }

    @GetMapping
    public String showPaymentForm(Model model) {
        Payment payment = new Payment();
        model.addAttribute("payment", payment);
        return "payment";
    }

    @PostMapping
    public String processPaymentFrom(@Valid Payment payment, Errors errors,
                                     @ModelAttribute SubscriptionDetail subscriptionDetail, Model model,
                                     @ModelAttribute Login login) {
        if (errors.hasErrors()) {
            return "payment";
        } else {
            paymentRepository.save(payment);
            //model.addAttribute("hello", payment);
            subscriptionDetail.setPayment(payment);
            //subscriptionDetailRepository.save(subscriptionDetail);
            subscriptionDetail.setUserId(login.getId());
            PlanDetail planDetail = new PlanDetail();
            planDetail.setUserId(subscriptionDetail.getUserId()); //user id set
            planDetail.setPlanId(subscriptionDetail.getPlandId()); //plan id set
            planDetail.setProductId(subscriptionDetail.getProductId()); // product id set
            planDetail.setStartDate(subscriptionDetail.getPayment().getPaymentDate()); // plan start date set
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(subscriptionDetail.getPayment().getPaymentDate());
//            calendar.add(Calendar.DAY_OF_MONTH,28);
//            planDetail.setEndDate(calendar.getTime());
            String productId = subscriptionDetail.getProductId();
            String validity;
            String data;
            String amountPaid;
            String planType;
            Optional<UserCredential>  userCredential = userCredentialRepository.findById(login.getId());

            ////Getting plan validity
            if(productId.equals("prePaid")){
                Optional<PrePaid> prePaid = prePaidRepository.findById(subscriptionDetail.getPlandId());
//                Optional<UserCredential>  userCredential = userCredentialRepository.findById(login.getId());
                userCredential.get().setPrePaidPlan("prePaid");
                userCredential.get().setPrePaidPlanId(planDetail.getId());
//                userCredentialRepository.save(userCredential.get());
                validity = prePaid.get().getValidity();
                data = prePaid.get().getBenefits();
                amountPaid = prePaid.get().getPreMoney();
                planType = prePaid.get().getType();
            }else if(productId.equals("postPaid")){
                Optional<PostPaid> postPaid = postPaidRepository.findById(subscriptionDetail.getPlandId());
                validity = postPaid.get().getValidity();
                data = postPaid.get().getBenefits();
                amountPaid = postPaid.get().getPostMoney();
                planType = postPaid.get().getType();
//                Optional<UserCredential>  userCredential = userCredentialRepository.findById(login.getId());
                userCredential.get().setPostPaidPlan("postPaid");

            }else{
                Optional<Dongle> dongle = dongleRepository.findById(subscriptionDetail.getPlandId());
                validity = dongle.get().getValidity();
                data = dongle.get().getBenefits();
                amountPaid = dongle.get().getDongleMoney();
                planType = dongle.get().getType();
//                Optional<UserCredential>  userCredential = userCredentialRepository.findById(login.getId());
                userCredential.get().setDonglePlan("dongle");
                userCredential.get().setDonglePlanId(planDetail.getId());
//                userCredentialRepository.save(userCredential.get());
            }


            planDetail.setValidity(validity); // validity set
            planDetail.setAmountPaid(amountPaid); // amountPaid set
            planDetail.setPlanType(planType); // planType set
            subscriptionDetail.setPlanPrice(amountPaid); // plan price set
            char[] validityChar = validity.toCharArray();
            int validityNumber = validityChar[0] - '0';
            for(int i=1;i<validityChar.length;i++){
                if(Character.isDigit(validityChar[i])){
                    validityNumber=validityNumber*10;
                    validityNumber=validityNumber+validityChar[i]-'0';
                }
            }

            calendar.add(Calendar.DAY_OF_MONTH,validityNumber);
            planDetail.setEndDate(calendar.getTime());
            planDetail.setData(data);
            planDetail.setRemainingData(data);
            // creating a randam 10 digit mobile number;
            long mobileNumber = (long)((Math.random() * 100000000) + 7980000000L);
            String stringMobileNumber = Long.toString(mobileNumber);
            planDetail.setGeneratedNumber(stringMobileNumber);
            planDetailRepository.save(planDetail);
            subscriptionDetailRepository.save(subscriptionDetail);
            if(productId.equals("prePaid")){
                userCredential.get().setPrePaidPlanId(planDetail.getId());
                userCredentialRepository.save(userCredential.get());
            }else if(productId.equals("postPaid")){
                userCredential.get().setPostPaidPlanId(planDetail.getId());
                userCredentialRepository.save(userCredential.get());
            }else{
                userCredential.get().setDonglePlanId(planDetail.getId());
                userCredentialRepository.save(userCredential.get());
            }
            model.addAttribute("paymentMade","yes");
            return "payment";
            //return "redirect:/success";
        }
    }
}
