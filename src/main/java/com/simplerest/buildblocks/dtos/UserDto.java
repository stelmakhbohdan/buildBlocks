package com.simplerest.buildblocks.dtos;

import com.simplerest.buildblocks.entities.Order;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String id;
    private String username;
    private String firstname;
    private List<Order> orders;
}
