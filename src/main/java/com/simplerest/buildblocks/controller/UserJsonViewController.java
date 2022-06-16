package com.simplerest.buildblocks.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.simplerest.buildblocks.entities.Person;
import com.simplerest.buildblocks.entities.Views;
import com.simplerest.buildblocks.exceptions.UserNotFoundException;
import com.simplerest.buildblocks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.Optional;

@RestController
@Validated
@RequestMapping(value = "/jsonview/users")
public class UserJsonViewController {

    @Autowired
    private UserService userService;

    @JsonView(Views.External.class)
    @GetMapping("/external/{id}")
    public Optional<Person> getUserById(@PathVariable("id") @Min(1) Long id) {
        try {
            return userService.getUserById(id);
        }catch (UserNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

    @GetMapping("/internal/{id}")
    @JsonView(Views.Internal.class)
    public Optional<Person> getUserById2(@PathVariable("id") @Min(1) Long id) {
        try {
            return userService.getUserById(id);
        }catch (UserNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }
}
