package com.flowjava.controller;

import com.flowjava.entity.GroupUser;
import com.flowjava.service.GroupUserService;
import com.flowjava.shared.ConstantsApp;
import com.flowjava.shared.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/group")
public class GroupUserController {

    @Autowired
    GroupUserService groupUserService;

    @RequestMapping(
            value = "/add",
            method = RequestMethod.POST)
    public ResponseService createNewGroup(@Valid @RequestBody GroupUser groupUser) {
        ResponseService responseService = new ResponseService();
        try{
            GroupUser groupUser1 =  this.groupUserService.addGroup(groupUser);

            responseService.setData(groupUser1);

        } catch (Exception e) {
            responseService.setResponseCode(ConstantsApp.NOT_FOUND);
            responseService.setStatus(false);
            responseService.setMessage(e.getMessage());
        }
        return responseService;
    }
}
