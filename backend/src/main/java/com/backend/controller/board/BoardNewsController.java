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

import com.backend.model.board.BoardNews;
import com.backend.payload.request.BoardRequest;
import com.backend.repository.board.BoardNewsRepository;

@RestController
@RequestMapping(path="/api")
public class BoardNewsController {
    @Autowired
    BoardNewsRepository boardNewsRepository;

    @GetMapping(path="/board-news")
    public ResponseEntity<List<BoardNews>> getAllBoardNews() {
        try {
          List<BoardNews> boardNews = new ArrayList<BoardNews>();

          boardNewsRepository.findAll().forEach(boardNews::add);
          if(boardNews.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          }

          return new ResponseEntity<>(boardNews, HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/board-news/{id}")
    public ResponseEntity<BoardNews> getBoardNewsById(@PathVariable("id") long id) {
      Optional<BoardNews> newsData = boardNewsRepository.findById(id);

      if(newsData.isPresent()) {
        BoardNews _boardNews = newsData.get();
        int views = _boardNews.getViews() + 1;
        _boardNews.setViews(views);
        return new ResponseEntity<>(boardNewsRepository.save(_boardNews), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @GetMapping("/board-news/my-news")
    public ResponseEntity<List<BoardNews>> getMyBoardNews (@RequestBody BoardRequest boardRequest) {
      try{
        List <BoardNews> myNewss = boardNewsRepository.findByWriterStudentNo(boardRequest.getStudentNo());

        if(myNewss.isEmpty()){
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(myNewss, HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }        


    @PostMapping("/board-news")
    public ResponseEntity<BoardNews> createBoardNews(@RequestBody BoardNews boardNews) {
      try {
        BoardNews _boardNews = boardNewsRepository
                    .save(new BoardNews(boardNews.getWriterStudentNo(), boardNews.getWriter_name(), boardNews.getTitle(), boardNews.getContent()));
        return new ResponseEntity<>(_boardNews, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    @PutMapping("/board-news/{id}")
    public ResponseEntity<BoardNews> updateBoardNews(@PathVariable("id") long id, @RequestBody BoardNews boardNews) {
      Optional<BoardNews> newsData = boardNewsRepository.findById(id);

      if (newsData.isPresent()) {
        BoardNews _boardNews = newsData.get();
        _boardNews.setWriterStudentNo(boardNews.getWriterStudentNo());
        _boardNews.setWriter_name(boardNews.getWriter_name());
        _boardNews.setTitle(boardNews.getTitle());
        _boardNews.setContent(boardNews.getContent());
        return new ResponseEntity<>(boardNewsRepository.save(_boardNews), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @DeleteMapping("/board-news/{id}")
    public ResponseEntity<HttpStatus> deleteBoardNews(@PathVariable("id") long id) {
      try {
        boardNewsRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @DeleteMapping("/board-news")
    public ResponseEntity<HttpStatus> deleteAllBoardNewss() {
      try {
        boardNewsRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }

    }
}

