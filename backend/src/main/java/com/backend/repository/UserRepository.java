package com.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByStudentno(String studentno);
    
    Boolean existsByStudentno(String studentno);

    Boolean existsByEmail(String email);
}
