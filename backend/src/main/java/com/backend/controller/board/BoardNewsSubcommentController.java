package com.backend.controller.board;

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

import com.backend.model.board.BoardNewsComment;
import com.backend.model.board.BoardNewsSubcomment;
import com.backend.repository.board.BoardNewsCommentRepository;
import com.backend.repository.board.BoardNewsSubcommentRepository;

@RestController
@RequestMapping(path="/api")
public class BoardNewsSubcommentController {
    @Autowired
    BoardNewsCommentRepository boardNewsCommentRepository;
    @Autowired
    BoardNewsSubcommentRepository boardNewsSubcommentRepository;

    @GetMapping(path="/board-news/{newsId}/comments/{commentId}/subcomments")
    public ResponseEntity<List<BoardNewsSubcomment>> getAllBoardNewsSubcommentsByStoreCommentId(@PathVariable(name="commentId") Long commentId) {
        try {
          List<BoardNewsSubcomment> subComments = boardNewsSubcommentRepository.findByBoardNewsCommentId(commentId);
          return new ResponseEntity<>(subComments, HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/news-subcomments/{id}")
    public ResponseEntity<BoardNewsSubcomment> getSubcommentById(@PathVariable(value = "id") Long id) {
      Optional<BoardNewsSubcomment> newsSubcomment = boardNewsSubcommentRepository.findById(id);
      if(newsSubcomment.isPresent()) {
        BoardNewsSubcomment _newsSubcomment = newsSubcomment.get();
        return new ResponseEntity<BoardNewsSubcomment>(_newsSubcomment, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @PostMapping("/board-news/{newsId}/comments/{commentId}/subcomments")
    public ResponseEntity<BoardNewsSubcomment> createBoardNewsSubcomment(@PathVariable(name="newsId") Long newsId, @PathVariable(name="commentId") Long commentId, @RequestBody BoardNewsSubcomment boardNewsSubcommentRequest) {
      try {
        BoardNewsComment _boardNewsComment = boardNewsCommentRepository.findById(commentId).get();
        BoardNewsSubcomment _boardNewsSubcomment = new BoardNewsSubcomment(boardNewsSubcommentRequest.getWriter_studentno(), boardNewsSubcommentRequest.getWriter_name(),
                                        boardNewsSubcommentRequest.getContent());
        _boardNewsSubcomment.setBoardNewsComment(_boardNewsComment);
        boardNewsSubcommentRepository.save(_boardNewsSubcomment);
        return new ResponseEntity<>(_boardNewsSubcomment, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    @PutMapping("/news-subcomments/{id}")
    public ResponseEntity<BoardNewsSubcomment> updateBoardNewsSubcomment(@PathVariable(name="id") Long id, @RequestBody BoardNewsComment boardNewsSubcomment) {
      Optional<BoardNewsSubcomment> newsSubcommentData = boardNewsSubcommentRepository.findById(id);

      if (newsSubcommentData.isPresent()) {
        BoardNewsSubcomment _boardNewsSubcomment = newsSubcommentData.get();
        _boardNewsSubcomment.setContent(boardNewsSubcomment.getContent());
        boardNewsSubcommentRepository.save(_boardNewsSubcomment);
        return new ResponseEntity<>(_boardNewsSubcomment, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @DeleteMapping("/news-subcomments/{id}")
    public ResponseEntity<HttpStatus> deleteBoardNewsSubcomment(@PathVariable(name="id") long id) {
      try {
        boardNewsSubcommentRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @DeleteMapping("/board-news/{newsid}/comments/{commentId}/subcomments")
    public ResponseEntity<HttpStatus> deleteAllBoardNewsSubcomments(@PathVariable(name="newsid") long newsId, @PathVariable(name="commentId") long commentId) {
      try {
        boardNewsSubcommentRepository.deleteByBoardNewsCommentId(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }

    }    
}
