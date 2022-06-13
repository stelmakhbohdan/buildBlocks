package com.simplerest.buildblocks.controller;

import com.simplerest.buildblocks.entities.Person;
import com.simplerest.buildblocks.exceptions.UserExistsException;
import com.simplerest.buildblocks.exceptions.UserNotFoundException;
import com.simplerest.buildblocks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

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
    public ResponseEntity<Void> createUser(@RequestBody Person user, UriComponentsBuilder builder){
        try {
            userService.createUser(user);
            HttpHeaders httpHeaders =new HttpHeaders();
            httpHeaders.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
            return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
        }catch (UserExistsException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @GetMapping("/users/{id}")
    public Optional<Person> getUserById(@PathVariable("id") Long id) {
        try {
            return userService.getUserById(id);
        }catch (UserNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

    @PutMapping("/users/{id}")
    public Person updateUserById(@PathVariable ("id") Long id,@RequestBody Person user){
        try {
            return userService.updateUserById(id,user);
        }catch (UserNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
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
