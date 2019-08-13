package com.example.voizfonica.data;

import com.example.voizfonica.model.SubscriptionDetail;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubscriptionDetailRepository extends MongoRepository<SubscriptionDetail,String> {
}
