package com.flowjava.entity;

import com.flowjava.entity.ext.Auditable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SYSTEM_USER")
public class UserEntity extends Auditable<String> implements Serializable {

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name = "USER_NAME", nullable = true, length = 255)
    private String userName;

    @Column(name = "FIRST_NAME", nullable = true, length = 255)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = true, length = 255)
    private String lastName;

    @Column(name = "EMAIL", nullable = true, length = 255)
    private String email;

    @Column(name = "PASSWORD", nullable = true, length = 500)
    private String password;

    // constructor

    public UserEntity() {
    }

    // setter and getter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
