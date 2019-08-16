package com.example.voizfonica.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;

@Data
@Document
public class UserCredential {

    @Id
    private String id;

    @NotNull
    @Size(min=5, message = "Name must of atleast of length 5")
    private String userName;

    @NotEmpty
    @Size(min=8,max=25,message = "Must have a minimum of 8 characters")
    private String password;

    @Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\.\\-]+)\\.([a-zA-Z]{2,5})$",
            message = "Provide a valid email id")
    private String emailId;

    @Pattern(regexp = "^([0-9]){12}$", message = "Please provide a valid aadhar Number")
    private String aadharNumber;

    @NotNull
    @Pattern(regexp = "^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}?$", message = "Please provide a valid pan number")
    private String panNumber;


    private String prePaidPlan;
    private String prePaidPlanId;
    private String prePaidPlanNumber;
    private String postPaidPlan;
    private String postPaidPlanId;
    private String postPaidPlanNumber;
    private String donglePlan;
    private String donglePlanId;
    private String donglePlanNumber;

    @NotEmpty
    @Length(max = 100,min = 20, message = "Plese provide your address")
    private String address;


    @Pattern(regexp = "^([1-9]){1}([0-9]){9}$", message = "Please provide a valid contact number")
    private String contactNumber;

    @NotNull(message = "Please select a question!")
    private String securityQuestion;

    @NotNull(message = "Please provide a valid answer")
    private String securityAnswer;

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }


    public String getUserName() {
        return userName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getPrePaidPlan() {
        return prePaidPlan;
    }

    public void setPrePaidPlan(String prePaidPlan) {
        this.prePaidPlan = prePaidPlan;
    }

    public String getPostPaidPlan() {
        return postPaidPlan;
    }

    public void setPostPaidPlan(String postPaidPlan) {
        this.postPaidPlan = postPaidPlan;
    }

    public String getDonglePlan() {
        return donglePlan;
    }

    public void setDonglePlan(String donglePlan) {
        this.donglePlan = donglePlan;
    }

    public String getPrePaidPlanId() {
        return prePaidPlanId;
    }

    public void setPrePaidPlanId(String prePaidPlanId) {
        this.prePaidPlanId = prePaidPlanId;
    }

    public String getPostPaidPlanId() {
        return postPaidPlanId;
    }

    public void setPostPaidPlanId(String postPaidPlanId) {
        this.postPaidPlanId = postPaidPlanId;
    }

    public String getDonglePlanId() {
        return donglePlanId;
    }

    public void setDonglePlanId(String donglePlanId) {
        this.donglePlanId = donglePlanId;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public String getPrePaidPlanNumber() {
        return prePaidPlanNumber;
    }

    public void setPrePaidPlanNumber(String prePaidPlanNumber) {
        this.prePaidPlanNumber = prePaidPlanNumber;
    }

    public String getPostPaidPlanNumber() {
        return postPaidPlanNumber;
    }

    public void setPostPaidPlanNumber(String postPaidPlanNumber) {
        this.postPaidPlanNumber = postPaidPlanNumber;
    }

    public String getDonglePlanNumber() {
        return donglePlanNumber;
    }

    public void setDonglePlanNumber(String donglePlanNumber) {
        this.donglePlanNumber = donglePlanNumber;
    }
}

