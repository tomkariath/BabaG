package com.hypocritus.babag.controllers;

import com.hypocritus.babag.models.Group;
import com.hypocritus.babag.models.User;
import com.hypocritus.babag.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/groups/{groupId}")
    ResponseEntity<HttpStatus> deleteGroup (@PathVariable("groupId") long groupId){
        if (groupService.deleteGroup(groupId)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/groups/{groupId}")
    ResponseEntity<Group> updateGroup (@PathVariable("groupId") Long groupId, @RequestBody Group updatedGroup){
        Group group = groupService.updateGroup(groupId, updatedGroup);

        if (group == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @GetMapping("/groups/{groupId}/add/{userId}")
    ResponseEntity<Group> addGroupMember (@PathVariable("groupId") Long groupId, @PathVariable Long userId){
        Group group = groupService.addGroupMember(groupId, userId);

        if (group == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @GetMapping("/groups/{groupId}/remove/{userId}")
    ResponseEntity<Group> removeGroupMember (@PathVariable("groupId") Long groupId, @PathVariable Long userId){
        Group group = groupService.removeGroupMember(groupId, userId);

        if (group == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @PostMapping("/group")
    ResponseEntity<Group> createNewGroup (@RequestBody Group group){
        Group newGroup = groupService.createNewGroup(group);
        return new ResponseEntity<>(newGroup, HttpStatus.CREATED);
    }
}
