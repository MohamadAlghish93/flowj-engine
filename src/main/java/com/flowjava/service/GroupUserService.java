package com.flowjava.service;

import com.flowjava.dao.GroupUserDao;
import com.flowjava.entity.GroupUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupUserService {

    @Autowired
    GroupUserDao groupUserDao;

    public GroupUser addGroup(GroupUser groupUser) {

        return this.groupUserDao.save(groupUser);
    }
}
