package com.hypocritus.babag.services;

import com.hypocritus.babag.models.Task;
import com.hypocritus.babag.models.User;
import com.hypocritus.babag.repositories.TaskRepo;
import com.hypocritus.babag.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final TaskRepo taskRepo;

    @Autowired
    public UserService(UserRepo userRepo, TaskRepo taskRepo) {
        this.userRepo = userRepo;
        this.taskRepo = taskRepo;
    }

    public List<User> getAllUsers (){
        return userRepo.findAll();
    }

    public User getUser(long userId) {
        Optional<User> user = userRepo.findById(userId);

        return user.orElse(null);
    }

    public User resetUser(long userId) {
        Optional<User> userOptional = userRepo.findById(userId);
        User user = null;

        if (userOptional.isPresent()){
            user = userOptional.get();
            user.reset();
            for (Task task: user.getTasks()) {
                task.reset();
                taskRepo.save(task);
            }
            userRepo.save(user);
        }
        return user;
    }

    public User createNewUser (User user){
        return userRepo.save(new User(user.getUsername(), user.getBirthMonth()));
    }

    public User updateUser(long userId, User updatedUser){
        Optional<User> userOptional = userRepo.findById(userId);
        User user = null;

        if (userOptional.isPresent()){
            user = userOptional.get();
            user.update(updatedUser);
            userRepo.save(user);
        }
        return user;
    }

    public boolean deleteUser(long userId){
        Optional<User> userOptional = userRepo.findById(userId);

        if (userOptional.isPresent()){
            userRepo.deleteById(userId);

            return true;
        }

        return false;
    }
}
