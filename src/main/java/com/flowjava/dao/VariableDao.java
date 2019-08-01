package com.flowjava.dao;

import com.flowjava.entity.Variable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariableDao extends JpaRepository<Variable, Integer> {
}
