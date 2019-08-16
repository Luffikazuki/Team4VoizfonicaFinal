package com.example.voizfonica.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Document
public class SubscriptionDetail {

    @Id
    private String id;

    @NotNull
    private String userId;

    @NotNull
    private String productId;

    @NotNull
    private String plandId;

    @NotNull
    private Payment payment;

    @NotNull
    private String planPrice;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPlandId() {
        return plandId;
    }

    public void setPlandId(String plandId) {
        this.plandId = plandId;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Payment getPayment(){
        return payment;
    }

    public String getPlanPrice() {
        return planPrice;
    }

    public void setPlanPrice(String planPrice) {
        this.planPrice = planPrice;
    }
}
