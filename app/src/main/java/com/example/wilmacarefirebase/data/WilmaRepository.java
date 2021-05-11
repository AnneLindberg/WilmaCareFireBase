package com.example.wilmacarefirebase.data;

import androidx.lifecycle.MutableLiveData;

import com.example.wilmacarefirebase.models.DashboardPost;
import com.example.wilmacarefirebase.ui.dashboard.DashboardFragment;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class WilmaRepository {

    private WilmaCareDao dao;
    private static WilmaRepository instance;
    private ArrayList<DashboardPost> dashboardPostArrayList = new ArrayList<>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    private static final String TAG = "WilmaRepository";


    public static WilmaRepository getInstance(DashboardFragment context){
        if (instance == null){
            instance = new WilmaRepository();
        }
        return instance;
    }

    public static WilmaRepository getInstance() {
        if (instance == null){
            instance = new WilmaRepository();
        }
        return instance;
    }

    public MutableLiveData<DashboardPost> getPost() {
        return dao.getDashPostFB();
    }


    public void updatePosts(DashboardPost dashboardPost) {
    }

    public void insert(DashboardPost feedPost) {
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
}
