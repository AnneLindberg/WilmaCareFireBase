package com.example.wilmacarefirebase.data;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.wilmacarefirebase.models.DashboardPost;
import com.example.wilmacarefirebase.ui.dashboard.DashboardFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class WilmaRepository {

    private WilmaCareDao dao;
    private static WilmaRepository instance;
    private ArrayList<DashboardPost> dashboardPostArrayList = new ArrayList<>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static OnDataAdded onDataAdded;


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

    public MutableLiveData<ArrayList<DashboardPost>> getPost() {
        loadPostData();

        MutableLiveData<ArrayList<DashboardPost>> data = new MutableLiveData<>();
        data.setValue(dashboardPostArrayList);
        return data;
    }

    private void loadPostData() {
        db.collection("dashpost").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(!queryDocumentSnapshots.isEmpty()){
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                    for(DocumentSnapshot documentSnapshot : list){
                        dashboardPostArrayList.add(documentSnapshot.toObject(DashboardPost.class));
                    }

                    Log.e(TAG,"onSuccess: added to db");
                   // onDataAdded.added();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Log.e(TAG, "onFailure", e);
            }
        });
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
