package com.testing.test_app.repository.Posts;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.testing.test_app.model.Post;

@Component
public class SeachRepositoryImpl implements SearchRepository {

        @Autowired
        MongoClient mongoClient;

        @Autowired
        MongoConverter mongoConverter;

        @Override
        public List<Post> findByText(String text) {
                final List<Post> posts = new ArrayList<>();
                MongoDatabase database = mongoClient.getDatabase("test");
                MongoCollection<Document> collection = database.getCollection("Post");
                AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                                new Document("$search",
                                                new Document("text",
                                                                new Document("query", text)
                                                                                .append("path", "techs"))),
                                new Document("$sort",
                                                new Document("exp", 1L)),
                                new Document("$limit", 5L)));
                result.forEach(doc -> {
                        posts.add(mongoConverter.read(Post.class, doc));
                });
                return posts;
        }

}
