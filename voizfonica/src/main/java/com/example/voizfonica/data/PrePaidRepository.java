package com.example.voizfonica.data;

import com.example.voizfonica.model.PrePaid;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public interface PrePaidRepository extends MongoRepository<PrePaid,String> {
    List<PrePaid> findByType(String type);
    Optional<PrePaid> findById(String id);
}
