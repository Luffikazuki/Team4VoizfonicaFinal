
package com.example.voizfonica.model;

import java.lang.String;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class PrePaid {
    @Id
    private String id;
    private final String preMoney;
    private final String validity;
    private final String benefits;
    private final String type;

    public String getId() {
        return id;
    }

    public String getPreMoney() {
        return preMoney;
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

    public PrePaid(String preMoney, String validity, String benefits, String type) {
        this.preMoney = preMoney;
        this.validity = validity;
        this.benefits = benefits;
        this.type = type;
    }


}

