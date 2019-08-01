package com.flowjava.dao;

import com.flowjava.entity.Process;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessDao extends JpaRepository<Process, Integer> {
}
