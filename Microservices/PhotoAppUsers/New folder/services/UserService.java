package com.appdev.photoapp.api.users.PhotoAppUsers.services;

import com.appdev.photoapp.api.users.PhotoAppUsers.shared.UserDto;

public interface UserService {
    public UserDto createUser(UserDto userDetails);
}
