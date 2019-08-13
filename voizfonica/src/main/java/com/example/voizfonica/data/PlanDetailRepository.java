package com.example.voizfonica.data;

import com.example.voizfonica.model.PlanDetail;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PlanDetailRepository extends MongoRepository<PlanDetail,String> {
    List<PlanDetail> findByUserId(String userId);
}
