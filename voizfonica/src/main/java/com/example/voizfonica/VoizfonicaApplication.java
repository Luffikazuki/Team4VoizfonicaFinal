package com.example.voizfonica;

import com.example.voizfonica.data.DongleRepository;
import com.example.voizfonica.data.PostPaidRepository;
import com.example.voizfonica.model.Dongle;
import com.example.voizfonica.model.PostPaid;
import com.example.voizfonica.data.PrePaidRepository;
import com.example.voizfonica.model.PrePaid;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;


@SpringBootApplication
public class VoizfonicaApplication {

    public static void main(String[] args) {
        SpringApplication.run(VoizfonicaApplication.class, args);
    }

    //Postpaid Database
//    @Bean
//    public CommandLineRunner postLoader(PostPaidRepository postRepo) {
//        postRepo.deleteAll();
//        return args -> {
//            postRepo.save(new PostPaid("199INR","30 Days","100 GB", "3G"));
//            postRepo.save(new PostPaid("599INR","30 Days","250 GB","3G"));
//            postRepo.save(new PostPaid("899INR","365 Days","500 GB","3G"));
//            postRepo.save(new PostPaid("1500INR","30 Days","205 GB","4G"));
//            postRepo.save(new PostPaid("3300INR","30 Days","505 GB","4G"));
//            postRepo.save(new PostPaid("5500INR","365 Days","1001 GB","4G"));
//
//        };
//    }


    //Dongle Database
    @Bean
    public CommandLineRunner dongleLoader(DongleRepository dongleRepo) {
        dongleRepo.deleteAll();
        return args -> {
            dongleRepo.save(new Dongle("199INR", "28 Days", "10 GB", "3G"));
            dongleRepo.save(new Dongle("250INR", "28 Days", "25 GB", "3G"));
            dongleRepo.save(new Dongle("500INR", "48 Days", "100 GB", "3G"));
            dongleRepo.save(new Dongle("1250INR", "96 Days", "250 GB", "3G"));
            dongleRepo.save(new Dongle("99INR", "28 Days", "205 GB", "4G"));
            dongleRepo.save(new Dongle("299INR", "28 Days", "1000 GB", "4G"));
            dongleRepo.save(new Dongle("505INR", "48 Days", "205 GB", "4G"));
            dongleRepo.save(new Dongle("1520INR", "96 Days", "1000 GB", "4G"));

        };
    }


    //Prepaid Database
//    @Bean
//    public CommandLineRunner planLoader(PrePaidRepository repo) {
//        repo.deleteAll();
//        return args -> {
//            repo.save(new PrePaid("1500 INR","365 Days","547.5 GB", "3G"));
//            repo.save(new PrePaid("400 INR","84 Days","126 GB", "3G"));
//            repo.save(new PrePaid("28 INR","28 Days","42 GB" , "3G"));
//
//            repo.save(new PrePaid("198 INR","28 Days","56 GB", "4G"));
//            repo.save(new PrePaid("400 INR","78 Days","140 GB", "4G"));
//            repo.save(new PrePaid("448 INR","84 Days","168 GB", "4G"));
//        };
//    }

    }

