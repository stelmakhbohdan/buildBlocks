package com.simplerest.buildblocks.controller;

import com.simplerest.buildblocks.entities.Order;
import com.simplerest.buildblocks.entities.Person;
import com.simplerest.buildblocks.exceptions.UserNotFoundException;
import com.simplerest.buildblocks.repository.UserRepository;
import com.simplerest.buildblocks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping(value = "/hateoas/users")
public class UserHateoasController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public CollectionModel<Person> getAllUsers() throws UserNotFoundException {
        List<Person> allUsers =  userService.getAllUser();
        for (Person user:allUsers) {
            Long userId = user.getId();
            Link link = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userId).withSelfRel();
            user.add(link);

            CollectionModel<Order> orders= WebMvcLinkBuilder.methodOn(OrderHateoasController.class)
                    .getAllOrders(userId);
            Link orderLink =WebMvcLinkBuilder.linkTo(orders).withRel("all-orders");
            user.add(orderLink);

        }
        Link link = WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
        return CollectionModel.of(allUsers,link);
    }

    @GetMapping("/{id}")
    public EntityModel<Person> getUserById(@PathVariable("id") @Min(1) Long id) {
        try {
            Optional<Person> userOptional = userService.getUserById(id);
            Person user = userOptional.get();
            Long userid =user.getId();
            Link selfLink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
            user.add(selfLink);
            return EntityModel.of(user,selfLink);
        }catch (UserNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }
}
