package com.example.voizfonica.data;

import com.example.voizfonica.model.SubscriptionDetail;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SubscriptionDetailRepository extends MongoRepository<SubscriptionDetail,String> {
    List<SubscriptionDetail> findSubscriptionDetailByUserId(String userId);
}
