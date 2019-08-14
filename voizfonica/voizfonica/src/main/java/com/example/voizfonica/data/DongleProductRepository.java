package com.example.voizfonica.data;

import com.example.voizfonica.model.DongleProduct;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DongleProductRepository extends MongoRepository<DongleProduct,String> {
}
