package com.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.UserMember;

public interface UserMemberRepository extends JpaRepository<UserMember, Long> {
    Optional<UserMember> findByUserId(Long userId);
}
