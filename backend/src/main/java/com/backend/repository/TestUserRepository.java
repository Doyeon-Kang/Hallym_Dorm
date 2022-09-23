package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.model.TestUser;

public interface TestUserRepository extends JpaRepository<TestUser, Long>  {
    
}
