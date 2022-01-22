package com.hypocritus.babag.services;

import com.hypocritus.babag.models.Group;
import com.hypocritus.babag.repositories.GroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupService {
    GroupRepo groupRepo;

    @Autowired
    public GroupService(GroupRepo groupRepo) {
        this.groupRepo = groupRepo;
    }

    public Group getGroup(long groupId) {
        Optional<Group> groupOptional = groupRepo.findById(groupId);
        Group group = null;

        if (groupOptional.isPresent()){
            group = groupOptional.get();
        }

        return group;
    }
}
