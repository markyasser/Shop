package com.testing.test_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.testing.test_app.model.Post;
import com.testing.test_app.repository.Posts.PostRepository;
import com.testing.test_app.repository.Posts.SearchRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/feed")
public class feed {

    @Autowired
    PostRepository repo;

    @Autowired
    SearchRepository searchRepo;

    @PostMapping("/post")
    public Post addPost(@RequestBody Post post) {
        return repo.save(post);
    }

    @GetMapping("/posts")
    public List<Post> getPosts() {
        return repo.findAll();
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<?> deletePost(@PathVariable String id) {
        repo.deleteById(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "success");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/posts/{text}")
    public List<Post> searchPosts(@PathVariable String text) {
        return searchRepo.findByText(text);
    }

}
