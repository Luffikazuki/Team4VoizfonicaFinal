package com.example.voizfonica.controller;

import com.example.voizfonica.data.*;
import com.example.voizfonica.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.Optional;

@Controller
@SessionAttributes({"subscriptionDetail","login","dongleProduct"})
public class PaymentController {

    private PaymentRepository paymentRepository;
    private SubscriptionDetailRepository subscriptionDetailRepository;
    private PrePaidRepository prePaidRepository;
    private PostPaidRepository postPaidRepository;
    private PlanDetailRepository planDetailRepository;
    private DongleRepository dongleRepository;
    private UserCredentialRepository userCredentialRepository;
    private PlanDetailHistoryRepository planDetailHistoryRepository;
    private DongleProductRepository dongleProductRepository;

    @Autowired
    private JavaMailSender javaMail;

    @Autowired
    public PaymentController(PaymentRepository paymentRepository,
                             SubscriptionDetailRepository subscriptionDetailRepository,
                             PrePaidRepository prePaidRepository,
                             PostPaidRepository postPaidRepository,
                             PlanDetailRepository planDetailRepository,
                             DongleRepository dongleRepository,
                             UserCredentialRepository userCredentialRepository,
                             PlanDetailHistoryRepository planDetailHistoryRepository,
                             DongleProductRepository dongleProductRepository) {
        this.paymentRepository = paymentRepository;
        this.subscriptionDetailRepository = subscriptionDetailRepository;
        this.prePaidRepository = prePaidRepository;
        this.postPaidRepository = postPaidRepository;
        this.planDetailRepository = planDetailRepository;
        this.dongleRepository = dongleRepository;
        this.userCredentialRepository = userCredentialRepository;
        this.planDetailHistoryRepository = planDetailHistoryRepository;
        this.dongleProductRepository = dongleProductRepository;
    }

    @ModelAttribute(name = "payment")
    public Payment pay() {
        return new Payment();
    }

    /*  Get Mapping in payment controller for prepaid , postpaid and dongle  */
    //////
    /////
    /////

    @GetMapping("/payment")
    public String showPaymentForm(Model model) {
        Payment payment = new Payment();
        model.addAttribute("payment", payment);
        model.addAttribute("changetopostpaid","no");
        return "payment";
    }
    /*  Get Mapping in payment controller prepaid to post paid   */
    //////
    /////
    /////

    @GetMapping("/payments")
    public String showPaymentForPretoPost(Model model){
        model.addAttribute("payment",new Payment());
        model.addAttribute("changetopostpaid","yes");
        return "payment";
    }

    @GetMapping("/paymentdongle")
    public String showPaymentDongle(Model model){
        model.addAttribute("payment",new Payment());
        return "payment";
    }
    /*  Post Mapping in payment controller prepaid to post paid   */
    //////
    /////
    /////
    @PostMapping("/payments")
    public String processPaymentFor(@Valid Payment payment,
                                    Errors errors,
                                    @ModelAttribute SubscriptionDetail subscriptionDetail,
                                    Model model,
                                    @ModelAttribute Login login){
        if (errors.hasErrors()) {
            return "payment";
        }else{
            paymentRepository.save(payment);
            subscriptionDetail.setPayment(payment);
            subscriptionDetail.setUserId(login.getId());
            PlanDetail planDetail = new PlanDetail();
            planDetail.setUserId(subscriptionDetail.getUserId()); //user id set
            planDetail.setPlanId(subscriptionDetail.getPlandId()); //plan id set
            planDetail.setProductId(subscriptionDetail.getProductId()); // product id set
            planDetail.setStartDate(subscriptionDetail.getPayment().getPaymentDate()); // plan start date set
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(subscriptionDetail.getPayment().getPaymentDate());
            String validity;
            String data;
            String amountPaid;
            String planType;
            Optional<PostPaid> postPaid = postPaidRepository.findById(subscriptionDetail.getPlandId());
            validity = postPaid.get().getValidity();
            data = postPaid.get().getBenefits();
            amountPaid = postPaid.get().getPostMoney();
            planType = postPaid.get().getType();
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
            subscriptionDetailRepository.save(subscriptionDetail);
            Optional<UserCredential>  userCredential = userCredentialRepository.findById(login.getId());
            userCredential.get().setPostPaidPlan("postPaid");
            userCredential.get().setPrePaidPlan("null");
            Optional<PlanDetail> planDetail1 = planDetailRepository.findById(userCredential.get().getPrePaidPlanId());
            savePlanHistory(planDetail1.get());
            planDetailRepository.delete(planDetail1.get());
            userCredential.get().setPrePaidPlanId("null");
            userCredential.get().setPostPaidPlanNumber(userCredential.get().getPrePaidPlanNumber());
            userCredential.get().setPrePaidPlanNumber("null");
            planDetail.setGeneratedNumber(userCredential.get().getPostPaidPlanNumber());
            planDetailRepository.save(planDetail);
            userCredential.get().setPostPaidPlanId(planDetail.getId());
            userCredentialRepository.save(userCredential.get());
            model.addAttribute("paymentMade","yes");
            login.setPassword(planDetail.getId());
            model.addAttribute("login",login);
            //Mail is Sent
            SimpleMailMessage msg=new SimpleMailMessage();
            msg.setTo(userCredential.get().getEmailId());
            msg.setSubject("Acknowledgement from Voizfonica");
            msg.setText("Hi "+userCredential.get().getUserName()+",\n\n"+"\nYour number is changed from prepaid to postpaid successfully.\n"+
                    "\nMobile number:"+planDetail.getGeneratedNumber()+
                    "\n\n\nThanks and regards,\nTeam VoizFonica.");
            javaMail.send(msg);
            //Mail function ends here
            return "payment";


        }
    }

    /*  Post Mapping in payment controller for dongle product   */
    //////
    /////
    /////
    @PostMapping("/paymentdongle")
    public String processPaymentForDongle(@Valid Payment payment,
                                          Errors errors,
                                          @ModelAttribute SubscriptionDetail subscriptionDetail,
                                          Model model,
                                          @ModelAttribute Login login,
                                          @ModelAttribute DongleProduct dongleProduct){
        if (errors.hasErrors()) {
            return "payment";
        }else{

            paymentRepository.save(payment);
            subscriptionDetail.setPayment(payment);
            dongleProduct.setPayment(payment);
            dongleProduct.setUserId(login.getId());
            dongleProduct.setBoughtOn(payment.getPaymentDate());
            subscriptionDetail.setUserId(login.getId());
            PlanDetail planDetail = new PlanDetail();
            planDetail.setUserId(subscriptionDetail.getUserId()); //user id set
            planDetail.setPlanId(subscriptionDetail.getPlandId()); //plan id set
            planDetail.setProductId(subscriptionDetail.getProductId()); // product id set
            planDetail.setStartDate(subscriptionDetail.getPayment().getPaymentDate()); // plan start date set
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(subscriptionDetail.getPayment().getPaymentDate());
            String validity;
            String data;
            String amountPaid;
            String planType;
            Optional<Dongle> dongle = dongleRepository.findById(subscriptionDetail.getPlandId());
            validity = dongle.get().getValidity();
            data = dongle.get().getBenefits();
            amountPaid = dongle.get().getDongleMoney();
            planType = dongle.get().getType();
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
            subscriptionDetailRepository.save(subscriptionDetail);
            Optional<UserCredential>  userCredential = userCredentialRepository.findById(login.getId());
            userCredential.get().setDonglePlan("dongle");
            long mobileNumber = (long)((Math.random() * 100000000) + 7980000000L);
            String stringMobileNumber = Long.toString(mobileNumber);
            planDetail.setGeneratedNumber(stringMobileNumber);
            planDetail.setGeneratedNumber(stringMobileNumber);
            planDetailRepository.save(planDetail);
            userCredential.get().setDonglePlanId(planDetail.getId());
            userCredential.get().setDonglePlanNumber(stringMobileNumber);
            userCredentialRepository.save(userCredential.get());
            model.addAttribute("paymentMade","yes");
            login.setPassword(planDetail.getId());
            model.addAttribute("login",login);
            String totalprice = dongleProduct.getDonglePrice();
            char[] totalprices = totalprice.toCharArray();
            int totalnumber = totalprices[0] - '0';
            for(int i=1;i<totalprices.length;i++){
                if(Character.isDigit(totalprices[i])){
                    totalnumber=totalnumber*10;
                    totalnumber=totalnumber+totalprices[i]-'0';
                }
            }
            String totalpricees = planDetail.getAmountPaid();
            char[] totalpricesss = totalpricees.toCharArray();
            int totalnumbers = totalpricesss[0] - '0';
            for(int i=1;i<totalpricesss.length;i++){
                if(Character.isDigit(totalpricesss[i])){
                    totalnumbers=totalnumbers*10;
                    totalnumbers=totalnumbers+totalpricesss[i]-'0';
                }
            }
            model.addAttribute("paymentMade","yes");
            totalnumber = totalnumber + totalnumbers;
            String tol = Integer.toString(totalnumber);
            dongleProduct.setTotalprice(totalnumber);
            dongleProductRepository.save(dongleProduct);
            login.setEmailId(dongleProduct.getId());
            model.addAttribute("login",login);
            return "payment";


        }
    }


    /*  Post Mapping in payment controller for prepaid , postpaid and dongle  */
    //////
    /////
    /////
    @PostMapping("/payment")
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
                userCredential.get().setPrePaidPlanNumber(planDetail.getGeneratedNumber());
                userCredentialRepository.save(userCredential.get());
            }else if(productId.equals("postPaid")){
                userCredential.get().setPostPaidPlanId(planDetail.getId());
                userCredential.get().setPostPaidPlanNumber(planDetail.getGeneratedNumber());
                userCredentialRepository.save(userCredential.get());
            }else{
                userCredential.get().setDonglePlanId(planDetail.getId());
                userCredential.get().setDonglePlanNumber(planDetail.getGeneratedNumber());
                userCredentialRepository.save(userCredential.get());
            }
            model.addAttribute("paymentMade","yes");
            login.setPassword(planDetail.getId());
            model.addAttribute("login",login);
            return "payment";
            //return "redirect:/success";
        }
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
