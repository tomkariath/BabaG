package com.hypocritus.babag.services;

import com.hypocritus.babag.models.Group;
import com.hypocritus.babag.models.User;
import com.hypocritus.babag.repositories.GroupRepo;
import com.hypocritus.babag.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupService {
    GroupRepo groupRepo;
    UserRepo userRepo;

    @Autowired
    public GroupService(GroupRepo groupRepo, UserRepo userRepo) {
        this.groupRepo = groupRepo;
        this.userRepo = userRepo;
    }

    public Group createNewGroup (Group group){
        return groupRepo.save(new Group(group.getName()));
    }

    public Group getGroup(long groupId) {
        Optional<Group> groupOptional = groupRepo.findById(groupId);
        Group group = null;

        if (groupOptional.isPresent()){
            group = groupOptional.get();
        }

        return group;
    }

    public boolean deleteGroup(long groupId) {
        Optional<Group> groupOptional = groupRepo.findById(groupId);

        if (groupOptional.isPresent()){
            groupRepo.deleteById(groupId);

            return true;
        }

        return false;
    }

    public Group updateGroup(long groupId, Group updatedGroup){
        Optional<Group> groupOptional = groupRepo.findById(groupId);
        Group group = null;

        if (groupOptional.isPresent()){
            group = groupOptional.get();
            group.update(updatedGroup);
            groupRepo.save(group);
        }
        return group;
    }

    public Group addGroupMember(long groupId, long userId){
        Optional<Group> groupOptional = groupRepo.findById(groupId);
        Optional<User> userOptional = userRepo.findById(userId);

        Group group = null;

        if (groupOptional.isPresent() && userOptional.isPresent()){
            group = groupOptional.get();
            group.getUsers().add(userOptional.get());
            groupRepo.save(group);
        }
        return group;
    }

    public Group removeGroupMember(long groupId, long userId){
        Optional<Group> groupOptional = groupRepo.findById(groupId);
        Optional<User> userOptional = userRepo.findById(userId);

        Group group = null;

        if (groupOptional.isPresent() && userOptional.isPresent()){
            group = groupOptional.get();
            group.getUsers().remove(userOptional.get());
            groupRepo.save(group);
        }
        return group;
    }
}
