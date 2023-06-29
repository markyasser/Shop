package com.testing.test_app.repository.Users;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.testing.test_app.model.User;

public interface UserRepository extends MongoRepository<User, String> {
}