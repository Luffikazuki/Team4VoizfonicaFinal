package com.example.blog.Model;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Document
@RequiredArgsConstructor
public class Register1{

    @Id
    private String _id;


    @NotBlank
    @Size(min = 2,message = "Atleast 2 characters")
    private String name;

    
    @NotBlank
    @Size(min = 3,message = "Invalid email")
    private String email_address;

    @NotBlank
    @Size(min = 5,message = "Must be minimum 5 characters")
    private String password;

    @NotBlank
    @Size(min = 5,message = "Must be minimum 5 characters")
    private String confirmpassword;



}
