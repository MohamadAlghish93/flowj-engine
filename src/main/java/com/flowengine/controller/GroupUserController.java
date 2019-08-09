package com.flowengine.controller;

import com.flowengine.entity.GroupUser;
import com.flowengine.service.GroupUserService;
import com.flowengine.shared.ConstantsApp;
import com.flowengine.shared.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/group")
public class GroupUserController {

    @Autowired
    GroupUserService groupUserService;

    @RequestMapping(
            value = "/save",
            method = RequestMethod.POST)
    public ResponseService saveGroup(@Valid @RequestBody GroupUser groupUser) {
        ResponseService responseService = new ResponseService();
        try{
            GroupUser groupUser1 =  this.groupUserService.saveGroup(groupUser);

            responseService.setData(groupUser1);

        } catch (Exception e) {
            responseService.setResponseCode(ConstantsApp.NOT_FOUND);
            responseService.setStatus(false);
            responseService.setMessage(e.getMessage());
        }
        return responseService;
    }


    @RequestMapping(
            value = {"/list/{page}/{size}"},
            method = {RequestMethod.GET}
    )
    public ResponseService  listGroup(@PathVariable int page, @PathVariable int size) {
        ResponseService responseService = new ResponseService();
        try {

            Page result = this.groupUserService.findAll(new PageRequest(page,size));

            responseService.setData(result);

        } catch (Exception e) {
            responseService.setStatus(false);
            responseService.setMessage(e.getMessage());

        }
        return responseService;
    }


}
