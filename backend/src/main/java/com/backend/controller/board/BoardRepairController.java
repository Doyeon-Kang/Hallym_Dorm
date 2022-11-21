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

import com.backend.model.board.BoardRepair;
import com.backend.payload.response.BoardResponse;
import com.backend.repository.board.BoardRepairRepository;

@RestController
@RequestMapping(path="/api")
public class BoardRepairController {
    @Autowired
    BoardRepairRepository boardRepairRepository;

    @GetMapping(path="/board-repair")
    public ResponseEntity<List<BoardResponse>> getAllBoardRepair() {
        try {
          List<BoardRepair> boardRepairs = new ArrayList<BoardRepair>();

          boardRepairRepository.findAll().forEach(boardRepairs::add);
          if(boardRepairs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          }

          List<BoardResponse> responses = new ArrayList<BoardResponse>();

          for(BoardRepair _boardRepair : boardRepairs) {
            responses.add(new BoardResponse(_boardRepair.getId(), _boardRepair.getWriterStudentNo(),
            _boardRepair.getWriter_name(), _boardRepair.getTitle(), _boardRepair.getContent(),
            _boardRepair.getViews(), _boardRepair.getDate(), "repair"));
          }

          return new ResponseEntity<>(responses, HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/board-repair/{id}")
    public ResponseEntity<BoardResponse> getBoardRepairById(@PathVariable("id") long id) {
      Optional<BoardRepair> repairData = boardRepairRepository.findById(id);

      if(repairData.isPresent()) {
        BoardRepair _boardRepair = repairData.get();
        int views = _boardRepair.getViews() + 1;
        _boardRepair.setViews(views);
        boardRepairRepository.save(_boardRepair);

        BoardResponse response = new BoardResponse(_boardRepair.getId(), _boardRepair.getWriterStudentNo(),
                                _boardRepair.getWriter_name(), _boardRepair.getTitle(), _boardRepair.getContent(),
                                _boardRepair.getViews(), _boardRepair.getDate(), "repair");

        return new ResponseEntity<>(response, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @GetMapping("/board-repair/my-repair")
    public ResponseEntity<List<BoardResponse>> getMyBoardRepair (@RequestParam("studentNo") String studentNo) {
      try{
        List <BoardRepair> myRepairs = boardRepairRepository.findByWriterStudentNo(studentNo);

        if(myRepairs.isEmpty()){
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        List<BoardResponse> responses = new ArrayList<BoardResponse>();

        for(BoardRepair _boardRepair : myRepairs) {
          responses.add(new BoardResponse(_boardRepair.getId(), _boardRepair.getWriterStudentNo(),
          _boardRepair.getWriter_name(), _boardRepair.getTitle(), _boardRepair.getContent(),
          _boardRepair.getViews(), _boardRepair.getDate(), "repair"));
        }

        return new ResponseEntity<>(responses, HttpStatus.OK);

      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }        


    @PostMapping("/board-repair")
    public ResponseEntity<BoardRepair> createBoardRepair(@RequestBody BoardRepair boardRepair) {
      try {
        BoardRepair _boardRepair = boardRepairRepository
                    .save(new BoardRepair(boardRepair.getWriterStudentNo(), boardRepair.getWriter_name(), boardRepair.getTitle(), boardRepair.getContent()));
        return new ResponseEntity<>(_boardRepair, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    @PutMapping("/board-repair/{id}")
    public ResponseEntity<BoardRepair> updateBoardRepair(@PathVariable("id") long id, @RequestBody BoardRepair boardRepair) {
      Optional<BoardRepair> repairData = boardRepairRepository.findById(id);

      if (repairData.isPresent()) {
        BoardRepair _boardRepair = repairData.get();
        _boardRepair.setWriterStudentNo(boardRepair.getWriterStudentNo());
        _boardRepair.setWriter_name(boardRepair.getWriter_name());
        _boardRepair.setTitle(boardRepair.getTitle());
        _boardRepair.setContent(boardRepair.getContent());
        return new ResponseEntity<>(boardRepairRepository.save(_boardRepair), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @DeleteMapping("/board-repair/{id}")
    public ResponseEntity<HttpStatus> deleteBoardRepair(@PathVariable("id") long id) {
      try {
        boardRepairRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @DeleteMapping("/board-repair")
    public ResponseEntity<HttpStatus> deleteAllBoardRepairs() {
      try {
        boardRepairRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }

    }


}
