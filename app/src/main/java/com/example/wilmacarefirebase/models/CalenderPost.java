package com.example.wilmacarefirebase.models;

import java.text.SimpleDateFormat;

public class CalenderPost {
    private String username;
    private String message;
    private String image;
    private SimpleDateFormat date = new SimpleDateFormat("dd-M-yyyy");

    public CalenderPost(String username, String message, String image) {
        this.username = username;
        this.message = message;
        this.image = image;
    }

    public CalenderPost() {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public SimpleDateFormat getDate() {
        return date;
    }

    public void setDate(SimpleDateFormat date) {
        this.date = date;
    }
}


