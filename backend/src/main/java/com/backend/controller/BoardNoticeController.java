package com.backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.BoardNotice;
import com.backend.repository.BoardNoticeRepository;

@RestController
@RequestMapping(path="/api")
public class BoardNoticeController {

    @Autowired
    BoardNoticeRepository boardNoticeRepository;

    @GetMapping(path="/notices")
    public ResponseEntity<List<BoardNotice>> getAllBoardNotice(@RequestParam(required = false) String title) {
        try {
          List<BoardNotice> boardNotices = new ArrayList<BoardNotice>();

          if(title == null)
            boardNoticeRepository.findAll().forEach(boardNotices::add);
          else 
            boardNoticeRepository.findByTitleContaining(title).forEach(boardNotices::add);

          if(boardNotices.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          }

          return new ResponseEntity<>(boardNotices, HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/notices/{id}")
    public ResponseEntity<BoardNotice> getBoardNoticeById(@PathVariable("id") long id) {
      Optional<BoardNotice> noticeData = boardNoticeRepository.findById(id);

      if(noticeData.isPresent()) {
        return new ResponseEntity<>(noticeData.get(), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @PostMapping("/notices")
    public ResponseEntity<BoardNotice> createTutorial(@RequestBody BoardNotice boardNotice) {
      try {
        BoardNotice _boardNotice = boardNoticeRepository
                    .save(new BoardNotice(boardNotice.getWriter_id(), boardNotice.getTitle(), boardNotice.getContents()));
        return new ResponseEntity<>(_boardNotice, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
}
