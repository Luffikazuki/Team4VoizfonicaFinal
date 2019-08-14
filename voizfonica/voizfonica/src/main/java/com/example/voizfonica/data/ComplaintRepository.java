package com.example.voizfonica.data;


import com.example.voizfonica.model.Complaint;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ComplaintRepository extends MongoRepository<Complaint,String> {
}

