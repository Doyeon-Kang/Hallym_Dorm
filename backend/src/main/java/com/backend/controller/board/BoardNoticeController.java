package com.backend.controller.board;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.board.BoardNotice;
import com.backend.payload.response.BoardResponse;
import com.backend.repository.board.BoardNoticeRepository;

@RestController
@RequestMapping(path="/api")
public class BoardNoticeController {
    @Autowired
    BoardNoticeRepository boardNoticeRepository;

    @GetMapping(path="/board-notice")
    public ResponseEntity<List<BoardResponse>> getAllBoardNotice() {
        try {
          List<BoardNotice> boardNotices = new ArrayList<BoardNotice>();

          boardNoticeRepository.findByNotice1False().forEach(boardNotices::add);
          if(boardNotices.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          }

          List<BoardResponse> responses = new ArrayList<BoardResponse>();

          for(BoardNotice _boardNotice : boardNotices) {
            responses.add(new BoardResponse(_boardNotice.getId(), _boardNotice.getWriterStudentNo(),
            _boardNotice.getWriter_name(), _boardNotice.getTitle(), _boardNotice.getContent(),
            _boardNotice.getViews(), _boardNotice.getDate(), "notice"));
          }
  
          return new ResponseEntity<>(responses, HttpStatus.OK);

        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/board-notice1")
    public ResponseEntity<List<BoardResponse>> getAllBoardNotice1() {
      try {
        List<BoardNotice> boardNotices = new ArrayList<BoardNotice>();

        boardNoticeRepository.findByNotice1True().forEach(boardNotices::add);
        if(boardNotices.isEmpty()) {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<BoardResponse> responses = new ArrayList<BoardResponse>();

        for(BoardNotice _boardNotice : boardNotices) {
          responses.add(new BoardResponse(_boardNotice.getId(), _boardNotice.getWriterStudentNo(),
          _boardNotice.getWriter_name(), _boardNotice.getTitle(), _boardNotice.getContent(),
          _boardNotice.getViews(), _boardNotice.getDate(), "notice1"));
        }

        return new ResponseEntity<>(responses, HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @GetMapping("/board-notice/{id}")
    public ResponseEntity<BoardResponse> getBoardNoticeById(@PathVariable("id") long id) {
      Optional<BoardNotice> noticeData = boardNoticeRepository.findById(id);

      if(noticeData.isPresent()) {
        BoardNotice _boardNotice = noticeData.get();
        int views = _boardNotice.getViews() + 1;
        _boardNotice.setViews(views);
        boardNoticeRepository.save(_boardNotice);

        String type = "";
        if(_boardNotice.isNotice1()) type = "notice1";
        else type = "notice";

        BoardResponse response = new BoardResponse(_boardNotice.getId(), _boardNotice.getWriterStudentNo(),
                                  _boardNotice.getWriter_name(), _boardNotice.getTitle(), _boardNotice.getContent(),
                                  _boardNotice.getViews(), _boardNotice.getDate(), type);
        return new ResponseEntity<>(response, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @GetMapping("/board-notice/my-notice")
    public ResponseEntity<List<BoardResponse>> getMyBoardNotice (@RequestParam("studentNo") String studentNo) {
      try{
        List <BoardNotice> myNotices = boardNoticeRepository.findByWriterStudentNoAndNotice1False(studentNo);

        if(myNotices.isEmpty()){
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<BoardResponse> responses = new ArrayList<BoardResponse>();

        for(BoardNotice _boardNotice : myNotices) {
          responses.add(new BoardResponse(_boardNotice.getId(), _boardNotice.getWriterStudentNo(),
          _boardNotice.getWriter_name(), _boardNotice.getTitle(), _boardNotice.getContent(),
          _boardNotice.getViews(), _boardNotice.getDate(), "notice"));
        }

        return new ResponseEntity<>(responses, HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @GetMapping("/board-notice1/my-notice")
    public ResponseEntity<List<BoardResponse>> getMyBoardNotice1 (@RequestParam("studentNo") String studentNo) {
      try{
        List <BoardNotice> myNotices = boardNoticeRepository.findByWriterStudentNoAndNotice1True(studentNo);

        if(myNotices.isEmpty()){
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<BoardResponse> responses = new ArrayList<BoardResponse>();

        for(BoardNotice _boardNotice : myNotices) {
          responses.add(new BoardResponse(_boardNotice.getId(), _boardNotice.getWriterStudentNo(),
          _boardNotice.getWriter_name(), _boardNotice.getTitle(), _boardNotice.getContent(),
          _boardNotice.getViews(), _boardNotice.getDate(), "notice1"));
        }

        return new ResponseEntity<>(responses, HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }    

    @PostMapping("/board-notice")
    public ResponseEntity<BoardNotice> createBoardNotice(@RequestBody BoardNotice boardNotice) {
      try {
        BoardNotice _boardNotice = boardNoticeRepository
                    .save(new BoardNotice(boardNotice.getWriterStudentNo(), boardNotice.getWriter_name(), boardNotice.getTitle(), boardNotice.getContent(), boardNotice.isNotice1()));
        return new ResponseEntity<>(_boardNotice, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    @PutMapping("/board-notice/{id}")
    public ResponseEntity<BoardNotice> updateBoardNotice(@PathVariable("id") long id, @RequestBody BoardNotice boardNotice) {
      Optional<BoardNotice> noticeData = boardNoticeRepository.findById(id);

      if (noticeData.isPresent()) {
        BoardNotice _boardNotice = noticeData.get();
        _boardNotice.setWriterStudentNo(boardNotice.getWriterStudentNo());
        _boardNotice.setWriter_name(boardNotice.getWriter_name());
        _boardNotice.setTitle(boardNotice.getTitle());
        _boardNotice.setContent(boardNotice.getContent());
        _boardNotice.setNotice1(boardNotice.isNotice1());
        return new ResponseEntity<>(boardNoticeRepository.save(_boardNotice), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @DeleteMapping("/board-notice/{id}")
    public ResponseEntity<HttpStatus> deleteBoardNotice(@PathVariable("id") long id) {
      try {
        boardNoticeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @DeleteMapping("/board-notice")
    public ResponseEntity<HttpStatus> deleteAllBoardNotices() {
      try {
        boardNoticeRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }

    }


}
