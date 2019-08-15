package com.flowengine.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flowengine.entity.*;
import com.flowengine.entity.helper.HelperVariableFile;
import com.flowengine.service.*;
import com.flowengine.shared.EnumsApp;
import com.flowengine.shared.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    VariableOptionValueService variableOptionValueService;

    @Autowired
    ConditionService conditionService;

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
            List<Activity> activities = this._businessActivity.getMyActiveActivityByGroupId(activityList, groupId);

            for (Activity item:
                    activities) {

                for (Variable iter:
                     item.getVariables()) {

                    List<VariableOptionValue> list =
                            this.variableOptionValueService.findAllByVariableId(iter.getId());

                    if (list != null && list.size() > 0) {
                        iter.setVariableOptionValues(list);
                    }
                }

                if (item.getTag() != EnumsApp.enumActivityTags.end.ordinal()) {
                    arrows = new ArrayList<>();
                    List<Activity> tmpActivities = new ArrayList<>();
                    tmpActivities.add(item);
                    arrows = this.arrowService.findAllByActivityCurrent(tmpActivities);

                    for (Arrow elem:
                            arrows) {

                        List<Condition> conditions = this.conditionService.findAllByArrowId(elem.getId());
                        elem.setCondition(conditions);
                    }

                    responseService.setMultiObject(true);
                    responseService.append_objects("Arrows_" + item.getId(), arrows);
                }
            }

            responseService.setData(activities);

        } catch (Exception e) {
            responseService.setStatus(false);
            responseService.setMessage(e.getMessage());

        }
        return responseService;
    }


    @RequestMapping(
            value = {"/excuteActivity/{activityId}/{multiArrow}/{variableCondition}" ,
                    "/excuteActivity/{activityId}/{multiArrow}"  },
            method = RequestMethod.POST
    )
    public ResponseService excuteActivity(@Valid @RequestBody String activity, @PathVariable UUID activityId, @PathVariable boolean multiArrow, @PathVariable Optional<UUID> variableCondition) {
        ResponseService responseService = new ResponseService();
        try{
            Boolean condition = true;
            ObjectMapper mapper = new ObjectMapper();
            Activity value = mapper.readValue(activity, Activity.class);


            for (Variable item : value.getVariables()) {
                Variable variable = this.variableService.saveVaiable(item);
            }
            value.setStatus(EnumsApp.enumActivityStatus.inactive.ordinal());
            Activity activity1 = this.activityService.saveActivity(value);

            List<Activity> activityList = this.activityService.findAllByProcessId(value.getProcessId());
            List<Activity> activities = this._businessActivity.getOtherActivitiesActivity(activityList, value.getId());

            if (variableCondition.isPresent()) {

                Optional<Variable> variable = this.variableService.findById(variableCondition.get());

                if ( variable.isPresent() ) {

                    String str = variable.get().getValue();

                    if ( str != null && !str.isEmpty() && ( str.equalsIgnoreCase("true") || str.equalsIgnoreCase("false") )) {
                        condition =  Boolean.valueOf(str);
                    } else {
                        condition = false;
                    }
                }
            }

            if (condition) {

                Optional<Activity> activity2  = this.activityService.findActivityById(activityId);
                if (activity2 != null) {

                    activity2.get().setStatus(EnumsApp.enumActivityStatus.active.ordinal());
                    this.activityService.saveActivity(activity2.get());
                }

//                if (activities.size() == 0 && !multiArrow) {
//
//                    Optional<Activity> activity2  = this.activityService.findActivityById(activityId);
//
//                    if (activity2 != null) {
//                        activity2.get().setStatus(EnumsApp.enumActivityStatus.active.ordinal());
//                        this.activityService.saveActivity(activity2.get());
//                    }
//                } else if (multiArrow) {
//                    Optional<Activity> activity2  = this.activityService.findActivityById(activityId);
//
//                    if (activity2 != null) {
//                        activity2.get().setStatus(EnumsApp.enumActivityStatus.active.ordinal());
//                        this.activityService.saveActivity(activity2.get());
//                    }
//                }
            }


            responseService.setData(activity1);
        } catch (Exception e) {
            responseService.setStatus(false);
            responseService.setMessage(e.getMessage());
        }
        return responseService;
    }


    @PostMapping(value = "/addVariableFile")
    public ResponseService addFiles(@RequestParam("file") MultipartFile file, @RequestParam("helperVariableFile") String helperVariableFile) {
        ResponseService responseService = new ResponseService();
        try{
            ObjectMapper mapper = new ObjectMapper();
            HelperVariableFile helperVariableFileObj = mapper.readValue(helperVariableFile, HelperVariableFile.class);
            VariableFile variableFile = this.activityService.addFile(helperVariableFileObj, file);
            responseService.setData(variableFile);


        } catch (Exception e) {
            responseService.setStatus(false);
            responseService.setMessage(e.getMessage());
        }

        return responseService;

    }
}
