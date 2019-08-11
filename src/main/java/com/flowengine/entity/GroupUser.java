package com.flowengine.entity;


import javax.persistence.*;
import java.util.UUID;

@Entity
@Table (name = "group_user")
public class GroupUser {
    private UUID id;
    private String name;
    private String code;
    private String type;
    private String color;
    private Long permission;
//    private List<Activity> activityGroupUser;

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

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "type",nullable = true, length = 255)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "permission",nullable = true)
    public Long getPermission() {
        return permission;
    }

    public void setPermission(Long permission) {
        this.permission = permission;
    }

    @Basic
    @Column(name = "color",nullable = true)
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    //    @ManyToMany(mappedBy = "groupUsers")
//    public List<Activity> getActivityGroupUser() {
//        return activityGroupUser;
//    }
//
//    public void setActivityGroupUser(List<Activity> activityGroupUser) {
//        this.activityGroupUser = activityGroupUser;
//    }
}
