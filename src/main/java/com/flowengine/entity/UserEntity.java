package com.flowengine.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.flowengine.entity.ext.Auditable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"},ignoreUnknown = true)
@Entity
@Table(name = "SYSTEM_USER")
public class UserEntity extends Auditable<String> implements Serializable {


//    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<GroupUser> groupUsers;

    // constructor

    public UserEntity() {
    }

    // setter and getter

    @Column(name = "ID")
    @Id
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Column(name = "USER_NAME", nullable = true, length = 255)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "FIRST_NAME", nullable = true, length = 255)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LAST_NAME", nullable = true, length = 255)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "EMAIL", nullable = true, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Column(name = "PASSWORD", nullable = true, length = 500)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "system_user_gorup",
            joinColumns = { @JoinColumn(name = "system_user_id") },
            inverseJoinColumns = { @JoinColumn(name = "group_id") })
    public List<GroupUser> getGroupUsers() {
        return groupUsers;
    }

    public void setGroupUsers(List<GroupUser> groupUsers) {
        this.groupUsers = groupUsers;
    }
}
