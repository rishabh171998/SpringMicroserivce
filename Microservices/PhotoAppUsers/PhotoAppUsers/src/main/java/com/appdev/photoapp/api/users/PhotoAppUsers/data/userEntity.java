package com.appdev.photoapp.api.users.PhotoAppUsers.data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class userEntity implements Serializable {

    @Id
    @GeneratedValue
    private long dbid;

    @Column(nullable = false,length=50)
    private String firstname;
    @Column(nullable = false,length=50)
    private String lastname;
    @Column(nullable = false,length=150,unique = true)
    private String email;
    @Column(nullable = false,unique = true)
    private String userId;

    @Column(nullable = false)
    private String EncryptedPassword;

    public long getDbid() {
        return dbid;
    }

    public void setDbid(long dbid) {
        this.dbid = dbid;
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
