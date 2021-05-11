package com.example.wilmacarefirebase.models;

public class Resident {

    String firstname;
    String lastname;
    int bornIn;
    String description;

    //normally would not do it like this, but the clients wanted that you should put it in manually..
    String nameOfContactPerson;
    String phoneNumberContactPerson;


    public Resident(String firstname, String lastname, int bornIn, String description, String nameOfContactPerson, String phoneNumberContactPerson) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.bornIn = bornIn;
        this.description = description;
        this.nameOfContactPerson = nameOfContactPerson;
        this.phoneNumberContactPerson = phoneNumberContactPerson;
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

    public String getNameOfContactPerson() {
        return nameOfContactPerson;
    }

    public void setNameOfContactPerson(String nameOfContactPerson) {
        this.nameOfContactPerson = nameOfContactPerson;
    }

    public String getPhoneNumberContactPerson() {
        return phoneNumberContactPerson;
    }

    public void setPhoneNumberContactPerson(String phoneNumberContactPerson) {
        this.phoneNumberContactPerson = phoneNumberContactPerson;
    }
}
