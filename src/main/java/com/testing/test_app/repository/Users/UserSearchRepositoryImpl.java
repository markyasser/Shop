package com.testing.test_app.repository.Users;

import com.testing.test_app.model.User;

import java.util.Arrays;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Component
public class UserSearchRepositoryImpl implements UserSearchRepository {

        @Autowired
        MongoClient mongoClient;

        @Autowired
        MongoConverter mongoConverter;

        @Override
        public User findByName(String name) {
                MongoDatabase database = mongoClient.getDatabase("test");
                MongoCollection<Document> collection = database.getCollection("users");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                                new Document("index", "users")
                                                .append("text",
                                                                new Document("query", name)
                                                                                .append("path", "name"))),
                                new Document("$limit", 1L)));
                if (result.first() == null) {
                        return null;
                }
                User user = mongoConverter.read(User.class, result.first());
                return user;
        }
}
