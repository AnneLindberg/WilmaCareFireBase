package com.example.wilmacarefirebase.models;

import java.io.Serializable;

public class User implements Serializable {

    public String uid;
    public String name;
    public String email;


    public User(String uid, String name, String email) {
        this.uid = uid;
        this.name = name;
        this.email = email;
    }

    public User() {

    }



}
