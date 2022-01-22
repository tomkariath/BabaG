package com.hypocritus.babag.services;

import com.hypocritus.babag.models.Task;
import com.hypocritus.babag.models.User;
import com.hypocritus.babag.repositories.TaskRepo;
import com.hypocritus.babag.repositories.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final UserRepo userRepo;
    private final TaskRepo taskRepo;

    public TaskService(UserRepo userRepo, TaskRepo taskRepo) {
        this.userRepo = userRepo;
        this.taskRepo = taskRepo;
    }

    public List<Task> getTasksOfUser(long userId){
        Optional<User> userOptional = userRepo.findById(userId);
        List<Task> tasks = null;

        if (userOptional.isPresent()){
            tasks = userOptional.get().getTasks();
        }

        return tasks;
    }

    public Task getTaskById(long taskId){
        Optional<Task> taskOptional = taskRepo.findById(taskId);

        return taskOptional.orElse(null);
    }

    public Task creatNewTaskForUser(long userId, Task task){
        Optional<User> userOptional = userRepo.findById(userId);
        Task newTask = null;

        if (userOptional.isPresent()){
            newTask = taskRepo.save(new Task(task.getTaskName(), task.getCompletionScore()));
            User user = userOptional.get();
            user.getTasks().add(newTask);
            userRepo.save(user);
        }
        return newTask;
    }

    public Task updateTask(long taskId, Task updatedTask){
        Optional<Task> taskOptional = taskRepo.findById(taskId);
        Task task = null;

        if (taskOptional.isPresent()){
            task = taskOptional.get();
            task.update(updatedTask);
            taskRepo.save(task);
        }
        return task;
    }

    public boolean deleteTaskFromUser(long taskId){
        Optional<Task> taskOptional = taskRepo.findById(taskId);

        if (taskOptional.isPresent()){
            Task task = taskOptional.get();
            User user =  task.getUser();
            user.getTasks().remove(task);
            userRepo.save(user);
            return true;
        }
        else {
            return false;
        }
    }
}
