package com.flowengine.entity.helper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"},ignoreUnknown = true)
public class VariableFile {
    private String guidVariable;
    private String guidActivitty;

    public String getGuidVariable() {
        return guidVariable;
    }

    public void setGuidVariable(String guidVariable) {
        this.guidVariable = guidVariable;
    }

    public String getGuidActivitty() {
        return guidActivitty;
    }

    public void setGuidActivitty(String guidActivitty) {
        this.guidActivitty = guidActivitty;
    }
}
