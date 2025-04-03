package com.caching.redis.controller;


import com.caching.redis.models.Users;
import com.caching.redis.services.framework.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Log4j2
public class UsersController {

    private final UserService userService;


    @PostMapping("/create")
    public ResponseEntity<String> createUser(@Valid @RequestBody Users users){
        log.warn("Creating New User....");
        userService.addUser(users);
        return new ResponseEntity("User Created Successfully", HttpStatus.CREATED);
    }


    @PostMapping("/update")
    public ResponseEntity<String> updateUser(@Valid @RequestBody Users users){
        log.warn("Updating Existing User....");
        userService.updateUser(users);
        return new ResponseEntity("User Updated Successfully", HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id){
        log.warn("Fetching User By Id....");
        Users users = userService.getUserById(id);
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        log.warn("Deleting User By Id....");
        userService.deleteUser(id);
        return new ResponseEntity("User Deleted Successfully", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers(){
        log.warn("Fetching All Users....");
        List<Users> users = userService.getAllUsers();
        return new ResponseEntity(users, HttpStatus.OK);
    }
}
