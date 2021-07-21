package com.example.motoheal;

public class Feedback {

    String name,email,message,userId;

    public Feedback() {
    }

    public Feedback(String name, String email, String message, String userId) {
        this.name = name;
        this.email = email;
        this.message = message;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
