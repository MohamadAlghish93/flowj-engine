package com.flowengine.dao;

import com.flowengine.entity.Variable;
import com.flowengine.entity.VariableOptionValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VariableOptionValueDao extends JpaRepository<VariableOptionValue, UUID> {

    List<VariableOptionValue> findAllByVariableId(UUID variableUuid);

}
