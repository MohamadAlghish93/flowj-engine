package com.flowjava.entity;


import com.sun.org.apache.bcel.internal.generic.FADD;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "variable")
public class Variable {

    private UUID id;
    private String name;
    private String type;
    private String value;
    private Activity activityVariable;

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
    @Column(name = "name",unique = true, nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "type",unique = true, nullable = false, length = 255)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "value", nullable = true, length = 500)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @ManyToOne
    @JoinColumn(name = "activity_id", referencedColumnName = "ID", insertable=false, updatable=false)
    public Activity getActivityVariable() {
        return activityVariable;
    }

    public void setActivityVariable(Activity activityVariable) {
        this.activityVariable = activityVariable;
    }
}
