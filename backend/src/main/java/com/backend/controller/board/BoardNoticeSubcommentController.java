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

import com.backend.model.board.BoardNoticeComment;
import com.backend.model.board.BoardNoticeSubcomment;
import com.backend.repository.board.BoardNoticeCommentRepository;
import com.backend.repository.board.BoardNoticeSubcommentRepository;

@RestController
@RequestMapping(path="/api")
public class BoardNoticeSubcommentController {
    @Autowired
    BoardNoticeCommentRepository boardNoticeCommentRepository;
    @Autowired
    BoardNoticeSubcommentRepository boardNoticeSubcommentRepository;

    @GetMapping(path="/board-notice/{noticeId}/comments/{commentId}/subcomments")
    public ResponseEntity<List<BoardNoticeSubcomment>> getAllBoardNoticeSubcommentsByNoticeCommentId(@PathVariable(name="commentId") Long commentId) {
        try {
          List<BoardNoticeSubcomment> subComments = boardNoticeSubcommentRepository.findByBoardNoticeCommentId(commentId);
          return new ResponseEntity<>(subComments, HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/notice-subcomments/{id}")
    public ResponseEntity<BoardNoticeSubcomment> getSubcommentById(@PathVariable(value = "id") Long id) {
      Optional<BoardNoticeSubcomment> noticeSubcomment = boardNoticeSubcommentRepository.findById(id);
      if(noticeSubcomment.isPresent()) {
        BoardNoticeSubcomment _noticeSubcomment = noticeSubcomment.get();
        return new ResponseEntity<BoardNoticeSubcomment>(_noticeSubcomment, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @PostMapping("/board-notice/{noticeId}/comments/{commentId}/subcomments")
    public ResponseEntity<BoardNoticeSubcomment> createBoardNoticeSubcomment(@PathVariable(name="noticeId") Long noticeId, @PathVariable(name="commentId") Long commentId, @RequestBody BoardNoticeSubcomment boardNoticeSubcommentRequest) {
      try {
        BoardNoticeComment _boardNoticeComment = boardNoticeCommentRepository.findById(commentId).get();
        BoardNoticeSubcomment _boardNoticeSubcomment = new BoardNoticeSubcomment(boardNoticeSubcommentRequest.getWriter_studentno(), boardNoticeSubcommentRequest.getWriter_name(),
                                        boardNoticeSubcommentRequest.getContent());
        _boardNoticeSubcomment.setBoardNoticeComment(_boardNoticeComment);
        boardNoticeSubcommentRepository.save(_boardNoticeSubcomment);
        return new ResponseEntity<>(_boardNoticeSubcomment, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    @PutMapping("/notice-subcomments/{id}")
    public ResponseEntity<BoardNoticeSubcomment> updateBoardNoticeSubcomment(@PathVariable(name="id") Long id, @RequestBody BoardNoticeComment boardNoticeSubcomment) {
      Optional<BoardNoticeSubcomment> noticeSubcommentData = boardNoticeSubcommentRepository.findById(id);

      if (noticeSubcommentData.isPresent()) {
        BoardNoticeSubcomment _boardNoticeSubcomment = noticeSubcommentData.get();
        _boardNoticeSubcomment.setContent(boardNoticeSubcomment.getContent());
        boardNoticeSubcommentRepository.save(_boardNoticeSubcomment);
        return new ResponseEntity<>(_boardNoticeSubcomment, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @DeleteMapping("/notice-subcomments/{id}")
    public ResponseEntity<HttpStatus> deleteBoardNoticeSubcomment(@PathVariable(name="id") long id) {
      try {
        boardNoticeSubcommentRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @DeleteMapping("/board-notice/{noticeId}/comments/{commentId}/subcomments")
    public ResponseEntity<HttpStatus> deleteAllBoardNoticeSubcomments(@PathVariable(name="noticeId") long noticeId, @PathVariable(name="commentId") long commentId) {
      try {
        boardNoticeSubcommentRepository.deleteByBoardNoticeCommentId(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }

    }    
}
