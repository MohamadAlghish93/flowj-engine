package com.flowjava.entity;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "arrow")
public class Arrow {

    private UUID id;
    private String name;
    private List<Activity> activitiesNext;
    private List<Activity> activitiesPrev;
    private List<Activity> activityCurrent;
    private String Condition;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Basic
    @Column(name = "condition", length = 2048)
    public String getCondition() {
        return Condition;
    }

    public void setCondition(String condition) {
        Condition = condition;
    }


    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.REMOVE},mappedBy = "arrowNext")
    public List<Activity> getActivitiesNext() {
        return activitiesNext;
    }

    public void setActivitiesNext(List<Activity> activitiesNext) {
        this.activitiesNext = activitiesNext;
    }


    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.REMOVE},mappedBy = "arrowPrev")
    public List<Activity> getActivitiesPrev() {
        return activitiesPrev;
    }

    public void setActivitiesPrev(List<Activity> activitiesPrev) {
        this.activitiesPrev = activitiesPrev;
    }


    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.REMOVE},mappedBy = "currentArrow")

    public List<Activity> getActivityCurrent() {
        return activityCurrent;
    }

    public void setActivityCurrent(List<Activity> activityCurrent) {
        this.activityCurrent = activityCurrent;
    }
}
