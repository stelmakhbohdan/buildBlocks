package com.simplerest.buildblocks.controller;

import com.simplerest.buildblocks.entities.Order;
import com.simplerest.buildblocks.entities.Person;
import com.simplerest.buildblocks.exceptions.UserNotFoundException;
import com.simplerest.buildblocks.repository.OrderRepository;
import com.simplerest.buildblocks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping(value = "/hateoas/users")
public class OrderHateoasController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/{userid}/orders")
    public CollectionModel<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {
        Optional<Person> user = userRepository.findById(userid);
        if (!user.isPresent())
            throw new UserNotFoundException("User not found");
        List<Order> orders= user.get().getOrders();
        return CollectionModel.of(orders);
    }
}
