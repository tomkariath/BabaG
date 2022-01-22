package com.hypocritus.babag.services;

import com.hypocritus.babag.models.Task;
import com.hypocritus.babag.models.User;
import com.hypocritus.babag.repositories.TaskRepo;
import com.hypocritus.babag.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final TaskRepo taskRepo;

    @Value("${custom.monthly-cost}")
    private int monthlyCostPerMember;

    //TODO get this from group
    private static final int NUMBER_OF_GROUP_MEMBERS = 3;

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

    public User calculateEarnings(long userId){

        Optional<User> userOptional = userRepo.findById(userId);
        User user = null;

        long currentEarnings;

        if (userOptional.isPresent()){
            user = userOptional.get();

            currentEarnings = user.getEarnings();

            if (user.getEarningCalculatedMonth().compareTo(LocalDate.now().getMonth()) == 0){
                return user;
            }

            List<Task> tasks = user.getTasks();
            int costPerTask = getCostPerTask(tasks.size());

            for (Task task : tasks){
                int score = task.getCurrentScore();
                int requiredScore = task.getCompletionScore();

                if (task.isCompleted()){
                    currentEarnings = currentEarnings + costPerTask;
                }
                else if (isEligibleForEarnings(score, requiredScore)){
                    currentEarnings = (long) (currentEarnings +
                            (getCompletionPercentage(score, requiredScore)) * costPerTask);
                }
            }

            user.setEarnings(currentEarnings);
            userRepo.save(user);
        }

        return user;
    }

    private int getCostPerTask(int numberOfTasks){
        return (monthlyCostPerMember * NUMBER_OF_GROUP_MEMBERS) / numberOfTasks;
    }

    private boolean isEligibleForEarnings (int score, int requiredScore){
        return getCompletionPercentage(score, requiredScore) > 0.5;
    }

    private double getCompletionPercentage (double score, double requiredScore) {
        return (score/requiredScore);
    }
}
