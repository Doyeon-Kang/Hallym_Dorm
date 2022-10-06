package com.backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.backend.model.BoardStore;
import com.backend.payload.response.MessageResponse;
import com.backend.payload.response.StoreResponseFile;
import com.backend.repository.BoardStoreRepository;
import com.backend.service.BoardStoreService;


@RestController
@RequestMapping(path="/api")
public class BoardStoreController {
    @Autowired
    BoardStoreService boardStoreService;

    @GetMapping(path="/stores")
    public ResponseEntity<List<StoreResponseFile>> getAllBoardStore() {
        List<StoreResponseFile> files = boardStoreService.getAllStores().map(store -> {
          String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("api/files/")
                .path((store.getId()).toString())
                .toUriString();

          return new StoreResponseFile(
            store.getWriter_username(),
            store.getWriter_name(),
            store.getTitle(),
            store.getContents(),
            fileDownloadUri);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

<<<<<<< HEAD
    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getPhotoStoreById(@PathVariable("id") long id) {
      BoardStore _boardStore = boardStoreService.getStore(id);

      return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + _boardStore.getTitle() + "\"")
            .body(_boardStore.getPhoto());
=======
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
>>>>>>> 0638bbf13e6b91409d07f8749726ce03a8d75a54
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
        boardStoreService.store(store, photo);

        message = "Uploaded the file successfully: " + photo.getOriginalFilename();
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
      } catch (Exception e) {
        message = "Could not upload the file: " + photo.getOriginalFilename() + "!";
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(message));
      }
    }
    // @PutMapping("/stores/{id}")
    // public ResponseEntity<BoardStore> updateBoardStore(@PathVariable("id") long id, @RequestBody BoardStore boardStore) {
    //   Optional<BoardStore> storeData = boardStoreRepository.findById(id);

    //   if (storeData.isPresent()) {
    //     BoardStore _boardStore = storeData.get();
    //     _boardStore.setWriter_username(boardStore.getWriter_username());
    //     _boardStore.setWriter_name(boardStore.getWriter_name());
    //     _boardStore.setTitle(boardStore.getTitle());
    //     _boardStore.setContents(boardStore.getContents());
    //     _boardStore.setPhoto(boardStore.getPhoto());
    //     _boardStore.setPhotoName(boardStore.getPhotoName());
    //     return new ResponseEntity<>(boardStoreRepository.save(_boardStore), HttpStatus.OK);
    //   } else {
    //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //   }
    // }

    // @DeleteMapping("/stores/{id}")
    // public ResponseEntity<HttpStatus> deleteBoardStore(@PathVariable("id") long id) {
    //   try {
    //     boardStoreRepository.deleteById(id);
    //     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //   } catch (Exception e) {
    //     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    //   }
    // }

    // @DeleteMapping("/stores")
    // public ResponseEntity<HttpStatus> deleteAllBoardStores() {
    //   try {
    //     boardStoreRepository.deleteAll();
    //     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //   } catch (Exception e) {
    //     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    //   }

    // }


}
