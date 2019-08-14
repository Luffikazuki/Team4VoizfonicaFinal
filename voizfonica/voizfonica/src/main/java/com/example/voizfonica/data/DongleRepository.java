package com.example.voizfonica.data;


import com.example.voizfonica.model.Dongle;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DongleRepository extends MongoRepository<Dongle, String> {
    List<Dongle> findByType(String type);
}
