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

import com.backend.model.board.BoardLost;
import com.backend.model.board.LostPhoto;
import com.backend.payload.response.MessageResponse;
import com.backend.payload.response.PhotoResponse;
import com.backend.repository.board.BoardLostRepository;
import com.backend.repository.board.LostPhotoRepository;

@RestController
@RequestMapping(path="/api")
public class LostPhotoController {
    @Autowired
    BoardLostRepository boardLostRepository;

    @Autowired
    LostPhotoRepository lostPhotoRepository;

    @PostMapping(value = "/board-lost/{lostId}/upload")
    public ResponseEntity<MessageResponse> uploadPhotos(@PathVariable(name="lostId") Long lostId, @RequestParam("photos") MultipartFile[] photos) {
        String message = "";
        try {
            BoardLost _boardLost = boardLostRepository.findById(lostId).get();

            List<String> photoNames = new ArrayList<>();

            Arrays.asList(photos).stream().forEach(photo -> {
                String photoName = StringUtils.cleanPath(photo.getOriginalFilename());
                LostPhoto _lostPhoto = new LostPhoto();
                _lostPhoto.setName(photoName);
                _lostPhoto.setType(photo.getContentType());
                try {
                    _lostPhoto.setData(photo.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                _lostPhoto.setBoardLost(_boardLost);
                lostPhotoRepository.save(_lostPhoto);
                photoNames.add(photoName);
            });
            message = "Uploaded the files successfully: " + photoNames;
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
        } catch (Exception e) {
            message = "Failed to upload files!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(message));
        }
    }
    
    @GetMapping("/board-lost/{lostId}/photos")
    public ResponseEntity<List<PhotoResponse>> getListPhotos(@PathVariable(name = "lostId") Long lostId) {
        List<LostPhoto> lostPhotos = lostPhotoRepository.findByBoardLostId(lostId);
        List<PhotoResponse> lostPhotoResponses = new ArrayList<>();
        lostPhotos.forEach(photo -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                                        .fromCurrentContextPath()
                                        .path("api/board-lost/" + lostId + "/photo/")
                                        .path(photo.getId())
                                        .toUriString();
            photo.setUrl(fileDownloadUri);
            lostPhotoRepository.save(photo);
            PhotoResponse response = new PhotoResponse(photo.getId(), photo.getType(), photo.getUrl());
            lostPhotoResponses.add(response);
        });
        try {
            if(lostPhotos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(lostPhotoResponses, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/board-lost/{lostId}/photo/{photoId}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable(name = "lostId") Long lostId, @PathVariable(name = "photoId") String photoId) {
        LostPhoto _lostPhoto = lostPhotoRepository.findById(photoId);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + _lostPhoto.getName() + "\"")
            .body(_lostPhoto.getData());
    }

    @DeleteMapping("/board-lost/{lostId}/photo/{photoId}")
    public ResponseEntity<HttpStatus> deletePhoto(@PathVariable(name = "lostId") Long lostId, @PathVariable(name = "photoId") String photoId) {
        try {
            lostPhotoRepository.deleteById(photoId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
