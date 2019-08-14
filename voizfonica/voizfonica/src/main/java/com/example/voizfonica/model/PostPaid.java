package com.example.voizfonica.model;



import java.lang.String;
import lombok.Data;


import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Data

@Document
public class PostPaid {
    @Id
    private String id;

    private final String postMoney;
    private final String validity;
    private final String benefits;
    private final String type;

    public String getId() {
        return id;
    }

    public String getPostMoney() {
        return postMoney;
    }

    public String getValidity() {
        return validity;
    }

    public String getBenefits() {
        return benefits;
    }

    public String getType() {
        return type;
    }

    public PostPaid(String postMoney, String validity, String benefits, String type) {
        this.postMoney = postMoney;
        this.validity = validity;
        this.benefits = benefits;
        this.type = type;
    }
}
