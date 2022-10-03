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

import com.backend.model.BoardStore;
import com.backend.repository.BoardStoreRepository;

@RestController
@RequestMapping(path="/api")
public class BoardStoreController {
    @Autowired
    BoardStoreRepository boardStoreRepository;

    @GetMapping(path="/stores")
    public ResponseEntity<List<BoardStore>> getAllBoardStore() {
        try {
          List<BoardStore> boardStores = new ArrayList<BoardStore>();

          boardStoreRepository.findAll().forEach(boardStores::add);

          if(boardStores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          }

          return new ResponseEntity<>(boardStores, HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/stores/{id}")
    public ResponseEntity<BoardStore> getBoardStoreById(@PathVariable("id") long id) {
      Optional<BoardStore> storeData = boardStoreRepository.findById(id);

      if(storeData.isPresent()) {
        BoardStore _boardStore = storeData.get();
        int views = _boardStore.getViews() + 1;
        _boardStore.setViews(views);
        return new ResponseEntity<>(boardStoreRepository.save(_boardStore), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @PostMapping("/stores")
    public ResponseEntity<BoardStore> createBoardStore(@RequestBody BoardStore boardStore) {
      try {
        BoardStore _boardStore = boardStoreRepository
                    .save(new BoardStore(boardStore.getWriter_username(), boardStore.getWriter_name(), boardStore.getTitle(), boardStore.getContents()));
        return new ResponseEntity<>(_boardStore, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    @PutMapping("/stores/{id}")
    public ResponseEntity<BoardStore> updateBoardStore(@PathVariable("id") long id, @RequestBody BoardStore boardStore) {
      Optional<BoardStore> storeData = boardStoreRepository.findById(id);

      if (storeData.isPresent()) {
        BoardStore _boardStore = storeData.get();
        _boardStore.setWriter_username(boardStore.getWriter_username());
        _boardStore.setWriter_name(boardStore.getWriter_name());
        _boardStore.setTitle(boardStore.getTitle());
        _boardStore.setContents(boardStore.getContents());
        return new ResponseEntity<>(boardStoreRepository.save(_boardStore), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @DeleteMapping("/stores/{id}")
    public ResponseEntity<HttpStatus> deleteBoardStore(@PathVariable("id") long id) {
      try {
        boardStoreRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @DeleteMapping("/stores")
    public ResponseEntity<HttpStatus> deleteAllBoardStores() {
      try {
        boardStoreRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }

    }


}
