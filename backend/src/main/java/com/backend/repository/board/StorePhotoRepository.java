package com.backend.repository.board;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.board.StorePhoto;

public interface StorePhotoRepository extends JpaRepository<StorePhoto, Long> {
    List<StorePhoto> findByBoardStoreId(Long storeId);

    StorePhoto findById(String id);

    @Transactional
    void deleteByBoardStoreId(Long storeId);

    @Transactional
    void deleteById(String id);
}
