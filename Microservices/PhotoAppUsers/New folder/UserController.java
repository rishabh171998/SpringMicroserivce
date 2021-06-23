package com.appdev.photoapp.api.users.PhotoAppUsers.controllers;


import com.appdev.photoapp.api.users.PhotoAppUsers.models.createUserRequestModel;

import com.appdev.photoapp.api.users.PhotoAppUsers.services.UserService;
import com.appdev.photoapp.api.users.PhotoAppUsers.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
     private Environment env;
    @GetMapping("/status/check")
    public String status()
    {
     return "Working on port "+env.getProperty("local.server.port");
    }
    
    @PostMapping
    public String createUser(@Valid @RequestBody createUserRequestModel createUser)
    {
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(STRICT);
        UserDto userDto=modelMapper.map(createUser,UserDto.class);
        userService.createUser(userDto);
        return "create user method is called";
    }
}