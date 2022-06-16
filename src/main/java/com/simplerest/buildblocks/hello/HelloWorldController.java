package com.simplerest.buildblocks.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private ResourceBundleMessageSource messageSource;

    @GetMapping("/helloworld")
    public String helloWorld(){
        return "Hello";
    }

    @GetMapping("/helloworld-bean")
    public UserDetails helloWorldBean(){
        return new UserDetails("Bohdan","Ready","Kiev");
    }

    @GetMapping("/hello-int")
    public String getMessageInI18Format(@RequestHeader(name = "Accept language",required = false) String locale){
        return messageSource.getMessage("label.hello",null,new Locale(locale));
    }
}
