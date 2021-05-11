package com.example.wilmacarefirebase.data;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.wilmacarefirebase.models.DashboardPost;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WilmaCareDao {

    private MutableLiveData<DashboardPost> dashPostML;
    private FirebaseUser user;
    private FirebaseDatabase database;
    private static WilmaCareDao instance;


    public WilmaCareDao() {
        database = FirebaseDatabase.getInstance();
        dashPostML = new MutableLiveData<>();

        dashPostML.observeForever(new Observer<DashboardPost>() {
            @Override
            public void onChanged(DashboardPost post) {
                DatabaseReference reference = database.getReference("dashboard").child(user.getUid());
                reference.setValue("hello");
            }
        });

    }

    public static synchronized WilmaCareDao getInstance(Context context) {
        if (instance == null) {
            instance = null;

        }
        return instance;
    }

    public void addPost(DashboardPost post) {
        DashboardPost post1 = dashPostML.getValue();
        post1.addPost(post);
        dashPostML.setValue(post1);
    }

    public MutableLiveData<DashboardPost> getDashPostFB() {
        return dashPostML;
    }
}
