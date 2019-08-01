package com.flowjava.dao;

import com.flowjava.entity.Activity;
import com.flowjava.entity.Arrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArrowDao extends JpaRepository<Arrow, Integer> {


    List<Arrow> findAllByActivitiesNext(List<Activity> activities);
}
