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

import com.backend.model.board.BoardStoreComment;
import com.backend.model.board.BoardStoreSubcomment;
import com.backend.repository.board.BoardStoreCommentRepository;
import com.backend.repository.board.BoardStoreSubcommentRepository;

@RestController
@RequestMapping(path="/api")
public class BoardStoreSubcommentController {
    @Autowired
    BoardStoreCommentRepository boardStoreCommentRepository;
    @Autowired
    BoardStoreSubcommentRepository boardStoreSubcommentRepository;

    @GetMapping(path="/board-store/{storeId}/comments/{commentId}/subcomments")
    public ResponseEntity<List<BoardStoreSubcomment>> getAllBoardStoreSubcommentsByStoreCommentId(@PathVariable(name="commentId") Long commentId) {
        try {
          List<BoardStoreSubcomment> subComments = boardStoreSubcommentRepository.findByBoardStoreCommentId(commentId);
          return new ResponseEntity<>(subComments, HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/store-subcomments/{id}")
    public ResponseEntity<BoardStoreSubcomment> getSubcommentById(@PathVariable(value = "id") Long id) {
      Optional<BoardStoreSubcomment> storeSubcomment = boardStoreSubcommentRepository.findById(id);
      if(storeSubcomment.isPresent()) {
        BoardStoreSubcomment _storeSubcomment = storeSubcomment.get();
        return new ResponseEntity<BoardStoreSubcomment>(_storeSubcomment, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @PostMapping("/board-store/{storeId}/comments/{commentId}/subcomments")
    public ResponseEntity<BoardStoreSubcomment> createBoardStoreSubcomment(@PathVariable(name="storeId") Long storeId, @PathVariable(name="commentId") Long commentId, @RequestBody BoardStoreSubcomment boardStoreSubcommentRequest) {
      try {
        BoardStoreComment _boardStoreComment = boardStoreCommentRepository.findById(commentId).get();
        BoardStoreSubcomment _boardStoreSubcomment = new BoardStoreSubcomment(boardStoreSubcommentRequest.getWriter_studentno(), boardStoreSubcommentRequest.getWriter_name(),
                                        boardStoreSubcommentRequest.getContent());
        _boardStoreSubcomment.setBoardStoreComment(_boardStoreComment);
        boardStoreSubcommentRepository.save(_boardStoreSubcomment);
        return new ResponseEntity<>(_boardStoreSubcomment, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    @PutMapping("/store-subcomments/{id}")
    public ResponseEntity<BoardStoreSubcomment> updateBoardStoreSubcomment(@PathVariable(name="id") Long id, @RequestBody BoardStoreComment boardStoreSubcomment) {
      Optional<BoardStoreSubcomment> storeSubcommentData = boardStoreSubcommentRepository.findById(id);

      if (storeSubcommentData.isPresent()) {
        BoardStoreSubcomment _boardStoreSubcomment = storeSubcommentData.get();
        _boardStoreSubcomment.setContent(boardStoreSubcomment.getContent());
        boardStoreSubcommentRepository.save(_boardStoreSubcomment);
        return new ResponseEntity<>(_boardStoreSubcomment, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @DeleteMapping("/store-subcomments/{id}")
    public ResponseEntity<HttpStatus> deleteBoardStoreSubcomment(@PathVariable(name="id") long id) {
      try {
        boardStoreSubcommentRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @DeleteMapping("/board-store/{storeid}/comments/{commentId}/subcomments")
    public ResponseEntity<HttpStatus> deleteAllBoardStoreSubcomments(@PathVariable(name="storeid") long storeId, @PathVariable(name="commentId") long commentId) {
      try {
        boardStoreSubcommentRepository.deleteByBoardStoreCommentId(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }

    }    
}
