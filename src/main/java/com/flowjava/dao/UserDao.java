package com.flowjava.dao;

import com.flowjava.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);

}
