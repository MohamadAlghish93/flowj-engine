package com.flowengine.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.UUID;


@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"},ignoreUnknown = true)
@Entity
@Table(name = "Variable_File")
public class VariableFile {
    private UUID id;
    private String URL;
    private UUID variableId;
    private Variable variableFromFile;

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
    @Column(name = "URL", nullable = false, length = 2048)
    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    @Basic
    @Column(name = "variable_id")
    public UUID getVariableId() {
        return variableId;
    }

    public void setVariableId(UUID variableId) {
        this.variableId = variableId;
    }


    @Transient
    @ManyToOne( cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "variable_id",referencedColumnName = "ID", nullable = false,insertable=false, updatable=false)

    public Variable getVariableFromFile() {
        return variableFromFile;
    }

    public void setVariableFromFile(Variable variableFromFile) {
        this.variableFromFile = variableFromFile;
    }
}
