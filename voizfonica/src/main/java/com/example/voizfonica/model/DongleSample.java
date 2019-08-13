package com.example.voizfonica.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotNull;

@Data
public class DongleSample {
    @Id
    private String id;

    @NotNull
    private String donglePlanName;

    @NotNull
    private String dongleValidity;

    @NotNull
    private String dongleData;

    @NotNull
    private String donglePlanPrice;

    public String getId() {
        return id;
    }

    public String getDonglePlanName() {
        return donglePlanName;
    }

    public String getDongleValidity() {
        return dongleValidity;
    }

    public String getDongleData() {
        return dongleData;
    }

    public String getDonglePlanPrice() {
        return donglePlanPrice;
    }
}
