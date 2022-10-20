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

import com.backend.model.BoardRepair;
import com.backend.model.BoardRepairComment;
import com.backend.repository.BoardRepairCommentRepository;
import com.backend.repository.BoardRepairRepository;

@RestController
@RequestMapping(path="/api")
public class BoardRepairCommentController {
    @Autowired
    BoardRepairRepository boardRepairRepository;
    @Autowired
    BoardRepairCommentRepository boardRepairCommentRepository;

    @GetMapping(path="/board-repair/{repairId}/comments")
    public ResponseEntity<List<BoardRepairComment>> getAllBoardRepairCommentsByRepairId(@PathVariable(name="repairId") Long repairId) {
        try {
          List<BoardRepairComment> repairComments = boardRepairCommentRepository.findByBoardRepairId(repairId);
          return new ResponseEntity<>(repairComments, HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/repair-comments/{id}")
    public ResponseEntity<BoardRepairComment> getCommentById(@PathVariable(value = "id") Long id) {
      Optional<BoardRepairComment> repairComment = boardRepairCommentRepository.findById(id);
      if(repairComment.isPresent()) {
        BoardRepairComment _boardRepairComment = repairComment.get();
        return new ResponseEntity<BoardRepairComment>(_boardRepairComment, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @PostMapping("/board-repair/{repairId}/comments")
    public ResponseEntity<BoardRepairComment> createBoardRepairComment(@PathVariable(name="repairId") Long repairId, @RequestBody BoardRepairComment boardRepairCommentRequest) {
      try {
        BoardRepair _boardRepair = boardRepairRepository.findById(repairId).get();
        BoardRepairComment _boardRepairComment = new BoardRepairComment(boardRepairCommentRequest.getWriter_studentno(), boardRepairCommentRequest.getWriter_name(),
                                                                        boardRepairCommentRequest.getContent());
        _boardRepairComment.setBoardRepair(_boardRepair);
        boardRepairCommentRepository.save(_boardRepairComment);
        return new ResponseEntity<>(_boardRepairComment, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    @PutMapping("/repair-comments/{id}")
    public ResponseEntity<BoardRepairComment> updateBoardRepairComment(@PathVariable(name="id") Long id, @RequestBody BoardRepairComment boardRepairComment) {
      Optional<BoardRepairComment> repairCommentData = boardRepairCommentRepository.findById(id);

      if (repairCommentData.isPresent()) {
        BoardRepairComment _boardRepairComment = repairCommentData.get();
        _boardRepairComment.setContent(boardRepairComment.getContent());
        boardRepairCommentRepository.save(_boardRepairComment);
        return new ResponseEntity<>(_boardRepairComment, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @DeleteMapping("/repair-comments/{id}")
    public ResponseEntity<HttpStatus> deleteBoardRepairComment(@PathVariable(name="id") long id) {
      try {
        boardRepairCommentRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @DeleteMapping("/board-repair/{repairid}/comments")
    public ResponseEntity<HttpStatus> deleteAllBoardRepairComments(@PathVariable(name="repairid") long repairId) {
      try {
        boardRepairCommentRepository.deleteByBoardRepairId(repairId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }

    }
}
