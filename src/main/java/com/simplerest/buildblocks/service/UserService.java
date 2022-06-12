package com.simplerest.buildblocks.service;

import com.simplerest.buildblocks.entities.Person;
import com.simplerest.buildblocks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<Person> getAllUser(){
        return userRepository.findAll();
    }


    public Person createUser(Person person){
        return userRepository.save(person);
    }
}
