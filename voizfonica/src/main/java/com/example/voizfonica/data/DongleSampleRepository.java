package com.example.voizfonica.data;

import com.example.voizfonica.model.DongleSample;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DongleSampleRepository extends MongoRepository<DongleSample,String> {
}
