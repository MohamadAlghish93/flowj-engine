package com.flowjava.dao;

import com.flowjava.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ActivityDao extends JpaRepository<Activity, UUID> {
    List<Activity> findAllByProcessId(UUID processId);
}
