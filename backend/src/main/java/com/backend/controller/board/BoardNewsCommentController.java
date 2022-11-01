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

import com.backend.model.board.BoardNews;
import com.backend.model.board.BoardNewsComment;
import com.backend.repository.board.BoardNewsCommentRepository;
import com.backend.repository.board.BoardNewsRepository;

@RestController
@RequestMapping(path="/api")
public class BoardNewsCommentController {
    @Autowired
    BoardNewsRepository boardNewsRepository;
    @Autowired
    BoardNewsCommentRepository boardNewsCommentRepository;

    @GetMapping(path="/board-news/{newsId}/comments")
    public ResponseEntity<List<BoardNewsComment>> getAllBoardNewsCommentsByStoreId(@PathVariable(name="newsId") Long newsId) {
        try {
          List<BoardNewsComment> newsComments = boardNewsCommentRepository.findByBoardNewsId(newsId);
          return new ResponseEntity<>(newsComments, HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/news-comments/{id}")
    public ResponseEntity<BoardNewsComment> getCommentById(@PathVariable(value = "id") Long id) {
      Optional<BoardNewsComment> newsComment = boardNewsCommentRepository.findById(id);
      if(newsComment.isPresent()) {
        BoardNewsComment _boardNewsComment = newsComment.get();
        return new ResponseEntity<BoardNewsComment>(_boardNewsComment, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @PostMapping("/board-news/{newsId}/comments")
    public ResponseEntity<BoardNewsComment> createBoardNewsComment(@PathVariable(name="newsId") Long newsId, @RequestBody BoardNewsComment boardNewsCommentRequest) {
      try {
        BoardNews _boardNews = boardNewsRepository.findById(newsId).get();
        BoardNewsComment _boardNewsComment = new BoardNewsComment(boardNewsCommentRequest.getWriter_studentno(), boardNewsCommentRequest.getWriter_name(),
                                                                        boardNewsCommentRequest.getContent());
        _boardNewsComment.setBoardNews(_boardNews);
        boardNewsCommentRepository.save(_boardNewsComment);
        return new ResponseEntity<>(_boardNewsComment, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    @PutMapping("/news-comments/{id}")
    public ResponseEntity<BoardNewsComment> updateBoardNewsComment(@PathVariable(name="id") Long id, @RequestBody BoardNewsComment boardNewsComment) {
      Optional<BoardNewsComment> newsCommentData = boardNewsCommentRepository.findById(id);

      if (newsCommentData.isPresent()) {
        BoardNewsComment _boardNewsComment = newsCommentData.get();
        _boardNewsComment.setContent(boardNewsComment.getContent());
        boardNewsCommentRepository.save(_boardNewsComment);
        return new ResponseEntity<>(_boardNewsComment, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @DeleteMapping("/news-comments/{id}")
    public ResponseEntity<HttpStatus> deleteBoardNewsComment(@PathVariable(name="id") long id) {
      try {
        boardNewsCommentRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @DeleteMapping("/board-news/{newsId}/comments")
    public ResponseEntity<HttpStatus> deleteAllBoardNewsComments(@PathVariable(name="newsId") long newsId) {
      try {
        boardNewsCommentRepository.deleteByBoardNewsId(newsId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
}
