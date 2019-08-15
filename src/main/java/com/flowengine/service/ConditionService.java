package com.flowengine.service;

import com.flowengine.dao.ConditionDao;
import com.flowengine.entity.Condition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ConditionService {

    @Autowired
    ConditionDao conditionDao;

    public Condition save(Condition condition) {

        return this.conditionDao.save(condition);
    }

    public List<Condition> findAllByArrowId(UUID uuid) {

        return this.conditionDao.findAllByArrowId(uuid);
    }
}
