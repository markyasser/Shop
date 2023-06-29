package com.testing.test_app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.testing.test_app.model.User;

public interface UserRepository extends MongoRepository<User, String> {
}