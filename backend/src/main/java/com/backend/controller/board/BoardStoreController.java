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

import com.backend.model.board.BoardStore;
import com.backend.payload.request.BoardRequest;
import com.backend.repository.board.BoardStoreRepository;


@RestController
@RequestMapping(path="/api")
public class BoardStoreController {
    @Autowired
    BoardStoreRepository boardStoreRepository;

    @GetMapping(path="/board-store")
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

    @GetMapping("/board-store/{id}")
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

    @GetMapping("/board-store/my-store")
    public ResponseEntity<List<BoardStore>> getMyBoardStore (@RequestBody BoardRequest boardRequest) {
      try{
        List <BoardStore> myStores = boardStoreRepository.findByWriterStudentNo(boardRequest.getStudentNo());

        if(myStores.isEmpty()){
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(myStores, HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }  

    @PostMapping("/board-store")
    public ResponseEntity<BoardStore> createBoardStore(@RequestBody BoardStore boardStore) {
      try {
        BoardStore _boardStore = boardStoreRepository
                    .save(new BoardStore(boardStore.getWriterStudentNo(), boardStore.getWriter_name(), boardStore.getTitle(), boardStore.getContent()));
        return new ResponseEntity<>(_boardStore, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    @PutMapping("/board-store/{id}")
    public ResponseEntity<BoardStore> updateBoardStore(@PathVariable("id") long id, @RequestBody BoardStore boardStore) {
      Optional<BoardStore> storeData = boardStoreRepository.findById(id);

      if (storeData.isPresent()) {
        BoardStore _boardStore = storeData.get();
        _boardStore.setWriterStudentNo(boardStore.getWriterStudentNo());
        _boardStore.setWriter_name(boardStore.getWriter_name());
        _boardStore.setTitle(boardStore.getTitle());
        _boardStore.setContent(boardStore.getContent());
        return new ResponseEntity<>(boardStoreRepository.save(_boardStore), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @DeleteMapping("/board-store/{id}")
    public ResponseEntity<HttpStatus> deleteBoardStore(@PathVariable("id") long id) {
      try {
        boardStoreRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @DeleteMapping("/board-store")
    public ResponseEntity<HttpStatus> deleteAllBoardStores() {
      try {
        boardStoreRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
}