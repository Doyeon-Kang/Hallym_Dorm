package com.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.BoardNotice;


public interface BoardStoreRepository extends JpaRepository<BoardNotice, Long>  {
    List<BoardNotice> findByTitleContaining(String title);
}
