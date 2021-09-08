package com.example.security_gateway.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.security_gateway.model.User;

public interface UserDetailsRepo extends MongoRepository<User, String> {

}
