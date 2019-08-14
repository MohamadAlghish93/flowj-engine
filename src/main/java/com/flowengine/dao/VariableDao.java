package com.flowengine.dao;

import com.flowengine.entity.Variable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VariableDao extends JpaRepository<Variable, UUID> {


}
