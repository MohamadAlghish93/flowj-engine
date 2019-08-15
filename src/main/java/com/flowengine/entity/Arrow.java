package com.flowengine.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"},ignoreUnknown = true)
@Entity
@Table(name = "arrow")
public class Arrow {

    private UUID id;
    private String name;
    private Process arrowsProcess;
    private UUID processId;
    private List<Activity> activityCurrent;
    private List<Activity> activitiesNext;
    private List<Activity> activitiesPrev;
    private List<Condition> condition;

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
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "process_id")
    public UUID getProcessId() {
        return processId;
    }

    public void setProcessId(UUID processId) {
        this.processId = processId;
    }


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "activity_arrow_next",
            joinColumns = { @JoinColumn(name = "arrow_id") },
            inverseJoinColumns = { @JoinColumn(name = "activity_id") })
    public List<Activity> getActivitiesNext() {
        return activitiesNext;
    }

    public void setActivitiesNext(List<Activity> activitiesNext) {
        this.activitiesNext = activitiesNext;
    }


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "activity_arrow_prev",
            joinColumns = { @JoinColumn(name = "arrow_id") },
            inverseJoinColumns = { @JoinColumn(name = "activity_id") })
    public List<Activity> getActivitiesPrev() {
        return activitiesPrev;
    }

    public void setActivitiesPrev(List<Activity> activitiesPrev) {
        this.activitiesPrev = activitiesPrev;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "activity_arrow_current",
            joinColumns = { @JoinColumn(name = "arrow_id") },
            inverseJoinColumns = { @JoinColumn(name = "activity_id") })
    public List<Activity> getActivityCurrent() {
        return activityCurrent;
    }

    public void setActivityCurrent(List<Activity> activityCurrent) {
        this.activityCurrent = activityCurrent;
    }

    @Transient
    @ManyToOne( cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "process_id",referencedColumnName = "ID", nullable = false,insertable=true, updatable=true)
    public Process getArrowsProcess() {
        return arrowsProcess;
    }

    public void setArrowsProcess(Process arrowsProcess) {
        this.arrowsProcess = arrowsProcess;
    }

    @Transient
    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.REMOVE},mappedBy = "arrow")
    public List<Condition> getCondition() {
        return condition;
    }

    public void setCondition(List<Condition> condition) {
        this.condition = condition;
    }
}
