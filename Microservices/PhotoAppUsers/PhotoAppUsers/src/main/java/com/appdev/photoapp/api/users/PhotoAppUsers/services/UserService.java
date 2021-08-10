package com.appdev.photoapp.api.users.PhotoAppUsers.services;

import com.appdev.photoapp.api.users.PhotoAppUsers.shared.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public UserDto createUser(UserDto userDetails);
    public UserDto getUserDetailsByEmail(String email);
    public UserDto getUserDetailsByUserId(String userId);
}
