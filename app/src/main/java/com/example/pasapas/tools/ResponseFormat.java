package com.example.pasapas.tools;

import com.google.gson.JsonObject;

public class ResponseFormat {
    String message;
    Integer code;
    JsonObject data;

    public ResponseFormat(String message, Integer code, JsonObject data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public ResponseFormat() {
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

    public JsonObject getData() {
        return data;
    }

    public void setData(JsonObject data) {
        this.data = data;
    }
}
