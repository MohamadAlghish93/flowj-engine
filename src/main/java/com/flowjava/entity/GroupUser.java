package com.flowjava.entity;


import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table (name = "group_user")
public class GroupUser {
    private UUID id;
    private String name;
    private List<Activity> activityGroupUser;

    @Column(name = "id")
    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name",unique = true, nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "groupUsers")
    public List<Activity> getActivityGroupUser() {
        return activityGroupUser;
    }

    public void setActivityGroupUser(List<Activity> activityGroupUser) {
        this.activityGroupUser = activityGroupUser;
    }
}
