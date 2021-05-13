package com.example.wilmacarefirebase.data;

import androidx.lifecycle.LiveData;

import com.example.wilmacarefirebase.models.DashboardPost;
import com.google.firebase.auth.FirebaseUser;

public class WilmaRepository {

    private WilmaCareDao dao;
    private static WilmaRepository instance;


    private static final String TAG = "WilmaRepository";


    public WilmaRepository() {
        this.dao = WilmaCareDao.getInstance();
    }

    public static WilmaRepository getInstance(){
        if (instance == null){
            instance = new WilmaRepository();
        }
        return instance;
    }

    public LiveData<DashboardPost> getPost() {
        return dao.getDashPostFB();
    }


    public void updatePosts(DashboardPost dashboardPost) {
        dao.updatePost(dashboardPost);
    }

    public void update(DashboardPost feedPost) {
    }

    public void delete(DashboardPost feedPost) {
    }

    public void deleteAllPost() {
    }

    public void addPost(DashboardPost post) {
        dao.addPost(post);
    }

    public FirebaseUser getUser(){
        return dao.getUser();
    }
}
