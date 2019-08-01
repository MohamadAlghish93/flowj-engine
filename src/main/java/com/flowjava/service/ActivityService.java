package com.flowjava.service;

import com.flowjava.dao.ActivityDao;
import com.flowjava.dao.GroupUserDao;
import com.flowjava.entity.Activity;
import com.flowjava.entity.GroupUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ActivityService {

    @Autowired
    ActivityDao activityDao;

    public Activity addActivity(Activity activity) {

        return this.activityDao.save(activity);
    }
    public List<Activity> findAllByProcessId(UUID processId) {
        return this.activityDao.findAllByProcessId(processId);
    }
}
