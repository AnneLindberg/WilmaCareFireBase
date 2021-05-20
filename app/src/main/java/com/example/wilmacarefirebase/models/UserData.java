package com.example.wilmacarefirebase.models;

public class UserData {

    private String username;
    private String imageURL;

    public UserData(String imageURL, String username) {
        this.username = username;
        this.imageURL = imageURL;
    }

    public UserData() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
