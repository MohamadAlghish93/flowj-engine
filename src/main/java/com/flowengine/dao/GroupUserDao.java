package com.flowengine.dao;

import com.flowengine.entity.GroupUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GroupUserDao extends JpaRepository<GroupUser, UUID> {
}
