package com.flowjava.service;

import com.flowjava.dao.ProcessDao;
import com.flowjava.entity.Process;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessService {

    @Autowired
    ProcessDao processDao;


    public Process addProcess(Process process) {

        return this.processDao.save(process);
    }

    public List<Process> listProcess() {
        return this.processDao.findAll();
    }

}
