package com.flowjava.service;

import com.flowjava.dao.ActivityDao;
import com.flowjava.dao.VariableDao;
import com.flowjava.entity.Activity;
import com.flowjava.entity.Variable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VariableService {

    @Autowired
    VariableDao variableDao;

    public Variable saveVaiable(Variable variable) {

        return this.variableDao.save(variable);
    }

    public List<Variable> saveAllVariables(List<Variable> variables) {

        return this.variableDao.saveAll(variables);
    }
}
