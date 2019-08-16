package com.example.voizfonica.model;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Document
public class Payment {
    @Id
    private String id;
    @NotNull
    @Size(min = 8, message = "☜ Invalid name")
    private String name;
    @CreditCardNumber(message="☜ Invalid credit card number")
    private String ccNum;
    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$", message="☜ Must be formatted MM/YY")
    private String ccExpiration;
    @Pattern(regexp = "^([0-9]{3}$)", message = "☜ Invalid CVV")
    private String ccCVV;

    private Date paymentDate = new Date();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCcNum() {
        return ccNum;
    }

    public void setCcNum(String ccNum) {
        this.ccNum = ccNum;
    }

    public String getCcExpiration() {
        return ccExpiration;
    }

    public void setCcExpiration(String ccExpiration) {
        this.ccExpiration = ccExpiration;
    }

    public String getCcCVV() {
        return ccCVV;
    }

    public void setCcCVV(String ccCVV) {
        this.ccCVV = ccCVV;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }


}

