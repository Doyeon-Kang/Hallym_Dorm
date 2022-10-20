package com.backend.controller.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.backend.model.board.BoardStore;
import com.backend.model.board.StorePhoto;
import com.backend.payload.response.MessageResponse;
import com.backend.payload.response.PhotoResponse;
import com.backend.repository.board.BoardStoreRepository;
import com.backend.repository.board.StorePhotoRepository;

@RestController
@RequestMapping(path="/api")
public class StorePhotoController {
    @Autowired
    BoardStoreRepository boardStoreRepository;

    @Autowired
    StorePhotoRepository storePhotoRepository;

    @PostMapping(value = "/board-store/{storeId}/upload")
    public ResponseEntity<MessageResponse> uploadPhotos(@PathVariable(name="storeId") Long storeId, @RequestParam("photos") MultipartFile[] photos) {
        String message = "";
        try {
            BoardStore _boardStore = boardStoreRepository.findById(storeId).get();

            List<String> photoNames = new ArrayList<>();

            Arrays.asList(photos).stream().forEach(photo -> {
                String photoName = StringUtils.cleanPath(photo.getOriginalFilename());
                StorePhoto _storePhoto = new StorePhoto();
                _storePhoto.setName(photoName);
                _storePhoto.setType(photo.getContentType());
                try {
                    _storePhoto.setData(photo.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                _storePhoto.setBoardStore(_boardStore);
                storePhotoRepository.save(_storePhoto);
                photoNames.add(photoName);
            });
            message = "Uploaded the files successfully: " + photoNames;
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
        } catch (Exception e) {
            message = "Failed to upload files!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(message));
        }
    }
    
    @GetMapping("/board-store/{storeId}/photos")
    public ResponseEntity<List<PhotoResponse>> getListPhotos(@PathVariable(name = "storeId") Long storeId) {
        List<StorePhoto> storePhotos = storePhotoRepository.findByBoardStoreId(storeId);
        List<PhotoResponse> storePhotoResponses = new ArrayList<>();
        storePhotos.forEach(photo -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                                        .fromCurrentContextPath()
                                        .path("api/board-store/" + storeId + "/photo/")
                                        .path(photo.getId())
                                        .toUriString();
            photo.setUrl(fileDownloadUri);
            storePhotoRepository.save(photo);
            PhotoResponse response = new PhotoResponse(photo.getId(), photo.getType(), photo.getUrl());
            storePhotoResponses.add(response);
        });
        try {
            if(storePhotos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(storePhotoResponses, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/board-store/{storeId}/photo/{photoId}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable(name = "storeId") Long storeId, @PathVariable(name = "photoId") String photoId) {
        StorePhoto _storePhoto = storePhotoRepository.findById(photoId);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + _storePhoto.getName() + "\"")
            .body(_storePhoto.getData());
    }

    @DeleteMapping("/board-store/{storeId}/photo/{photoId}")
    public ResponseEntity<HttpStatus> deletePhoto(@PathVariable(name = "storeId") Long storeId, @PathVariable(name = "photoId") String photoId) {
        try {
            storePhotoRepository.deleteById(photoId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
