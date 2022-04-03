package com.challenge;

public class ApiResponse<T> {
    private int code;
    private String msg;
    private T payload;
    public ApiResponse(int code, String msg, T payload) {
        this.code = code;
        this.msg = msg;
        this.payload = payload;
    }   
}
