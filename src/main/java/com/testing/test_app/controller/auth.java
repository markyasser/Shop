package com.testing.test_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.testing.test_app.model.User;
import com.testing.test_app.repository.UserRepository;

@RestController
@RequestMapping("/auth")
public class auth {

    @Autowired
    UserRepository repo;

    @PostMapping("/signup")
    public User addUser(@RequestBody User user) {
        return repo.save(user);
    }
}
