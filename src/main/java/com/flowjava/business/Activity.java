package com.flowjava.business;

import com.flowjava.entity.GroupUser;
import com.flowjava.shared.EnumsApp;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Activity {

    public Activity() {
    }

    public List<com.flowjava.entity.Activity> getMyActiveActivityByGroupId(List<com.flowjava.entity.Activity> activities, UUID groupId) {

        List<com.flowjava.entity.Activity> list = new ArrayList<>();
        for (com.flowjava.entity.Activity item: activities) {

            GroupUser equalGroup = item.getGroupUsers().stream()
                    .filter(e -> e.getId() .equals(groupId)).findAny()
                    .orElse(null);
            if ( item.getStatus() == EnumsApp.enumActivityStatus.active.ordinal()
                    && equalGroup != null  ) {

                list.add(item);
            }

        }

        return list;

    }


    public List<com.flowjava.entity.Activity> getOtherActivitiesActive(List<com.flowjava.entity.Activity> activities, UUID activityId) {

        List<com.flowjava.entity.Activity> list = new ArrayList<>();
        for (com.flowjava.entity.Activity item: activities) {

            if ( item.getStatus() == EnumsApp.enumActivityStatus.active.ordinal()
                    && activityId != item.getId()  ) {

                list.add(item);
            }

        }

        return list;

    }


}
