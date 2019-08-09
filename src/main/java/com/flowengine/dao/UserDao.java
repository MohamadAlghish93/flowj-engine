package com.flowengine.dao;

import com.flowengine.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDao extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(String email);

}
