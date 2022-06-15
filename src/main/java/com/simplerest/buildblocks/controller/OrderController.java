package com.simplerest.buildblocks.controller;

import com.simplerest.buildblocks.entities.Order;
import com.simplerest.buildblocks.entities.Person;
import com.simplerest.buildblocks.exceptions.UserNotFoundException;
import com.simplerest.buildblocks.repository.OrderRepository;
import com.simplerest.buildblocks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class OrderController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/{userid}/orders")
    public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {
        Optional<Person> user = userRepository.findById(userid);
        if (!user.isPresent())
            throw new UserNotFoundException("User not found");
            return user.get().getOrders();
    }

    @PostMapping("{userid}/orders")
    public Order createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNotFoundException {
        Optional<Person> userOptional = userRepository.findById(userid);
        if (!userOptional.isPresent())
            throw new UserNotFoundException("User not found");
        Person user = userOptional.get();
        order.setUser(user);
        return orderRepository.save(order);
    }
}