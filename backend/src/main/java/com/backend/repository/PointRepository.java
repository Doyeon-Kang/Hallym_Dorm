package com.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.Point;

public interface PointRepository extends JpaRepository<Point, Long>  {
    List<Point> findByUserMemberId(Long userMemberId);
}
