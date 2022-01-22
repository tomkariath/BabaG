package com.hypocritus.babag.controllers;

import com.hypocritus.babag.models.User;
import com.hypocritus.babag.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    List<User> getAllUsers (){
        return userService.getAllUsers();
    }

    @GetMapping("/users/{userId}")
    ResponseEntity<User> getUser (@PathVariable("userId") Long userId){
        User user = userService.getUser(userId);

        if (user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/calculate-earnings")
    ResponseEntity<User> calculateUserEarnings (@PathVariable("userId") Long userId){
        User user = userService.calculateEarnings(userId);

        if (user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/reset")
    ResponseEntity<User> resetUser (@PathVariable("userId") Long userId){
        User user = userService.resetUser(userId);

        if (user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/users")
    ResponseEntity<User> createNewUser (@RequestBody User user){
       User newUser = userService.createNewUser(user);
       return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/users/{userId}")
    ResponseEntity<User> updateUser (@PathVariable("userId") Long userId, @RequestBody User updatedUser){
        User user = userService.updateUser(userId, updatedUser);

        if (user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}")
    ResponseEntity<HttpStatus> deleteUser (@PathVariable("userId") long userId){
        if (userService.deleteUser(userId)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
