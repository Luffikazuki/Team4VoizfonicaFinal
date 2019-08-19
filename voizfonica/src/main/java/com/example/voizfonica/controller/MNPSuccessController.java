package com.example.voizfonica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


@Controller
public class MNPSuccessController {

    @GetMapping("MNPSuccess")
    public String mnpsuccessfunc()
    {

        return "MNPSuccess";
    }

}
