package com.backend.controller;

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

import com.backend.model.BoardLost;
import com.backend.repository.BoardLostRepository;

@RestController
@RequestMapping(path="/api")
public class BoardLostController {
    @Autowired
    BoardLostRepository boardLostRepository;

    @GetMapping(path="/losts")
    public ResponseEntity<List<BoardLost>> getAllBoardLost() {
        try {
          List<BoardLost> boardLosts = new ArrayList<BoardLost>();

          boardLostRepository.findAll().forEach(boardLosts::add);

          if(boardLosts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          }

          return new ResponseEntity<>(boardLosts, HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/losts/{id}")
    public ResponseEntity<BoardLost> getBoardLostById(@PathVariable("id") long id) {
      Optional<BoardLost> lostData = boardLostRepository.findById(id);

      if(lostData.isPresent()) {
        BoardLost _boardLost = lostData.get();
        int views = _boardLost.getViews() + 1;
        _boardLost.setViews(views);
        return new ResponseEntity<>(boardLostRepository.save(_boardLost), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @PostMapping("/losts")
    public ResponseEntity<BoardLost> createBoardLost(@RequestBody BoardLost boardLost) {
      try {
        BoardLost _boardLost = boardLostRepository
                    .save(new BoardLost(boardLost.getWriter_studentno(), boardLost.getWriter_name(), boardLost.getTitle(), boardLost.getContents()));
        return new ResponseEntity<>(_boardLost, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    @PutMapping("/losts/{id}")
    public ResponseEntity<BoardLost> updateBoardLost(@PathVariable("id") long id, @RequestBody BoardLost boardLost) {
      Optional<BoardLost> lostData = boardLostRepository.findById(id);

      if (lostData.isPresent()) {
        BoardLost _boardLost = lostData.get();
        _boardLost.setWriter_studentno(boardLost.getWriter_studentno());
        _boardLost.setWriter_name(boardLost.getWriter_name());
        _boardLost.setTitle(boardLost.getTitle());
        _boardLost.setContents(boardLost.getContents());
        return new ResponseEntity<>(boardLostRepository.save(_boardLost), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @DeleteMapping("/losts/{id}")
    public ResponseEntity<HttpStatus> deleteBoardLost(@PathVariable("id") long id) {
      try {
        boardLostRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @DeleteMapping("/losts")
    public ResponseEntity<HttpStatus> deleteAllBoardLosts() {
      try {
        boardLostRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }

    }


}
