package com.flowjava.business;

import com.flowjava.entity.GroupUser;
import com.flowjava.shared.EnumsApp;

import java.util.List;
import java.util.UUID;

public class Activity {

    public Activity() {
    }

    public com.flowjava.entity.Activity getMyActiveActivityByGroupId(List<com.flowjava.entity.Activity> activities, UUID groupId) {

        for (com.flowjava.entity.Activity item: activities) {

            GroupUser equalGroup = item.getGroupUsers().stream()
                    .filter(e -> e.getId() .equals(groupId)).findAny()
                    .orElse(null);
            if ( item.getStatus() == EnumsApp.enumActivityStatus.active.ordinal()
                    && equalGroup != null  ) {

                return item;

            }

        }

        return null;

    }


}
