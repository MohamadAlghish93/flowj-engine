package com.flowjava.shared;

public class ResponseService {
    private Object data;
    private String message;
    private int responseCode;
    private boolean status;
    public ResponseService() {
        responseCode = 200; //default HTTP 200 OK
        status = true;
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
