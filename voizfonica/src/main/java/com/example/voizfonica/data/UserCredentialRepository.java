package com.example.voizfonica.data;

import com.example.voizfonica.model.UserCredential;
import org.apache.catalina.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserCredentialRepository extends MongoRepository<UserCredential,String> {


    List<UserCredential> findByEmailIdAndPassword(String emailId, String password);
    List<UserCredential> findByEmailId(String emailId);


}
