package com.appdev.photoapp.api.users.PhotoAppUsers.controllers;

import com.appdev.photoapp.api.users.PhotoAppUsers.models.UserResponseModel;
import com.appdev.photoapp.api.users.PhotoAppUsers.models.createUserRequestModel;
import com.appdev.photoapp.api.users.PhotoAppUsers.models.createUserResponseModel;
import com.appdev.photoapp.api.users.PhotoAppUsers.services.UserService;
import com.appdev.photoapp.api.users.PhotoAppUsers.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
     private Environment env;
    @GetMapping("/status/check")
    public String status()
    {
     return "Working on port "+env.getProperty("local.server.port") +"   "+ env.getProperty("token.secret");
    }
    
    @PostMapping
    public ResponseEntity<createUserResponseModel> createUser(@Valid @RequestBody createUserRequestModel createUser)
    {
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(STRICT);
        UserDto userDto=modelMapper.map(createUser,UserDto.class);
       UserDto createdUser= userService.createUser(userDto);

        createUserResponseModel returnValue=modelMapper.map(createdUser,createUserResponseModel.class);
        return new ResponseEntity(returnValue,HttpStatus.CREATED);
    }


    /*public ResponseEntity<UserResponseModel> getUser(@PathVariable("userId") String userId)
    {
       UserDto userDto=userService.getUserDetailsByUserId(userId);
       UserResponseModel returnValue=new ModelMapper().map(userDto,UserResponseModel.class);

       return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }*/
    @GetMapping("/{userId}")
    public  ResponseEntity<UserResponseModel> getUser(@PathVariable("userId") String userId)
    {

        UserDto userDto = userService.getUserDetailsByUserId(userId);
        UserResponseModel returnValue = new ModelMapper().map(userDto, UserResponseModel.class);

        return ResponseEntity.status(HttpStatus.OK).body(returnValue);

    }
}
