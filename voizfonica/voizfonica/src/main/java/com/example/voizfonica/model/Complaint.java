package com.example.voizfonica.model;


import lombok.Data;

import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.Id;


@Data
@Document
public class Complaint {

    @Id
    private static String id;

    @NotNull(message="Please select one")
    private  String queryType;

    @NotBlank(message="Enter Complaint to submit")
    @Size(max=500)
    private   String queryByUser;

    private  Integer ticket=100;

    public Integer getTicket() {
        return ticket;
    }

    public void setTicket(Integer ticket) {
        this.ticket = ticket;
    }
}

