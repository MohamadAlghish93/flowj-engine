package com.flowengine.controller;


import com.flowengine.entity.UserEntity;
import com.flowengine.service.UserService;
import com.flowengine.shared.ConstantsApp;
import com.flowengine.shared.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(
            value = "/save",
            method = RequestMethod.POST)
    public ResponseService saveUser(@Valid @RequestBody UserEntity userEntity) {
        ResponseService responseService = new ResponseService();
        try{
            UserEntity userEntityCreated =  this.userService.saveUser(userEntity);

            responseService.setData(userEntityCreated);

        } catch (Exception e) {
            responseService.setResponseCode(ConstantsApp.NOT_FOUND);
            responseService.setStatus(false);
            responseService.setMessage(e.getMessage());
        }
        return responseService;
    }

    @RequestMapping(
            value = {"get/{groupId}"},
            method = {RequestMethod.GET}
    )
    public ResponseService getById(@PathVariable UUID groupId) {
        ResponseService responseService = new ResponseService();
        try {

            Optional<UserEntity> item = this.userService.findById(groupId);

            responseService.setData(item);

        } catch (Exception e) {
            responseService.setStatus(false);
            responseService.setMessage(e.getMessage());

        }
        return responseService;
    }

    @RequestMapping(
            value = {"delete/{groupId}"},
            method = {RequestMethod.GET}
    )
    public ResponseService deleteById(@PathVariable UUID groupId) {
        ResponseService responseService = new ResponseService();
        try {

            boolean deleted = this.userService.deleteById(groupId);

            responseService.setData(deleted);

        } catch (Exception e) {
            responseService.setStatus(false);
            responseService.setMessage(e.getMessage());

        }
        return responseService;
    }

    @RequestMapping(
            value = {"/list/{page}/{size}"},
            method = {RequestMethod.GET}
    )
    public ResponseService  listPage(@PathVariable int page, @PathVariable int size) {
        ResponseService responseService = new ResponseService();
        try {

            Page result = this.userService.findAll(new PageRequest(page,size));

            responseService.setData(result);

        } catch (Exception e) {
            responseService.setStatus(false);
            responseService.setMessage(e.getMessage());

        }
        return responseService;
    }


    @RequestMapping(
            value = {"/get_by_email/{email}"},
            method = {RequestMethod.GET}
    )
    public ResponseService getByEmail(@PathVariable String email) {
        ResponseService responseService = new ResponseService();
        try {

            Optional<UserEntity> userEntity = this.userService.findByEmail(email);

            responseService.setData(userEntity);

        } catch (Exception e) {
            responseService.setStatus(false);
            responseService.setMessage(e.getMessage());

        }
        return responseService;
    }
}
