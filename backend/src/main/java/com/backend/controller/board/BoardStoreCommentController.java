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

import com.backend.model.board.BoardStore;
import com.backend.model.board.BoardStoreComment;
import com.backend.repository.board.BoardStoreCommentRepository;
import com.backend.repository.board.BoardStoreRepository;

@RestController
@RequestMapping(path="/api")
public class BoardStoreCommentController {
    @Autowired
    BoardStoreRepository boardStoreRepository;
    @Autowired
    BoardStoreCommentRepository boardStoreCommentRepository;

    @GetMapping(path="/board-store/{storeId}/comments")
    public ResponseEntity<List<BoardStoreComment>> getAllBoardStoreCommentsByStoreId(@PathVariable(name="storeId") Long storeId) {
        try {
          List<BoardStoreComment> storeComments = boardStoreCommentRepository.findByBoardStoreId(storeId);
          return new ResponseEntity<>(storeComments, HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/store-comments/{id}")
    public ResponseEntity<BoardStoreComment> getCommentById(@PathVariable(value = "id") Long id) {
      Optional<BoardStoreComment> storeComment = boardStoreCommentRepository.findById(id);
      if(storeComment.isPresent()) {
        BoardStoreComment _boardStoreComment = storeComment.get();
        return new ResponseEntity<BoardStoreComment>(_boardStoreComment, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @PostMapping("/board-store/{storeId}/comments")
    public ResponseEntity<BoardStoreComment> createBoardStoreComment(@PathVariable(name="storeId") Long storeId, @RequestBody BoardStoreComment boardStoreCommentRequest) {
      try {
        BoardStore _boardStore = boardStoreRepository.findById(storeId).get();
        BoardStoreComment _boardStoreComment = new BoardStoreComment(boardStoreCommentRequest.getWriter_studentno(), boardStoreCommentRequest.getWriter_name(),
                                                                        boardStoreCommentRequest.getContent());
        _boardStoreComment.setBoardStore(_boardStore);
        boardStoreCommentRepository.save(_boardStoreComment);
        return new ResponseEntity<>(_boardStoreComment, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    @PutMapping("/store-comments/{id}")
    public ResponseEntity<BoardStoreComment> updateBoardStoreComment(@PathVariable(name="id") Long id, @RequestBody BoardStoreComment boardStoreComment) {
      Optional<BoardStoreComment> storeCommentData = boardStoreCommentRepository.findById(id);

      if (storeCommentData.isPresent()) {
        BoardStoreComment _boardStoreComment = storeCommentData.get();
        _boardStoreComment.setContent(boardStoreComment.getContent());
        boardStoreCommentRepository.save(_boardStoreComment);
        return new ResponseEntity<>(_boardStoreComment, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @DeleteMapping("/store-comments/{id}")
    public ResponseEntity<HttpStatus> deleteBoardStoreComment(@PathVariable(name="id") long id) {
      try {
        boardStoreCommentRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @DeleteMapping("/board-store/{storeId}/comments")
    public ResponseEntity<HttpStatus> deleteAllBoardStoreComments(@PathVariable(name="storeId") long storeId) {
      try {
        boardStoreCommentRepository.deleteByBoardStoreId(storeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }

    }
}
