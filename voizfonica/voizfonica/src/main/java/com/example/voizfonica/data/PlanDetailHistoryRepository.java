package com.example.voizfonica.data;

import com.example.voizfonica.model.PlanDetailHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlanDetailHistoryRepository  extends MongoRepository<PlanDetailHistory,String> {
}
