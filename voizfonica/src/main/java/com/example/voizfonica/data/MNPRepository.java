package com.example.voizfonica.data;

import com.example.voizfonica.model.MNPDomain;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MNPRepository extends MongoRepository<MNPDomain,String> {

}
