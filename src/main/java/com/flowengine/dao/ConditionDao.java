package com.flowengine.dao;

import com.flowengine.entity.Condition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ConditionDao extends JpaRepository<Condition, UUID> {

    List<Condition> findAllByArrowId(UUID uuid);
}
