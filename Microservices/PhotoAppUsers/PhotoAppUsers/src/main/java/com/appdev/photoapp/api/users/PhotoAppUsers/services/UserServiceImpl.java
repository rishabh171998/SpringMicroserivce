package com.appdev.photoapp.api.users.PhotoAppUsers.services;

import com.appdev.photoapp.api.users.PhotoAppUsers.data.AlbumsServiceClient;
import com.appdev.photoapp.api.users.PhotoAppUsers.data.userEntity;
import com.appdev.photoapp.api.users.PhotoAppUsers.data.userRepository;
import com.appdev.photoapp.api.users.PhotoAppUsers.models.AlbumResponseModel;
import com.appdev.photoapp.api.users.PhotoAppUsers.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
     private userRepository userRepository;
    @Autowired
   private BCryptPasswordEncoder bcrypt;
  @Autowired
  private Environment env;
   final Logger logger= LoggerFactory.getLogger(this.getClass());
    //@Autowired
   // @LoadBalanced
    //private RestTemplate restTemplate;

    @Autowired
    private AlbumsServiceClient albumsServiceClient;

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

    @Override
    public UserDto getUserDetailsByUserId(String userId) {

        userEntity userEntity = userRepository.findByUserId(userId);
        if(userEntity == null) throw new UsernameNotFoundException("User not found");

        UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);


       // String albumsUrl = String.format(env.getProperty("albums.url"), userId);

      //  ResponseEntity<List<AlbumResponseModel>> albumsListResponse = restTemplate.exchange(albumsUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<AlbumResponseModel>>() {
      //  });

        //USING FEIGN CLIENT
        logger.info("Hello Sleuth");
        List<AlbumResponseModel> albumsList = albumsServiceClient.getAlbums(userId);
      /*try {
          albumsList = albumsServiceClient.getAlbums(userId);
      }catch(FeignException e)
      {
          e.printStackTrace();
      }*/


            userDto.setAlbumsList(albumsList);
        return userDto;
    }
}
