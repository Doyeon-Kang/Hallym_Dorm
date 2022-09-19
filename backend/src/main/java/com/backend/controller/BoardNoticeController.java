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

import com.backend.model.Boardnotice;
import com.backend.repository.BoardNoticeRepository;

@RestController
@RequestMapping(path="/api")
public class BoardNoticeController {

    @Autowired
    BoardNoticeRepository BoardNoticeRepository;

    @GetMapping(path="/add")
    public @ResponseBody String getAllBoardnotice() {
        Boardnotice testUser1 = new Boardnotice("aa", "bb");
        Boardnotice testUser2 = new Boardnotice("cc", "dd");
        BoardNoticeRepository.save(testUser1);
        BoardNoticeRepository.save(testUser2);
        return "Users added";
    }

    @GetMapping(path="/all")
    public @ResponseBody String getAllUsers() {
      // This returns a JSON or XML with the users
      return BoardNoticeRepository.findAll().toString();
    }
}
