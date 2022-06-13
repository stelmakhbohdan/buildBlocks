package com.simplerest.buildblocks.controller;

import com.simplerest.buildblocks.entities.Person;
import com.simplerest.buildblocks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/users/{id}")
    public Optional<Person> getUserById(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }

    @PutMapping("/users/{id}")
    public Person updateUserById(@PathVariable ("id") Long id,@RequestBody Person user){
        return userService.updateUserById(id,user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable("id") Long id){
        userService.deleteUserById(id);
    }

    @GetMapping("/users/byusername/{username}")
    public Person getUserByUsername(@PathVariable("username") String username){
        return userService.getUserByUsername(username);
    }
}
