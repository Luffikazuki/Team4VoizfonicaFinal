package com.example.blog.Data;

import com.example.blog.Model.Create;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepo extends MongoRepository<Create,String> {
    List<Create> findByName(String name);
    Create findAllByName(String name);
    Create findAllById(String t);
    Create findAllByNameAndId(String s, String t);
    void deleteByName(String s);
    void deleteByNameAndId(String t, String s);
}
