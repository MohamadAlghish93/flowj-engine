package com.flowengine.controller;


import com.flowengine.entity.UserEntity;
import com.flowengine.service.UserService;
import com.flowengine.shared.ConstantsApp;
import com.flowengine.shared.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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
