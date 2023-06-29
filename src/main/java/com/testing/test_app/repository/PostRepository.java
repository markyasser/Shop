package com.testing.test_app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.testing.test_app.model.Post;

public interface PostRepository extends MongoRepository<Post, String> {
}