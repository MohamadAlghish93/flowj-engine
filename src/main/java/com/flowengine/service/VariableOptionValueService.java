package com.flowengine.service;

import com.flowengine.dao.VariableOptionValueDao;
import com.flowengine.entity.VariableOptionValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VariableOptionValueService {

    @Autowired
    VariableOptionValueDao variableOptionValueDao;

    public VariableOptionValue save(VariableOptionValue variable) {

        return this.variableOptionValueDao.save(variable);
    }

    public List<VariableOptionValue> findAllByVariableId(UUID variableUuid) {

        return this.variableOptionValueDao.findAllByVariableId(variableUuid);
    }
}
