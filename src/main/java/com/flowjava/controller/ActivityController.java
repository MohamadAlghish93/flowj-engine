package com.flowjava.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flowjava.entity.Activity;
import com.flowjava.entity.Arrow;
import com.flowjava.entity.Variable;
import com.flowjava.service.ActivityService;
import com.flowjava.service.ArrowService;
import com.flowjava.service.VariableService;
import com.flowjava.shared.EnumsApp;
import com.flowjava.shared.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    @Autowired
    ArrowService arrowService;

    @Autowired
    ActivityService activityService;

    @Autowired
    VariableService variableService;

    private com.flowjava.business.Activity _businessActivity;

    public ActivityController() {
        this._businessActivity = new com.flowjava.business.Activity();
    }

    @RequestMapping(
            value = {"getMyActivity/{processId}/{groupId}"},
            method = {RequestMethod.GET}
    )
    public ResponseService getMyActivity(@PathVariable UUID processId, @PathVariable UUID groupId) {
        ResponseService responseService = new ResponseService();
        try {

            List<Arrow> arrows = new ArrayList<>();
            List<Activity> activityList = this.activityService.findAllByProcessId(processId);
            Activity activity = this._businessActivity.getMyActiveActivityByGroupId(activityList, groupId);

            responseService.setData(activity);


            if (activity.getTag() != EnumsApp.enumActivityTags.end.ordinal()) {
                List<Activity> tmpActivities = new ArrayList<>();
                tmpActivities.add(activity);
                arrows = this.arrowService.findAllByActivityCurrent(tmpActivities);

                responseService.setMultiObject(true);
                responseService.append_objects("Arrows", arrows);
            }

        } catch (Exception e) {
            responseService.setStatus(false);
            responseService.setMessage(e.getMessage());

        }
        return responseService;
    }


    @RequestMapping(
            value = "/excuteActivity/{activityId}",
            method = RequestMethod.POST
    )
    public ResponseService excuteActivity(@Valid @RequestBody String activity, @PathVariable UUID activityId) {
        ResponseService responseService = new ResponseService();
        try{
            ObjectMapper mapper = new ObjectMapper();
            Activity value = mapper.readValue(activity, Activity.class);


            for (Variable item : value.getVariables()) {
                Variable variable = this.variableService.saveVaiable(item);
            }
            value.setStatus(EnumsApp.enumActivityStatus.inactive.ordinal());
            Activity activity1 = this.activityService.saveActivity(value);

            Optional<Activity> activity2  = this.activityService.findActivityById(activityId);

            if (activity2 != null) {
                activity2.get().setStatus(EnumsApp.enumActivityStatus.active.ordinal());
                this.activityService.saveActivity(activity2.get());
            }


            responseService.setData(activity1);
        } catch (Exception e) {
            responseService.setStatus(false);
            responseService.setMessage(e.getMessage());
        }
        return responseService;
    }
}
