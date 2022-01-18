package com.hypocritus.babag.conntrollers;

import com.hypocritus.babag.models.User;
import com.hypocritus.babag.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final UserRepo userRepo;

    @Autowired
    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/users")
    List<User> getAllUsers (){
        return userRepo.findAll();
    }

    @GetMapping("/users/{userId}")
    ResponseEntity<User> getUser (@PathVariable("userId") Long userId){
        Optional<User> user = userRepo.findById(userId);

        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/users")
    ResponseEntity<User> createNewUser (@RequestBody User user){
       User newUser = userRepo.save(new User(user.getUsername(), user.getBirthMonth()));
       return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/users/{userId}")
    ResponseEntity<User> updateUser (@PathVariable("userId") Long userId, @RequestBody User updatedUser){
        Optional<User> userOptional = userRepo.findById(userId);

        if (userOptional.isPresent()){
            User user = userOptional.get();
            user.update(updatedUser);
            userRepo.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/{userId}")
    ResponseEntity<HttpStatus> deleteUser (@PathVariable("userId") long userId){
        Optional<User> userOptional = userRepo.findById(userId);

        if (userOptional.isPresent()){
            userRepo.deleteById(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //TODO GetAllTasks

    //TODO Get GetStatus of a task

    //TODO Increment task count

}
