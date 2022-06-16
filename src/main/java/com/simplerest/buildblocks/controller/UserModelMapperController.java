package com.simplerest.buildblocks.controller;

import com.simplerest.buildblocks.dtos.UserDto;
import com.simplerest.buildblocks.entities.Person;
import com.simplerest.buildblocks.exceptions.UserNotFoundException;
import com.simplerest.buildblocks.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.Optional;

@RestController
@RequestMapping("/modelmapper/users")
public class UserModelMapperController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFoundException {
            Optional<Person> userOptional = userService.getUserById(id);
            if (!userOptional.isPresent()){
                throw new UserNotFoundException("User not found");
            }
            Person user= userOptional.get();
            UserDto userDto =modelMapper.map(user,UserDto.class);
            return userDto;
    }
}
