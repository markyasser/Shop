package com.testing.test_app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.testing.test_app.model.User;
import com.testing.test_app.repository.Users.UserRepository;
import com.testing.test_app.repository.Users.UserSearchRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/auth")
public class auth {

    @Autowired
    UserRepository repo;

    @Autowired
    UserSearchRepository searchRepo;

    @PostMapping("/signup")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        User existUser = searchRepo.findByName(user.getName()); // find user by name
        if (existUser == null) { // check if the username already exists
            String token = Jwts.builder()
                    .setSubject(user.getName())
                    .signWith(SignatureAlgorithm.HS256, "thisIsASecretKey")
                    .compact();
            user.setToken(token);
            repo.save(user);
            return ResponseEntity.ok(user);
        }
        Map<String, String> response = new HashMap<>();
        response.put("message", "username already exists");
        return ResponseEntity.status(422).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String name = body.get("name"); // get name from request body
        String plainPassword = body.get("password"); // get password from request body
        User user = searchRepo.findByName(name); // find user by name
        if (user == null) { // if user not found
            Map<String, String> response = new HashMap<>();
            response.put("message", "username not found");
            return ResponseEntity.status(422).body(response);
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(plainPassword, user.getPassword())) { // check if password matches
            Map<String, String> response = new HashMap<>();
            response.put("message", "invalid password");
            return ResponseEntity.status(422).body(response);
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserById(@RequestBody String id) {
        User user = repo.findById(id).orElse(null); // find user by id
        if (user == null) { // if user not found
            Map<String, String> response = new HashMap<>();
            response.put("message", "username not found");
            return ResponseEntity.status(422).body(response);
        }
        return ResponseEntity.ok(user);
    }
}
