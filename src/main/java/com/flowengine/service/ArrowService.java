package com.flowengine.service;

import com.flowengine.dao.ArrowDao;
import com.flowengine.entity.Activity;
import com.flowengine.entity.Arrow;
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
