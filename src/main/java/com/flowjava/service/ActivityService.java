package com.flowjava.service;

import com.flowjava.dao.ActivityDao;
import com.flowjava.entity.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ActivityService {

    @Autowired
    ActivityDao activityDao;

    public Activity saveActivity(Activity activity) {
        return this.activityDao.save(activity);
    }

    public Optional<Activity> findActivityById(UUID id) {
        return this.activityDao.findById(id);
    }

    public List<Activity> findAllByProcessId(UUID processId) {
        return this.activityDao.findAllByProcessId(processId);
    }
}
