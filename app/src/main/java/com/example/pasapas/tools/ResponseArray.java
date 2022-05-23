package com.example.pasapas.tools;

import com.google.gson.JsonArray;

public class ResponseArray {
    String message;
    Integer code;
    JsonArray data;

    public ResponseArray(String message, Integer code, JsonArray data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public ResponseArray() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public JsonArray getData() {
        return data;
    }

    public void setData(JsonArray data) {
        this.data = data;
    }
}
