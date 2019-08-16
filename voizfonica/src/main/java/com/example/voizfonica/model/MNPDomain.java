package com.example.voizfonica.model;



import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Document
@Data
public class MNPDomain {
    @Id
    private String id;

    @NotBlank
    private String mobNumber;

    @NotBlank (message = "SIM Number can't be empty")
    private String simNumber;

    @NotBlank (message = "Port Key can't be empty")
    @Size (max=10)
    private String portKey;

}