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

import com.backend.model.board.BoardLostComment;
import com.backend.model.board.BoardLostSubcomment;
import com.backend.repository.board.BoardLostCommentRepository;
import com.backend.repository.board.BoardLostSubcommentRepository;

@RestController
@RequestMapping(path="/api")
public class BoardLostSubcommentController {
    @Autowired
    BoardLostCommentRepository boardLostCommentRepository;
    @Autowired
    BoardLostSubcommentRepository boardLostSubcommentRepository;

    @GetMapping(path="/board-lost/{lostId}/comments/{commentId}/subcomments")
    public ResponseEntity<List<BoardLostSubcomment>> getAllBoardLostSubcommentsByLostCommentId(@PathVariable(name="commentId") Long commentId) {
        try {
          List<BoardLostSubcomment> subComments = boardLostSubcommentRepository.findByBoardLostCommentId(commentId);
          return new ResponseEntity<>(subComments, HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/lost-subcomments/{id}")
    public ResponseEntity<BoardLostSubcomment> getSubcommentById(@PathVariable(value = "id") Long id) {
      Optional<BoardLostSubcomment> lostSubcomment = boardLostSubcommentRepository.findById(id);
      if(lostSubcomment.isPresent()) {
        BoardLostSubcomment _lostSubcomment = lostSubcomment.get();
        return new ResponseEntity<BoardLostSubcomment>(_lostSubcomment, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @PostMapping("/board-lost/{lostId}/comments/{commentId}/subcomments")
    public ResponseEntity<BoardLostSubcomment> createBoardLostSubcomment(@PathVariable(name="lostId") Long lostId, @PathVariable(name="commentId") Long commentId, @RequestBody BoardLostSubcomment boardLostSubcommentRequest) {
      try {
        BoardLostComment _boardLostComment = boardLostCommentRepository.findById(commentId).get();
        BoardLostSubcomment _boardLostSubcomment = new BoardLostSubcomment(boardLostSubcommentRequest.getWriter_studentno(), boardLostSubcommentRequest.getWriter_name(),
                                        boardLostSubcommentRequest.getContent());
        _boardLostSubcomment.setBoardLostComment(_boardLostComment);
        boardLostSubcommentRepository.save(_boardLostSubcomment);
        return new ResponseEntity<>(_boardLostSubcomment, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    @PutMapping("/lost-subcomments/{id}")
    public ResponseEntity<BoardLostSubcomment> updateBoardLostSubcomment(@PathVariable(name="id") Long id, @RequestBody BoardLostComment boardLostSubcomment) {
      Optional<BoardLostSubcomment> lostSubcommentData = boardLostSubcommentRepository.findById(id);

      if (lostSubcommentData.isPresent()) {
        BoardLostSubcomment _boardLostSubcomment = lostSubcommentData.get();
        _boardLostSubcomment.setContent(boardLostSubcomment.getContent());
        boardLostSubcommentRepository.save(_boardLostSubcomment);
        return new ResponseEntity<>(_boardLostSubcomment, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @DeleteMapping("/lost-subcomments/{id}")
    public ResponseEntity<HttpStatus> deleteBoardLostSubcomment(@PathVariable(name="id") long id) {
      try {
        boardLostSubcommentRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @DeleteMapping("/board-lost/{lostid}/comments/{commentId}/subcomments")
    public ResponseEntity<HttpStatus> deleteAllBoardLostSubcomments(@PathVariable(name="lostid") long lostId, @PathVariable(name="commentId") long commentId) {
      try {
        boardLostSubcommentRepository.deleteByBoardLostCommentId(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }

    }    
}
