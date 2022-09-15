package com.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.backend.model.TestUser;
import com.backend.repository.TestUserRepository;

@RestController
@RequestMapping(path="/api")
public class TestUserController {

    @Autowired
    TestUserRepository testUserRepository;

    @GetMapping(path="/add")
    public @ResponseBody String getAllTestUsers() {
        TestUser testUser1 = new TestUser("20198011", 1);
        TestUser testUser2 = new TestUser("20198012", 2);
        testUserRepository.save(testUser1);
        testUserRepository.save(testUser2);
        return "Users added";
    }

    @GetMapping(path="/all")
    public @ResponseBody String getAllUsers() {
      // This returns a JSON or XML with the users
      return testUserRepository.findAll().toString();
    }
}
