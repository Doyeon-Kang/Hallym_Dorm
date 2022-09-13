package com.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.TestUser;
import com.backend.repository.TestUserRepository;

@RestController
@RequestMapping(path="/api")
public class TestUserController {
    @Autowired
    TestUserRepository testUserRepository;

    @GetMapping(path="/add")
    public @ResponseBody String getAllTestUsers() {
        TestUser testUser1 = new TestUser(1, "first");
        TestUser testUser2 = new TestUser(2, "Second");
        testUserRepository.save(testUser1);
        testUserRepository.save(testUser2);
        return "Success";
    }

    @GetMapping(path="/all")
    public @ResponseBody String getAllUsers() {
      // This returns a JSON or XML with the users
      return testUserRepository.findAll().toString();
    }
}
