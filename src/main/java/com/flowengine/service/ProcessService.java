package com.flowengine.service;

import com.flowengine.dao.ProcessDao;
import com.flowengine.entity.Process;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessService {

    @Autowired
    ProcessDao processDao;


    public Process addProcess(Process process) {

        return this.processDao.saveAndFlush(process);
    }

    public List<Process> listProcess() {
        return this.processDao.findAll();
    }

}
