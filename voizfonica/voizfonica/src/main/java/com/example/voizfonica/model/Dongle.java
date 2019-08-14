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
    private  String dongleValidity;
    private  String dongleBenefits;
    private  String type;

    public String getId() {
        return id;
    }

    public String getDongleMoney() {
        return dongleMoney;
    }

    public String getValidity() {
        return dongleValidity;
    }

    public String getBenefits() {
        return dongleBenefits;
    }

    public String getType() {
        return type;
    }

    public Dongle(String dongleMoney, String dongleValidity, String dongleBenefits, String type) {
        this.dongleMoney = dongleMoney;
        this.dongleValidity = dongleValidity;
        this.dongleBenefits = dongleBenefits;
        this.type = type;
    }
}

