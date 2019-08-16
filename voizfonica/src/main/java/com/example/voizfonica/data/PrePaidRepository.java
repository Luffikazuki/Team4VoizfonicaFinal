//PrePaidRepository.java

package com.example.voizfonica.data;

import com.example.voizfonica.model.PrePaid;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PrePaidRepository extends MongoRepository<PrePaid,String> {
    List<PrePaid> findByType(String type);
}
