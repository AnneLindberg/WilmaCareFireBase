package com.example.wilmacarefirebase.models;

public class Resident {

    String firstname;
    String lastname;
    int bornIn;
    String description;

    public Resident() {
    }

    public Resident(String firstname, String lastname, int bornIn, String description) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.bornIn = bornIn;
        this.description = description;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getBornIn() {
        return bornIn;
    }

    public void setBornIn(int bornIn) {
        this.bornIn = bornIn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
