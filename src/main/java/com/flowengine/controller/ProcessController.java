package com.flowengine.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flowengine.entity.Activity;
import com.flowengine.entity.Arrow;
import com.flowengine.entity.Process;
import com.flowengine.entity.Variable;
import com.flowengine.service.ActivityService;
import com.flowengine.service.ArrowService;
import com.flowengine.service.ProcessService;
import com.flowengine.service.VariableService;
import com.flowengine.shared.ConstantsApp;
import com.flowengine.shared.HelperObj;
import com.flowengine.shared.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

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
    public ResponseService saveProcessString(@Valid @RequestBody String process) {
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
            value = "/addProcessTemplate",
            method = RequestMethod.GET
    )
    public ResponseService saveProcessTemplate() {
        ResponseService responseService = new ResponseService();
        try{
            Path path = Paths.get(Paths.get("").toAbsolutePath().toString() + "/flow-sanad-template.json");
            String process = HelperObj.readFileAsString(path.toString());
            ObjectMapper mapper = new ObjectMapper();
            Process projectObj = mapper.readValue(process, Process.class);

            Map<UUID, UUID> activityGuid = new HashMap<UUID, UUID>();
            Map<UUID, UUID> variableyGuid = new HashMap<UUID, UUID>();
            List<String> arrowGuid = new ArrayList<>();

            projectObj.setId(HelperObj.GenerateUUID());

            for (Arrow var : projectObj.getArrows()) {

                var.setId(HelperObj.GenerateUUID());

                for ( Activity item: var.getActivitiesNext()) {

                    if (!activityGuid.containsKey(item.getId())) {
                        UUID orginalId = item.getId();
                        UUID UUID = HelperObj.GenerateUUID();
                        item.setId(UUID);

                        for (Activity iter : projectObj.getActivities()) {
                            if (iter.getId().equals(orginalId)) {
                                iter.setId(UUID);
                            }
                        }
                        activityGuid.put(orginalId, UUID);
                    }
                }
            }

            for (Arrow var : projectObj.getArrows()) {

                for ( Activity item: var.getActivitiesNext()) {
                    if (activityGuid.containsKey(item.getId())) {
                        item.setId(activityGuid.get(item.getId()));
                    }
                }

                for ( Activity item: var.getActivitiesPrev()) {
                    if (activityGuid.containsKey(item.getId())) {
                        item.setId(activityGuid.get(item.getId()));
                    } else {
                        UUID orginalId = item.getId();
                        UUID UUID = HelperObj.GenerateUUID();
                        item.setId(UUID);

                        for (Activity iter : projectObj.getActivities()) {
                            if (iter.getId().equals(orginalId)) {
                                iter.setId(UUID);
                            }
                        }
                        activityGuid.put(orginalId, UUID);
                    }
                }

                for ( Activity item: var.getActivityCurrent()) {

                    if (activityGuid.containsKey(item.getId())) {
                        item.setId(activityGuid.get(item.getId()));
                    } else {
                        UUID orginalId = item.getId();
                        UUID UUID = HelperObj.GenerateUUID();
                        item.setId(UUID);

                        for (Activity iter : projectObj.getActivities()) {
                            if (iter.getId().equals(orginalId)) {
                                iter.setId(UUID);
                            }
                        }
                        activityGuid.put(orginalId, UUID);
                    }
                }
            }

            for (Activity var : projectObj.getActivities()) {
                for (Variable item : var.getVariables()) {

                    UUID UUID = HelperObj.GenerateUUID();

                    if (variableyGuid.containsKey(item.getId())) {
                        item.setId(variableyGuid.get(item.getId()));
                    } else {
                        variableyGuid.put(item.getId(), UUID);
                        item.setId(UUID);
                    }

                }
            }


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
