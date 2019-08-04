package com.flowengine.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flowengine.entity.Activity;
import com.flowengine.entity.Arrow;
import com.flowengine.entity.Variable;
import com.flowengine.service.ActivityService;
import com.flowengine.service.ArrowService;
import com.flowengine.service.VariableService;
import com.flowengine.shared.EnumsApp;
import com.flowengine.shared.ResponseService;
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

    private com.flowengine.business.Activity _businessActivity;

    public ActivityController() {
        this._businessActivity = new com.flowengine.business.Activity();
    }

    @RequestMapping(
            value = {"getMyActivity/{processId}/{groupId}"},
            method = {RequestMethod.GET}
    )
    public ResponseService getMyActivity(@PathVariable UUID processId, @PathVariable UUID groupId) {
        ResponseService responseService = new ResponseService();
        try {

            List<Arrow> arrows;
            List<Activity> activityList = this.activityService.findAllByProcessId(processId);
            List<Activity> activity = this._businessActivity.getMyActiveActivityByGroupId(activityList, groupId);

            responseService.setData(activity);

            for (Activity item:
                    activity) {

                if (item.getTag() != EnumsApp.enumActivityTags.end.ordinal()) {
                    arrows = new ArrayList<>();
                    List<Activity> tmpActivities = new ArrayList<>();
                    tmpActivities.add(item);
                    arrows = this.arrowService.findAllByActivityCurrent(tmpActivities);

                    responseService.setMultiObject(true);
                    responseService.append_objects("Arrows_" + item.getId(), arrows);
                }
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

            List<Activity> activityList = this.activityService.findAllByProcessId(value.getProcessId());
            List<Activity> activities = this._businessActivity.getOtherActivitiesActive(activityList, value.getId());

            if (activities.size() == 0) {
                Optional<Activity> activity2  = this.activityService.findActivityById(activityId);

                if (activity2 != null) {
                    activity2.get().setStatus(EnumsApp.enumActivityStatus.active.ordinal());
                    this.activityService.saveActivity(activity2.get());
                }
            }

            responseService.setData(activity1);
        } catch (Exception e) {
            responseService.setStatus(false);
            responseService.setMessage(e.getMessage());
        }
        return responseService;
    }
}
