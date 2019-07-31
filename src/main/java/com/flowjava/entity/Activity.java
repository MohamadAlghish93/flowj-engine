package com.flowjava.entity;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "activity")
public class Activity {

    private UUID id;
    private String name;
    private int status;
    private List<GroupUser> groupUsers;
    private List<Variable> variables;
    private Process processActivity;
    private Arrow arrowPrev;
    private Arrow arrowNext;
    private Arrow currentArrow;


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
    @Column(name = "status", nullable = false)
    public int getStatus() {
        return status;
    }


    public void setStatus(int status) {
        this.status = status;
    }

    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.REMOVE},mappedBy = "activityGroupUser")
    public List<GroupUser> getGroupUsers() {
        return groupUsers;
    }

    public void setGroupUsers(List<GroupUser> groupUsers) {
        this.groupUsers = groupUsers;
    }



    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.REMOVE},mappedBy = "activityVariable")
    public List<Variable> getVariables() {
        return variables;
    }

    public void setVariables(List<Variable> variables) {
        this.variables = variables;
    }

    @ManyToOne
    @JoinColumn(name = "process_id", referencedColumnName = "ID", insertable=false, updatable=false)
    public Process getProcessActivity() {
        return processActivity;
    }

    public void setProcessActivity(Process processActivity) {
        this.processActivity = processActivity;
    }

    @ManyToOne
    @JoinColumn(name = "arrow_id", referencedColumnName = "ID", insertable=false, updatable=false)

    public Arrow getArrowPrev() {
        return arrowPrev;
    }

    public void setArrowPrev(Arrow arrowPrev) {
        this.arrowPrev = arrowPrev;
    }

    @ManyToOne
    @JoinColumn(name = "arrow_id", referencedColumnName = "ID", insertable=false, updatable=false)

    public Arrow getArrowNext() {
        return arrowNext;
    }

    public void setArrowNext(Arrow arrowNext) {
        this.arrowNext = arrowNext;
    }

    @ManyToOne
    @JoinColumn(name = "arrow_id", referencedColumnName = "ID", insertable=false, updatable=false)
    public Arrow getCurrentArrow() {
        return currentArrow;
    }

    public void setCurrentArrow(Arrow currentArrow) {
        this.currentArrow = currentArrow;
    }
}
