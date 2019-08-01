package com.flowjava.service;

import com.flowjava.dao.ProcessDao;
import com.flowjava.entity.Process;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessService {

    @Autowired
    ProcessDao processDao;


    public Process addProcess(Process process) {

        return this.processDao.save(process);
    }

}
