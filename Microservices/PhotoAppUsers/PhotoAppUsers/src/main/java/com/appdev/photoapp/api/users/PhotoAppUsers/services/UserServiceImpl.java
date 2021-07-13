package com.appdev.photoapp.api.users.PhotoAppUsers.services;

import com.appdev.photoapp.api.users.PhotoAppUsers.data.userEntity;
import com.appdev.photoapp.api.users.PhotoAppUsers.data.userRepository;
import com.appdev.photoapp.api.users.PhotoAppUsers.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
           userEntity userentity= userRepository.findByEmail(username);
           if(userentity==null) throw new UsernameNotFoundException(username);

        return new User(userentity.getEmail(),userentity.getEncryptedPassword(),true,true,true,true,new ArrayList<>());
    }

    @Override
    public UserDto getUserDetailsByEmail(String email)
    {
        userEntity userentity= userRepository.findByEmail(email);
        if(userentity==null) throw new UsernameNotFoundException(email);

        return new ModelMapper().map(userentity,UserDto.class);
    }
}
