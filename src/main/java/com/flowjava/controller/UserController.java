package com.flowjava.controller;


import com.flowjava.entity.UserEntity;
import com.flowjava.service.UserService;
import com.flowjava.shared.ConstantsApp;
import com.flowjava.shared.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(
            value = "/create_user",
            method = RequestMethod.POST)
    public ResponseService createNewUser(@Valid @RequestBody UserEntity userEntity) {
        ResponseService responseService = new ResponseService();
        try{
            UserEntity userEntityCreated =  this.userService.createUser(userEntity);

            responseService.setData(userEntityCreated);

        } catch (Exception e) {
            responseService.setResponseCode(ConstantsApp.NOT_FOUND);
            responseService.setStatus(false);
            responseService.setMessage(e.getMessage());
        }
        return responseService;
    }
}
