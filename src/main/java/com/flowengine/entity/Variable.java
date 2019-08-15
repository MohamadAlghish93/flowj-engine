package com.flowengine.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"},ignoreUnknown = true)
@Entity
@Table(name = "variable")
public class Variable {

    private UUID id;
    private String name;
    private String type;
    private String value;
    private Boolean required;
    private List<VariableOptionValue> variableOptionValues;
    private List<VariableFile> variableFiles;

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
    @Column(name = "name", nullable = false, length = 2048)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "type", nullable = false, length = 255)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "value", nullable = true, length = 2048)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "required", nullable = true)
    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    @Transient
    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.REMOVE},mappedBy = "variable")
    public List<VariableOptionValue> getVariableOptionValues() {
        return variableOptionValues;
    }

    public void setVariableOptionValues(List<VariableOptionValue> variableOptionValues) {
        this.variableOptionValues = variableOptionValues;
    }

    @Transient
    @OneToMany(orphanRemoval=true,fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.REMOVE},mappedBy = "variableFromFile")
    public List<VariableFile> getVariableFiles() {
        return variableFiles;
    }

    public void setVariableFiles(List<VariableFile> variableFiles) {
        this.variableFiles = variableFiles;
    }
}
