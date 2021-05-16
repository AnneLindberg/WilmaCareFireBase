package com.example.wilmacarefirebase.models;

import com.google.firebase.firestore.DocumentId;

public class DashboardPost {

    @DocumentId
    private String post_id;
    private String username;
    private String title;
    private String description;
    private String image;

   // private ArrayList<DashboardPost> postsList;
    //private SimpleDateFormat date = new SimpleDateFormat("dd-M-yyyy");

    public DashboardPost() {
    }

    public DashboardPost(String post_id, String username, String title, String description, String image) {
        this.username = username;
        this.post_id = post_id;
        this.title = title;
        this.description = description;
        this.image = image;
        //this.postsList = new ArrayList<>();
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
