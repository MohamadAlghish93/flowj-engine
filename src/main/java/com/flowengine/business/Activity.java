package com.flowengine.business;

import com.flowengine.entity.GroupUser;
import com.flowengine.shared.EnumsApp;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Activity {

    public Activity() {
    }

    public List<com.flowengine.entity.Activity> getMyActiveActivityByGroupId(List<com.flowengine.entity.Activity> activities, UUID groupId) {

        List<com.flowengine.entity.Activity> list = new ArrayList<>();
        for (com.flowengine.entity.Activity item: activities) {

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


    public List<com.flowengine.entity.Activity> getOtherActivitiesActive(List<com.flowengine.entity.Activity> activities, UUID activityId) {

        List<com.flowengine.entity.Activity> list = new ArrayList<>();
        for (com.flowengine.entity.Activity item: activities) {

            if ( item.getStatus() == EnumsApp.enumActivityStatus.active.ordinal()
                    && activityId != item.getId()  ) {

                list.add(item);
            }

        }

        return list;

    }


}
