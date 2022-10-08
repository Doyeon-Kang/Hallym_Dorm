package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.BoardStore;


public interface BoardStoreRepository extends JpaRepository<BoardStore, Long>  {
    
}
