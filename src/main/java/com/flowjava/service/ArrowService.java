package com.flowjava.service;

import com.flowjava.dao.ArrowDao;
import com.flowjava.dao.VariableDao;
import com.flowjava.entity.Activity;
import com.flowjava.entity.Arrow;
import com.flowjava.entity.Variable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArrowService {

    @Autowired
    ArrowDao arrowDao;

    public Arrow addArrow(Arrow arrow) {

        return this.arrowDao.save(arrow);
    }

    public List<Arrow> findAllByActivityCurrent(List<Activity> activities) {
        return this.arrowDao.findAllByActivityCurrent(activities);
    }
}
