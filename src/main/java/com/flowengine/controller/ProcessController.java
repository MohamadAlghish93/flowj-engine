package com.flowengine.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flowengine.entity.Activity;
import com.flowengine.entity.Arrow;
import com.flowengine.entity.Process;
import com.flowengine.service.ActivityService;
import com.flowengine.service.ArrowService;
import com.flowengine.service.ProcessService;
import com.flowengine.service.VariableService;
import com.flowengine.shared.ConstantsApp;
import com.flowengine.shared.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/process")
public class ProcessController {

    @Autowired
    ProcessService processService;

    @Autowired
    ActivityService activityService;

    @Autowired
    VariableService variableService;

    @Autowired
    ArrowService arrowService;

    @RequestMapping(
            value = "/add",
            method = RequestMethod.POST)
    public ResponseService createNewProcess(@Valid @RequestBody Process process) {
        ResponseService responseService = new ResponseService();
        try{
            Process processCreated =  this.processService.addProcess(process);

            responseService.setData(processCreated);

        } catch (Exception e) {
            responseService.setResponseCode(ConstantsApp.NOT_FOUND);
            responseService.setStatus(false);
            responseService.setMessage(e.getMessage());
        }
        return responseService;
    }


    //with object
    @RequestMapping(
            value = "/addProcess",
            method = RequestMethod.POST
            )
    public ResponseService createNewProcessString(@Valid @RequestBody String process) {
        ResponseService responseService = new ResponseService();
        try{
            ObjectMapper mapper = new ObjectMapper();
            Process projectObj = mapper.readValue(process, Process.class);


            for (Activity var : projectObj.getActivities()) {
                this.variableService.saveAllVariables(var.getVariables());

                var.setProcessId(projectObj.getId());
                Activity activity = this.activityService.saveActivity(var);
            }

            for (Arrow var : projectObj.getArrows()) {
                var.setProcessId(projectObj.getId());
                Arrow arrow = this.arrowService.addArrow(var);
            }

            Process addedprocess =  this.processService.addProcess(projectObj);

            responseService.setData(addedprocess);
        } catch (Exception e) {
            responseService.setStatus(false);
            responseService.setMessage(e.getMessage());
        }
        return responseService;
    }

    @RequestMapping(
            value = {"/list"},
            method = {RequestMethod.GET}
    )
    public ResponseService  getListProcess() {
        ResponseService responseService = new ResponseService();
        try {
            List<Process> processes = this.processService.listProcess();

            responseService.setData(processes);

        } catch (Exception e) {
            responseService.setStatus(false);
            responseService.setMessage(e.getMessage());

        }
        return responseService;
    }

}
