package com.flowengine.service;

import com.flowengine.dao.GroupUserDao;
import com.flowengine.entity.GroupUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GroupUserService {

    @Autowired
    GroupUserDao groupUserDao;

    public GroupUser saveGroup(GroupUser groupUser) {

        return this.groupUserDao.save(groupUser);
    }

    public Page<GroupUser> findAll(Pageable pageable) {

        return this.groupUserDao.findAll(pageable);
    }
}
