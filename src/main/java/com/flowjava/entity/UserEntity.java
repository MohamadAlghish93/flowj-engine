package com.flowjava.entity;

import com.flowjava.entity.ext.Auditable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "SYSTEM_USER")
public class UserEntity extends Auditable<String> implements Serializable {

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "system_user_gorup",
            joinColumns = { @JoinColumn(name = "system_user_id") },
            inverseJoinColumns = { @JoinColumn(name = "group_id") })
    private List<GroupUser> groupUsers;

    // constructor

    public UserEntity() {
    }

    // setter and getter
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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


    public List<GroupUser> getGroupUsers() {
        return groupUsers;
    }


    public void setGroupUsers(List<GroupUser> groupUsers) {
        this.groupUsers = groupUsers;
    }
}
