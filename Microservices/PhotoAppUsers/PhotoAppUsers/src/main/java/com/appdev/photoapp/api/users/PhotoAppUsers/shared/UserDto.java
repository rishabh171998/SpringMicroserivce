package com.appdev.photoapp.api.users.PhotoAppUsers.shared;



import com.appdev.photoapp.api.users.PhotoAppUsers.models.AlbumResponseModel;

import java.io.Serializable;
import java.util.List;

public class UserDto implements Serializable {
    private static final long serialVersionUID=-953297098295050686L;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private String userId;
    private String EncryptedPassword;
    private List<AlbumResponseModel> albumsList;

    public List<AlbumResponseModel> getAlbumsList() {
        return albumsList;
    }

    public void setAlbumsList(List<AlbumResponseModel> albumsList) {
        this.albumsList = albumsList;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEncryptedPassword() {
        return EncryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        EncryptedPassword = encryptedPassword;
    }
}
