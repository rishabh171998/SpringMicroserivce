package com.appdev.photoapp.api.users.PhotoAppUsers.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class createUserRequestModel {
    
    @NotNull(message="First name cannot be null")
    @Size(min=2,message="First name must note be less than two characters")
    private String firstname;
    @NotNull(message="Last Name name cannot be null")
    @Size(min=2,message="Last name must note be less than two characters")
    private String lastname;
    @NotNull(message="Password cannot be null")
    @Size(min=2,max=16,message="Password must note be less than two characters")
    private String password;
    
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

    @NotNull(message = "Email cannot be null")
    @Email
    private String email;
}
