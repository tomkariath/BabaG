package com.hypocritus.babag.controllers;

import com.hypocritus.babag.models.Group;
import com.hypocritus.babag.models.User;
import com.hypocritus.babag.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GroupController {

    GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/groups/{groupId}")
    ResponseEntity<Group> getGroup (@PathVariable("groupId") Long groupId){
        Group group = groupService.getGroup(groupId);

        if (group == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(group, HttpStatus.OK);
    }
}
