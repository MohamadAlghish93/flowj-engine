package com.flowjava.entity;

import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "activity")
public class Activity {

    private UUID id;
    private String name;
    private int status;
    private UUID processId;
    private List<GroupUser> groupUsers;
    private List<Variable> variables;
    private Process processActivity;
    private List<Arrow> arrowPrev;
    private List<Arrow> arrowNext;
    private List<Arrow> arrowCurrent;


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
    @Column(name = "status", nullable = false)
    public int getStatus() {
        return status;
    }


    public void setStatus(int status) {
        this.status = status;
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
    @JoinTable(name = "activity_group_user",
            joinColumns = { @JoinColumn(name = "activity_id") },
            inverseJoinColumns = { @JoinColumn(name = "group_user_id") })
    public List<GroupUser> getGroupUsers() {
        return groupUsers;
    }

    public void setGroupUsers(List<GroupUser> groupUsers) {
        this.groupUsers = groupUsers;
    }



    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "activity_variable",
            joinColumns = { @JoinColumn(name = "activity_id") },
            inverseJoinColumns = { @JoinColumn(name = "variable_id") })
    public List<Variable> getVariables() {
        return variables;
    }

    public void setVariables(List<Variable> variables) {
        this.variables = variables;
    }

    @Transient
    @ManyToOne( cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "process_id",referencedColumnName = "ID", nullable = false,insertable=true, updatable=true)
    public Process getProcessActivity() {
        return processActivity;
    }

    public void setProcessActivity(Process processActivity) {
        this.processActivity = processActivity;
    }

    @ManyToMany(mappedBy = "activitiesPrev")
    public List<Arrow> getArrowPrev() {
        return arrowPrev;
    }
    public void setArrowPrev(List<Arrow> arrowPrev) {
        this.arrowPrev = arrowPrev;
    }

    @ManyToMany(mappedBy = "activitiesNext")
    public List<Arrow> getArrowNext() {
        return arrowNext;
    }

    public void setArrowNext(List<Arrow> arrowNext) {
        this.arrowNext = arrowNext;
    }


    @ManyToMany(mappedBy = "activityCurrent")
    public List<Arrow> getArrowCurrent() {
        return arrowCurrent;
    }

    public void setArrowCurrent(List<Arrow> arrowCurrent) {
        this.arrowCurrent = arrowCurrent;
    }
}
