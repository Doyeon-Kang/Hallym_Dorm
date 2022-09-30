package com.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.BoardStore;


public interface BoardStoreRepository extends JpaRepository<BoardStore, Long>  {
    List<BoardStore> findByTitleContaining(String title);
}
