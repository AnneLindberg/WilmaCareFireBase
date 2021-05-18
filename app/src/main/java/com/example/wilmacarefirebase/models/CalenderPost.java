package com.example.wilmacarefirebase.models;

import java.util.Date;

public class CalenderPost {
    private String title;
    private String description;
    private String image;
    private String date;
 //   private SimpleDateFormat date = new SimpleDateFormat("dd-M-yyyy");

    public CalenderPost(String username, String description, String image, String  date) {
        this.title = username;
        this.description = description;
        this.date = date;
    }

    public CalenderPost() {
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}


