package com.example.wilmacarefirebase.models;

import java.util.ArrayList;

public class DashboardPost {

    private String username;
    private String message;
    private String image;
    private ArrayList<DashboardPost> postsList;
    //private SimpleDateFormat date = new SimpleDateFormat("dd-M-yyyy");

    public DashboardPost(String username, String message, String image) {
        this.username = username;
        this.message = message;
        this.image = image;
        this.postsList = new ArrayList<>();
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


    public void addPost(DashboardPost post) {
        postsList.add(post);
    }


    public ArrayList<DashboardPost> getPostFirebase() {
        return postsList;
    }


}
