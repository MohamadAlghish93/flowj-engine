package com.flowengine.shared;

import java.util.HashMap;
import java.util.Map;

public class ResponseService {
    private Object data;
    Map<String, Object> map = new HashMap<String, Object>();
    private String message;
    private int responseCode;
    private boolean status;
    private boolean multiObject;
    public ResponseService() {
        this.map = new HashMap<String, Object>();
        this.multiObject = false;
        responseCode = 200; //default HTTP 200 OK
        status = true;
    }



    public void append_objects(String key , Object _objects) {
        this.map.put(key, _objects);
    }


    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public boolean isMultiObject() {
        return multiObject;
    }

    public void setMultiObject(boolean multiObject) {
        this.multiObject = multiObject;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object responseBody) {
        this.data = responseBody;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean isSuccess) {
        this.status = isSuccess;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
}
