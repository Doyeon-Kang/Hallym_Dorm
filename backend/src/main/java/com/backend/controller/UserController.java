package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.User;
import com.backend.repository.UserRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(path="/api")
public class UserController {
    @Autowired
    UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(path="/users")
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @PostMapping(path="/users")
    public User newUser(@RequestBody User user) {
      return userRepository.save(user);
    }

    @GetMapping("/users/{id}")
    public User findOne(@PathVariable("id") User user) {
      return user;
    }

    @PutMapping("/users/{id}")
    User replaceTestUser(@RequestBody User newUser, @PathVariable Long id) {
      
      return userRepository.findById(id)
        .map(user -> {
          user.setUsername(newUser.getUsername());
          user.setName(newUser.getName());
          user.setPassword(newUser.getPassword());
          user.setEmail(newUser.getEmail());
          return userRepository.save(user);
        })
        .orElseGet(() -> {
          newUser.setId(id);
          return userRepository.save(newUser);
        });
    }

    @DeleteMapping("/users/{id}")
    void deleteEmployee(@PathVariable Long id) {
      userRepository.deleteById(id);
    }
}
