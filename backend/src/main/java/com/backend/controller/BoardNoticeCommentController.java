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

import com.backend.model.BoardNotice;
import com.backend.model.BoardNoticeComment;
import com.backend.repository.BoardNoticeCommentRepository;
import com.backend.repository.BoardNoticeRepository;

@RestController
@RequestMapping(path="/api")
public class BoardNoticeCommentController {
    @Autowired
    BoardNoticeRepository boardNoticeRepository;
    @Autowired
    BoardNoticeCommentRepository boardNoticeCommentRepository;

    @GetMapping(path="/board-notice/{noticeId}/comments")
    public ResponseEntity<List<BoardNoticeComment>> getAllBoardNoticeCommentsByNoticeId(@PathVariable(name="noticeId") Long noticeId) {
        try {
          List<BoardNoticeComment> noticeComments = boardNoticeCommentRepository.findByBoardNoticeId(noticeId);
          return new ResponseEntity<>(noticeComments, HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/comments/{id}")
    public ResponseEntity<BoardNoticeComment> getCommentById(@PathVariable(value = "id") Long id) {
      Optional<BoardNoticeComment> noticeComment = boardNoticeCommentRepository.findById(id);
      if(noticeComment.isPresent()) {
        BoardNoticeComment _boardNoticeComment = noticeComment.get();
        return new ResponseEntity<BoardNoticeComment>(_boardNoticeComment, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @PostMapping("/board-notice/{noticeId}/comments")
    public ResponseEntity<BoardNoticeComment> createBoardNoticeComment(@PathVariable(name="noticeId") Long noticeId, @RequestBody BoardNoticeComment boardNoticeCommentRequest) {
      try {
        BoardNotice _boardNotice = boardNoticeRepository.findById(noticeId).get();
        BoardNoticeComment _boardNoticeComment = new BoardNoticeComment(boardNoticeCommentRequest.getWriter_studentno(), boardNoticeCommentRequest.getWriter_name(),
                                                                        boardNoticeCommentRequest.getContent());
        _boardNoticeComment.setBoardNotice(_boardNotice);
        boardNoticeCommentRepository.save(_boardNoticeComment);
        return new ResponseEntity<>(_boardNoticeComment, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    @PutMapping("/notice-comments/{id}")
    public ResponseEntity<BoardNoticeComment> updateBoardNoticeComment(@PathVariable(name="id") Long id, @RequestBody BoardNoticeComment boardNoticeComment) {
      Optional<BoardNoticeComment> noticeCommentData = boardNoticeCommentRepository.findById(id);

      if (noticeCommentData.isPresent()) {
        BoardNoticeComment _boardNoticeComment = noticeCommentData.get();
        _boardNoticeComment.setContent(boardNoticeComment.getContent());
        boardNoticeCommentRepository.save(_boardNoticeComment);
        return new ResponseEntity<>(_boardNoticeComment, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @DeleteMapping("/notice-comments/{id}")
    public ResponseEntity<HttpStatus> deleteBoardNoticeComment(@PathVariable(name="id") long id) {
      try {
        boardNoticeCommentRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @DeleteMapping("/board-notice/{noticeid}/comments")
    public ResponseEntity<HttpStatus> deleteAllBoardNoticeComments(@PathVariable(name="noticeid") long noticeId) {
      try {
        boardNoticeCommentRepository.deleteByBoardNoticeId(noticeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }

    }


}
