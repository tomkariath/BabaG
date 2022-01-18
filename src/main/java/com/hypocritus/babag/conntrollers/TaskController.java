package com.hypocritus.babag.conntrollers;

import com.hypocritus.babag.models.Task;
import com.hypocritus.babag.models.User;
import com.hypocritus.babag.repositories.TaskRepo;
import com.hypocritus.babag.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {

    private final UserRepo userRepo;
    private final TaskRepo taskRepo;

    @Autowired
    public TaskController(UserRepo userRepo, TaskRepo taskRepo) {
        this.userRepo = userRepo;
        this.taskRepo = taskRepo;
    }

    @GetMapping("/users/{userId}/tasks")
    ResponseEntity<List<Task>> getUser (@PathVariable("userId") Long userId){
        Optional<User> userOptional = userRepo.findById(userId);

        if (userOptional.isPresent()){
            List<Task> tasks = userOptional.get().getTasks();

            return new ResponseEntity<>(tasks, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
