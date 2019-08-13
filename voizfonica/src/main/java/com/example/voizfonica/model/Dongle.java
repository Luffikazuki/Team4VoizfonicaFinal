package com.example.voizfonica.model;



import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
public class Dongle {

    @Id
    private  String id;

    private  String dongleMoney;
    private  String validity;
    private  String benefits;
    private  String type;

    public String getId() {
        return id;
    }

    public String getDongleMoney() {
        return dongleMoney;
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

    public Dongle(String dongleMoney, String validity, String benefits, String type) {
        this.dongleMoney = dongleMoney;
        this.validity = validity;
        this.benefits = benefits;
        this.type = type;
    }
}

