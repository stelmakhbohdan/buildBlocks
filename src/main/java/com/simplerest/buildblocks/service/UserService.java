package com.simplerest.buildblocks.service;

import com.simplerest.buildblocks.entities.Person;
import com.simplerest.buildblocks.exceptions.UserExistsException;
import com.simplerest.buildblocks.exceptions.UserNotFoundException;
import com.simplerest.buildblocks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<Person> getAllUser(){
        return userRepository.findAll();
    }

    public Person createUser(Person user) throws UserExistsException {
        Person existUser = userRepository.findByUsername(user.getUsername());
        if (existUser!=null){
            throw new UserExistsException("User already exists in repository");
        }
        return userRepository.save(user);
    }

    public Optional<Person> getUserById(Long id) throws UserNotFoundException {
        Optional<Person> user =  userRepository.findById(id);;
        if (!user.isPresent()){
            throw new UserNotFoundException("User not found in repository");
        }
        return user;
    }

    public Person updateUserById(Long id,Person user) throws UserNotFoundException {
        Optional<Person> optionalUser =  userRepository.findById(id);;
        if (!optionalUser.isPresent()){
            throw new UserNotFoundException("User not found in repository,provide correct user id");
        }
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUserById(Long id)  {
        Optional<Person> optionalUser =  userRepository.findById(id);;
        if (!optionalUser.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User not found in repository,provide correct user id");
        }
            userRepository.deleteById(id);
    }

    public Person getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }


}