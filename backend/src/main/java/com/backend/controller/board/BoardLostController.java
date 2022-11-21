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

import com.backend.model.board.BoardLost;
import com.backend.payload.response.BoardResponse;
import com.backend.repository.board.BoardLostRepository;

@RestController
@RequestMapping(path="/api")
public class BoardLostController {
    @Autowired
    BoardLostRepository boardLostRepository;

    @GetMapping(path="/board-lost")
    public ResponseEntity<List<BoardResponse>> getAllBoardLost() {
        try {
          List<BoardLost> boardLosts = new ArrayList<BoardLost>();

          boardLostRepository.findAll().forEach(boardLosts::add);

          if(boardLosts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          }

          List<BoardResponse> responses = new ArrayList<BoardResponse>();

          for(BoardLost _boardLost : boardLosts) {
            responses.add(new BoardResponse(_boardLost.getId(), _boardLost.getWriterStudentNo(),
            _boardLost.getWriter_name(), _boardLost.getTitle(), _boardLost.getContent(),
            _boardLost.getViews(), _boardLost.getDate(), "lost"));
          }

          return new ResponseEntity<>(responses, HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/board-lost/{id}")
    public ResponseEntity<BoardResponse> getBoardLostById(@PathVariable("id") long id) {
      Optional<BoardLost> lostData = boardLostRepository.findById(id);

      if(lostData.isPresent()) {
        BoardLost _boardLost = lostData.get();
        int views = _boardLost.getViews() + 1;
        _boardLost.setViews(views);
        boardLostRepository.save(_boardLost);

        BoardResponse response = new BoardResponse(_boardLost.getId(), _boardLost.getWriterStudentNo(),
                                _boardLost.getWriter_name(), _boardLost.getTitle(), _boardLost.getContent(),
                                _boardLost.getViews(), _boardLost.getDate(), "lost");

        return new ResponseEntity<>(response, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @GetMapping("/board-lost/my-lost")
    public ResponseEntity<List<BoardResponse>> getMyBoardLost (@RequestParam("studentNo") String studentNo) {
      try{
        List <BoardLost> myLosts = boardLostRepository.findByWriterStudentNo(studentNo);

        if(myLosts.isEmpty()){
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<BoardResponse> responses = new ArrayList<BoardResponse>();

        for(BoardLost _boardLost : myLosts) {
          responses.add(new BoardResponse(_boardLost.getId(), _boardLost.getWriterStudentNo(),
          _boardLost.getWriter_name(), _boardLost.getTitle(), _boardLost.getContent(),
          _boardLost.getViews(), _boardLost.getDate(), "lost"));
        }

        return new ResponseEntity<>(responses, HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }        


    @PostMapping("/board-lost")
    public ResponseEntity<BoardLost> createBoardLost(@RequestBody BoardLost boardLost) {
      try {
        BoardLost _boardLost = boardLostRepository
                    .save(new BoardLost(boardLost.getWriterStudentNo(), boardLost.getWriter_name(), boardLost.getTitle(), boardLost.getContent()));
        return new ResponseEntity<>(_boardLost, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    @PutMapping("/board-lost/{id}")
    public ResponseEntity<BoardLost> updateBoardLost(@PathVariable("id") long id, @RequestBody BoardLost boardLost) {
      Optional<BoardLost> lostData = boardLostRepository.findById(id);

      if (lostData.isPresent()) {
        BoardLost _boardLost = lostData.get();
        _boardLost.setWriterStudentNo(boardLost.getWriterStudentNo());
        _boardLost.setWriter_name(boardLost.getWriter_name());
        _boardLost.setTitle(boardLost.getTitle());
        _boardLost.setContent(boardLost.getContent());
        return new ResponseEntity<>(boardLostRepository.save(_boardLost), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @DeleteMapping("/board-lost/{id}")
    public ResponseEntity<HttpStatus> deleteBoardLost(@PathVariable("id") long id) {
      try {
        boardLostRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @DeleteMapping("/board-lost")
    public ResponseEntity<HttpStatus> deleteAllBoardLosts() {
      try {
        boardLostRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }

    }


}
