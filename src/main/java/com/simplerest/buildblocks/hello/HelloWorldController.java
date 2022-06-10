package com.simplerest.buildblocks.hello;

import com.simplerest.buildblocks.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/helloworld")
    public String helloWorld(){
        return "Hello";
    }

    @GetMapping("/helloworld-bean")
    public UserDetails helloWorldBean(){
        return new UserDetails("Bohdan","Ready","Kiev");
    }
}
