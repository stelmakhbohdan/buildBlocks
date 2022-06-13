package com.simplerest.buildblocks.service;

import com.simplerest.buildblocks.entities.Person;
import com.simplerest.buildblocks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

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

    public Optional<Person> getUserById(Long id){
        return userRepository.findById(id);
    }

    public Person updateUserById(Long id,Person user){
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUserById(Long id){
       if (userRepository.findById(id).isPresent()){
       userRepository.deleteById(id);
       }
    }

    public Person getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }


}