package com.flowengine.service;

import com.flowengine.dao.VariableDao;
import com.flowengine.entity.Variable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VariableService {

    @Autowired
    VariableDao variableDao;

    public Variable saveVaiable(Variable variable) {

        return this.variableDao.save(variable);
    }

    public Optional<Variable> findById(UUID uuid) {

        return this.variableDao.findById(uuid);
    }

    public List<Variable> saveAllVariables(List<Variable> variables) {

        return this.variableDao.saveAll(variables);
    }
}
