package com.appdev.photoapp.api.users.PhotoAppUsers.services;

import com.appdev.photoapp.api.users.PhotoAppUsers.data.userEntity;
import com.appdev.photoapp.api.users.PhotoAppUsers.data.userRepository;
import com.appdev.photoapp.api.users.PhotoAppUsers.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
     private userRepository userRepository;
    @Override
    public UserDto createUser(UserDto userDetails) {

        userDetails.setUserId(UUID.randomUUID().toString());
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(STRICT);
        userEntity userEntity=modelMapper.map(userDetails,userEntity.class);
       userEntity.setEncryptedPassword("test");
        userRepository.save(userEntity);
      return null;
    }
}
