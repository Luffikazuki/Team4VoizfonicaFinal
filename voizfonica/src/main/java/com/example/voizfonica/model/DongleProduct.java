package com.example.voizfonica.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Document
public class DongleProduct {
    @Id
    private String id;

    @NotNull
    private String dongleName;

    @NotNull
    private Date boughtOn;

    @NotNull
    private String donglePrice;

    @NotNull
    private Payment payment;

    @NotNull
    private String userId;

    private int totalprice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDongleName() {
        return dongleName;
    }

    public void setDongleName(String dongleName) {
        this.dongleName = dongleName;
    }

    public Date getBoughtOn() {
        return boughtOn;
    }

    public void setBoughtOn(Date boughtOn) {
        this.boughtOn = boughtOn;
    }

    public String getDonglePrice() {
        return donglePrice;
    }

    public void setDonglePrice(String donglePrice) {
        this.donglePrice = donglePrice;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }
}
