package com.wheelymammoth.repository;

import com.wheelymammoth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    
    Optional<UserEntity> findByStudentId(String studentId);
    
    boolean existsByStudentId(String studentId);
    
    boolean existsByEmail(String email);
}

