package com.flowengine.dao;

import com.flowengine.entity.Variable;
import com.flowengine.entity.VariableFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VariableFileDao extends JpaRepository<VariableFile, UUID> {


}
