package com.example.wilmacarefirebase.models;

import java.util.ArrayList;

public class ModelManager {

    private ArrayList<DashboardPost> dashboardPosts;

    public ModelManager() {
        this.dashboardPosts = new ArrayList<>();
    }

    public ArrayList<DashboardPost> getDashboardPosts() {
        return dashboardPosts;
    }

    public void setDashboardPosts(ArrayList<DashboardPost> dashboardPosts) {
        this.dashboardPosts = dashboardPosts;
    }


}
