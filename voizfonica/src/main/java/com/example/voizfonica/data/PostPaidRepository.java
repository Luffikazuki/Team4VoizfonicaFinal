package com.example.voizfonica.data;

import com.example.voizfonica.model.PostPaid;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostPaidRepository extends MongoRepository<PostPaid,String> {
    List<PostPaid> findByType(String type);

}
