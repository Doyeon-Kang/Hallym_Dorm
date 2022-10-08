package com.backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.BoardNotice;
import com.backend.repository.BoardNoticeRepository;

@RestController
@RequestMapping(path="/api")
public class BoardNoticeController {
    @Autowired
    BoardNoticeRepository boardNoticeRepository;

    @GetMapping(path="/board-notice")
    public ResponseEntity<List<BoardNotice>> getAllBoardNotice() {
        try {
          List<BoardNotice> boardNotices = new ArrayList<BoardNotice>();

          boardNoticeRepository.findAll().forEach(boardNotices::add);
          if(boardNotices.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          }

          return new ResponseEntity<>(boardNotices, HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/board-notice/{id}")
    public ResponseEntity<BoardNotice> getBoardNoticeById(@PathVariable("id") long id) {
      Optional<BoardNotice> noticeData = boardNoticeRepository.findById(id);

      if(noticeData.isPresent()) {
        BoardNotice _boardNotice = noticeData.get();
        int views = _boardNotice.getViews() + 1;
        _boardNotice.setViews(views);
        return new ResponseEntity<>(boardNoticeRepository.save(_boardNotice), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @PostMapping("/board-notice")
    public ResponseEntity<BoardNotice> createBoardNotice(@RequestBody BoardNotice boardNotice) {
      try {
        BoardNotice _boardNotice = boardNoticeRepository
                    .save(new BoardNotice(boardNotice.getWriter_studentno(), boardNotice.getWriter_name(), boardNotice.getTitle(), boardNotice.getContent()));
        return new ResponseEntity<>(_boardNotice, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    @PutMapping("/board-notice/{id}")
    public ResponseEntity<BoardNotice> updateBoardNotice(@PathVariable("id") long id, @RequestBody BoardNotice boardNotice) {
      Optional<BoardNotice> noticeData = boardNoticeRepository.findById(id);

      if (noticeData.isPresent()) {
        BoardNotice _boardNotice = noticeData.get();
        _boardNotice.setWriter_studentno(boardNotice.getWriter_studentno());
        _boardNotice.setWriter_name(boardNotice.getWriter_name());
        _boardNotice.setTitle(boardNotice.getTitle());
        _boardNotice.setContent(boardNotice.getContent());
        return new ResponseEntity<>(boardNoticeRepository.save(_boardNotice), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @DeleteMapping("/board-notice/{id}")
    public ResponseEntity<HttpStatus> deleteBoardNotice(@PathVariable("id") long id) {
      try {
        boardNoticeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @DeleteMapping("/board-notice")
    public ResponseEntity<HttpStatus> deleteAllBoardNotices() {
      try {
        boardNoticeRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }

    }


}
