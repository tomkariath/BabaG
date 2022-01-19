package com.hypocritus.babag.conntrollers;

import com.hypocritus.babag.models.Task;
import com.hypocritus.babag.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/users/{userId}/tasks")
    ResponseEntity<List<Task>> getUserTasks (@PathVariable("userId") Long userId){
        List<Task> tasks = taskService.getTasksOfUser(userId);
        if (tasks == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/tasks/{taskId}")
    ResponseEntity<Task> getTask (@PathVariable("taskId") Long taskId){
        Task task = taskService.getTaskById(taskId);

        if (task == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping("/users/{userId}/tasks")
    ResponseEntity<Task> createNewUserTask (@PathVariable("userId") Long userId, @RequestBody Task task){
        Task newTask = taskService.creatNewTaskForUser(userId, task);

        if (newTask == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }

    @PutMapping("/tasks/{taskId}")
    ResponseEntity<Task> updateTask (@PathVariable("taskId") Long taskId, @RequestBody Task updatedTask){
        Task task = taskService.updateTask(taskId, updatedTask);

        if (task == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}/tasks/{taskId}")
    ResponseEntity<HttpStatus> deleteTask (@PathVariable("userId") long userId, @PathVariable("taskId") long taskId){
        if (taskService.deleteTaskFromUser(userId, taskId)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
