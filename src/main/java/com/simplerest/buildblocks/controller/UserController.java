package com.simplerest.buildblocks.controller;

import com.simplerest.buildblocks.entities.Person;
import com.simplerest.buildblocks.exceptions.UserExistsException;
import com.simplerest.buildblocks.exceptions.UserNameNotFoundException;
import com.simplerest.buildblocks.exceptions.UserNotFoundException;
import com.simplerest.buildblocks.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@Api(tags = "User management RESTful Services",value = "UserController",description = "Controller for User Management Service")
@RestController
@Validated
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Retrieve list of users")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> getAllUsers(){
        return userService.getAllUser();
    }

    @ApiOperation(value = "Create new user")
    @PostMapping
    public ResponseEntity<Void> createUser(@ApiParam("User information for a new user to be created.")@Valid @RequestBody Person user, UriComponentsBuilder builder){
        try {
            userService.createUser(user);
            HttpHeaders httpHeaders =new HttpHeaders();
            httpHeaders.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
            return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
        }catch (UserExistsException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Person getUserById(@PathVariable("id") @Min(1) Long id) {
        try {
            Optional<Person> userOptional = userService.getUserById(id);
            return userOptional.get();
        }catch (UserNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Person updateUserById(@PathVariable ("id") Long id,@RequestBody Person user){
        try {
            return userService.updateUserById(id,user);
        }catch (UserNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id") Long id){
        userService.deleteUserById(id);
    }

    @GetMapping("/byusername/{username}")
    public Person getUserByUsername(@PathVariable("username") String username) throws UserNameNotFoundException {
        Person user =  userService.getUserByUsername(username);
        if (user==null){
            throw new UserNameNotFoundException("Username: "+username+ " not found in user repository");
        }
        return user;
    }
}
