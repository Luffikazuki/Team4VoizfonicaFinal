package com.example.voizfonica;

import com.example.voizfonica.data.DongleRepository;
import com.example.voizfonica.model.Dongle;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VoizfonicaApplication {

    public static void main(String[] args) {
        SpringApplication.run(VoizfonicaApplication.class, args);
    }



}
