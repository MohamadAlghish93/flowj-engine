package com.flowengine.dao;

import com.flowengine.entity.Activity;
import com.flowengine.entity.Arrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArrowDao extends JpaRepository<Arrow, Integer> {


    List<Arrow> findAllByActivityCurrent(List<Activity> activities);
}
