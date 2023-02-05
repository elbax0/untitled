package com.example.untitled.dto;

import lombok.ToString;

@ToString
public class Hello {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Hello() {
    }

    public Hello(String message) {
        this.message = message;
    }
}
