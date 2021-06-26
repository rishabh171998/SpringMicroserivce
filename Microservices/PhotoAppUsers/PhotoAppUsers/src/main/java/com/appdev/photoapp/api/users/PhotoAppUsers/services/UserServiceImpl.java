package com.appdev.photoapp.api.users.PhotoAppUsers.services;

import com.appdev.photoapp.api.users.PhotoAppUsers.data.userEntity;
import com.appdev.photoapp.api.users.PhotoAppUsers.data.userRepository;
import com.appdev.photoapp.api.users.PhotoAppUsers.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
     private userRepository userRepository;
    @Autowired
   private BCryptPasswordEncoder bcrypt;
    @Override
    public UserDto createUser(UserDto userDetails) {

        userDetails.setUserId(UUID.randomUUID().toString());
        userDetails.setEncryptedPassword(bcrypt.encode(userDetails.getPassword()));
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(STRICT);
        userEntity userEntity=modelMapper.map(userDetails,userEntity.class);
        userRepository.save(userEntity);
        UserDto returnValue=modelMapper.map(userEntity,UserDto.class);

      return returnValue;
    }
}
