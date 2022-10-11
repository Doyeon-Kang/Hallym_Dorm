package com.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.BoardLost;


public interface BoardLostRepository extends JpaRepository<BoardLost, Long>  {
    List<BoardLost> findByTitleContaining(String title);
}
