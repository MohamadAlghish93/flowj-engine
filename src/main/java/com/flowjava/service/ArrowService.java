package com.flowjava.service;

import com.flowjava.dao.ArrowDao;
import com.flowjava.dao.VariableDao;
import com.flowjava.entity.Arrow;
import com.flowjava.entity.Variable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArrowService {

    @Autowired
    ArrowDao arrowDao;

    public Arrow addArrow(Arrow arrow) {

        return this.arrowDao.save(arrow);
    }
}
