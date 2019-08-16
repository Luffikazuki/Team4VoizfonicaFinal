package com.example.voizfonica.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Data
public class Login {

    @Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\.\\-]+)\\.([a-zA-Z]{2,5})$", message = "Enter a valid email id")
    private String emailId;

    @NotEmpty(message = "Provide password")
    @Size(min=8,max=25 , message = "Password must be of length at least 8 character")
    private String password;

    private String userName;

    private String id;

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getUserName(){
        return userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
