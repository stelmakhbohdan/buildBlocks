package com.simplerest.buildblocks.controller;

import com.simplerest.buildblocks.entities.Person;
import com.simplerest.buildblocks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<Person> getAllUsers(){
        return userService.getAllUser();
    }

    @PostMapping("/users")
    public Person createUser(@RequestBody Person user){
        return userService.createUser(user);
    }
}
