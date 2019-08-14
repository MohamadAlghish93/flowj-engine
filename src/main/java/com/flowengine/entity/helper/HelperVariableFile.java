package com.flowengine.entity.helper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"},ignoreUnknown = true)
public class HelperVariableFile {
    private UUID guidVariable;
    private UUID guidActivitty;

    public UUID getGuidVariable() {
        return guidVariable;
    }

    public void setGuidVariable(UUID guidVariable) {
        this.guidVariable = guidVariable;
    }

    public UUID getGuidActivitty() {
        return guidActivitty;
    }

    public void setGuidActivitty(UUID guidActivitty) {
        this.guidActivitty = guidActivitty;
    }
}
