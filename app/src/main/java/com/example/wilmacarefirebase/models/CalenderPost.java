package com.example.wilmacarefirebase.models;

import java.text.SimpleDateFormat;

public class CalenderPost {
    private String title;
    private String message;
    private String image;
 //   private SimpleDateFormat date = new SimpleDateFormat("dd-M-yyyy");

    public CalenderPost(String username, String message, String image) {
        this.title = username;
        this.message = message;
        this.image = image;
    }

    public CalenderPost() {
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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


}


