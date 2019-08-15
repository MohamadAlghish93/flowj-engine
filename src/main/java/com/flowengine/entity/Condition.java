package com.flowengine.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.UUID;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"},ignoreUnknown = true)
@Entity
@Table(name = "condition")
public class Condition {

    private UUID id;
    private String value;
    private UUID arrowId;
    private Arrow arrow;

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
    @Column(name = "value", nullable = true, length = 500)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    @Basic
    @Column(name = "arrow_id", nullable = true)
    public UUID getArrowId() {
        return arrowId;
    }

    public void setArrowId(UUID arrowId) {
        this.arrowId = arrowId;
    }

    @Transient
    @ManyToOne( cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "arrow_id",referencedColumnName = "ID", nullable = true,insertable=true, updatable=true)
    public Arrow getArrow() {
        return arrow;
    }

    public void setArrow(Arrow arrow) {
        this.arrow = arrow;
    }
}
