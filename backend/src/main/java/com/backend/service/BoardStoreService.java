package com.backend.service;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.backend.model.BoardStore;
import com.backend.repository.BoardStoreRepository;

@Service
public class BoardStoreService {
    @Autowired
    private BoardStoreRepository boardStoreRepository;

    public BoardStore store(BoardStore store, MultipartFile file) throws IOException {
        BoardStore _boardStore = new BoardStore(store.getWriter_studentno(), store.getWriter_name(),
                                                store.getTitle(), store.getContents());
        _boardStore.setPhoto(file.getBytes());
        return boardStoreRepository.save(_boardStore); 
    }

    public BoardStore getStore(Long id) {
        return boardStoreRepository.findById(id).get();
    }

    public Stream<BoardStore> getAllStores() {
        return boardStoreRepository.findAll().stream();
    }
}
