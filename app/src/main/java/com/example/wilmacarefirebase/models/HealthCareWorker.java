package com.example.wilmacarefirebase.models;

public class HealthCareWorker {

    private String fullname;
    private String phoneNumber;
    private String email;
    private String worksAt;

    public HealthCareWorker(String fullname, String phoneNumber, String email, String worksAt) {
        this.fullname = fullname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.worksAt = worksAt;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWorksAt() {
        return worksAt;
    }

    public void setWorksAt(String worksAt) {
        this.worksAt = worksAt;
    }
}
