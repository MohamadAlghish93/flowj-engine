package com.flowengine.service;

import com.flowengine.dao.GroupUserDao;
import com.flowengine.entity.GroupUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public List<GroupUser> findAll() {

        return this.groupUserDao.findAll();
    }

    public Optional<GroupUser> findById(UUID uuid) {

        return this.groupUserDao.findById(uuid);
    }

    public boolean deleteById(UUID uuid) {

        try {
            this.groupUserDao.deleteById(uuid);

            return true;
        } catch (Exception e) {
            throw e;
        }
    }
}
