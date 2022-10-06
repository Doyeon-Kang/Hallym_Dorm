package com.backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.BoardNoticeComment;
import com.backend.repository.BoardNoticeCommentRepository;

@RestController
@RequestMapping(path="/api")
public class BoardNoticeCommentController {
    @Autowired
    BoardNoticeCommentRepository boardNoticeCommentRepository;

    @GetMapping(path="/notice-comments")
    public ResponseEntity<List<BoardNoticeComment>> getAllBoardNoticeCommentComment(@RequestParam Long noticeId) {
        try {
          List<BoardNoticeComment> boardNoticeComments = new ArrayList<BoardNoticeComment>();

          boardNoticeCommentRepository.findAllByNoticeId(noticeId).forEach(boardNoticeComments::add);

          if(boardNoticeComments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          }

          return new ResponseEntity<>(boardNoticeComments, HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/notice-comments")
    public ResponseEntity<BoardNoticeComment> createBoardNoticeComment(@RequestBody BoardNoticeComment boardNoticeComment) {
      try {
        BoardNoticeComment _boardNoticeComment = boardNoticeCommentRepository
                    .save(new BoardNoticeComment(boardNoticeComment.getNoticeId(), boardNoticeComment.getComment(), boardNoticeComment.getWriterId(), boardNoticeComment.getWriterName()));
        return new ResponseEntity<>(_boardNoticeComment, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    @PutMapping("/notice-comments/{commentId}")
    public ResponseEntity<BoardNoticeComment> updateBoardNoticeComment(@PathVariable("commentId") long commentId, @RequestBody BoardNoticeComment boardNoticeComment) {
      BoardNoticeComment _boardNoticeComment = boardNoticeCommentRepository.findByNoticeIdAndCommentId(boardNoticeComment.getNoticeId(), commentId);

      if (_boardNoticeComment != null) {
        _boardNoticeComment.setComment(boardNoticeComment.getComment());
        return new ResponseEntity<>(boardNoticeCommentRepository.save(_boardNoticeComment), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @DeleteMapping("/notice-comments/{commentId}")
    public ResponseEntity<HttpStatus> deleteBoardNoticeComment(@PathVariable("id") long commentId, @RequestParam Long noticeId) {
      try {
        boardNoticeCommentRepository.deletebyNoticeIdAndCommentId(noticeId, commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @DeleteMapping("/notice-comments")
    public ResponseEntity<HttpStatus> deleteAllBoardNoticeComments(@RequestParam Long noticeId) {
      try {
        boardNoticeCommentRepository.deleteByNoticeId(noticeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }

    }


}
