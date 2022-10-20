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
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.board.BoardRepair;
import com.backend.repository.board.BoardRepairRepository;

@RestController
@RequestMapping(path="/api")
public class BoardRepairController {
    @Autowired
    BoardRepairRepository boardRepairRepository;

    @GetMapping(path="/board-repair")
    public ResponseEntity<List<BoardRepair>> getAllBoardRepair() {
        try {
          List<BoardRepair> boardRepairs = new ArrayList<BoardRepair>();

          boardRepairRepository.findAll().forEach(boardRepairs::add);
          if(boardRepairs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          }

          return new ResponseEntity<>(boardRepairs, HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/board-repair/{id}")
    public ResponseEntity<BoardRepair> getBoardRepairById(@PathVariable("id") long id) {
      Optional<BoardRepair> repairData = boardRepairRepository.findById(id);

      if(repairData.isPresent()) {
        BoardRepair _boardRepair = repairData.get();
        int views = _boardRepair.getViews() + 1;
        _boardRepair.setViews(views);
        return new ResponseEntity<>(boardRepairRepository.save(_boardRepair), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @PostMapping("/board-repair")
    public ResponseEntity<BoardRepair> createBoardRepair(@RequestBody BoardRepair boardRepair) {
      try {
        BoardRepair _boardRepair = boardRepairRepository
                    .save(new BoardRepair(boardRepair.getWriter_studentno(), boardRepair.getWriter_name(), boardRepair.getTitle(), boardRepair.getContent()));
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
        _boardRepair.setWriter_studentno(boardRepair.getWriter_studentno());
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
