package com.flowjava.dao;

import com.flowjava.entity.GroupUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupUserDao extends JpaRepository<GroupUser, Integer> {
}
