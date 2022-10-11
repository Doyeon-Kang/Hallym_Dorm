package com.backend.controller;

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

import com.backend.model.BoardLost;
import com.backend.model.BoardLostComment;
import com.backend.repository.BoardLostCommentRepository;
import com.backend.repository.BoardLostRepository;

@RestController
@RequestMapping(path="/api")
public class BoardLostCommentController {
    @Autowired
    BoardLostRepository boardLostRepository;
    @Autowired
    BoardLostCommentRepository boardLostCommentRepository;

    @GetMapping(path="/board-lost/{lostId}/comments")
    public ResponseEntity<List<BoardLostComment>> getAllBoardLostCommentsByLostId(@PathVariable(name="lostId") Long lostId) {
        try {
          List<BoardLostComment> lostComments = boardLostCommentRepository.findByBoardLostId(lostId);
          return new ResponseEntity<>(lostComments, HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/lost-comments/{id}")
    public ResponseEntity<BoardLostComment> getCommentById(@PathVariable(value = "id") Long id) {
      Optional<BoardLostComment> lostComment = boardLostCommentRepository.findById(id);
      if(lostComment.isPresent()) {
        BoardLostComment _boardLostComment = lostComment.get();
        return new ResponseEntity<BoardLostComment>(_boardLostComment, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @PostMapping("/board-lost/{lostId}/comments")
    public ResponseEntity<BoardLostComment> createBoardLostComment(@PathVariable(name="lostId") Long lostId, @RequestBody BoardLostComment boardLostCommentRequest) {
      try {
        BoardLost _boardLost = boardLostRepository.findById(lostId).get();
        BoardLostComment _boardLostComment = new BoardLostComment(boardLostCommentRequest.getWriter_studentno(), boardLostCommentRequest.getWriter_name(),
                                                                        boardLostCommentRequest.getContent());
        _boardLostComment.setBoardLost(_boardLost);
        boardLostCommentRepository.save(_boardLostComment);
        return new ResponseEntity<>(_boardLostComment, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    @PutMapping("/lost-comments/{id}")
    public ResponseEntity<BoardLostComment> updateBoardLostComment(@PathVariable(name="id") Long id, @RequestBody BoardLostComment boardLostComment) {
      Optional<BoardLostComment> lostCommentData = boardLostCommentRepository.findById(id);

      if (lostCommentData.isPresent()) {
        BoardLostComment _boardLostComment = lostCommentData.get();
        _boardLostComment.setContent(boardLostComment.getContent());
        boardLostCommentRepository.save(_boardLostComment);
        return new ResponseEntity<>(_boardLostComment, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @DeleteMapping("/lost-comments/{id}")
    public ResponseEntity<HttpStatus> deleteBoardLostComment(@PathVariable(name="id") long id) {
      try {
        boardLostCommentRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @DeleteMapping("/board-lost/{lostId}/comments")
    public ResponseEntity<HttpStatus> deleteAllBoardLostComments(@PathVariable(name="lostId") long lostId) {
      try {
        boardLostCommentRepository.deleteByBoardLostId(lostId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }

    }


}
