package com.example.wilmacarefirebase.data;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.wilmacarefirebase.models.DashboardPost;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WilmaRepository {

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private CollectionReference collectionReferencDashPost = firebaseFirestore.collection("dashboardlist");
    private CollectionReference collectionReferenceCalender = firebaseFirestore.collection("calenderpost");
    private WilmaCareDao dao;
    private MutableLiveData<List<DashboardPost>> postLiveData = new MutableLiveData<>();
    private static WilmaRepository instance;


    private OnFirestoreTaskComplete onFirestoreTaskComplete;

    private static final String TAG = "WilmaRepository";

    public WilmaRepository(OnFirestoreTaskComplete onFirestoreTaskComplete) {
        this.onFirestoreTaskComplete = onFirestoreTaskComplete;
    }

    public WilmaRepository() {

    }

    public void getPostData() {
        collectionReferencDashPost.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    onFirestoreTaskComplete.postDataAdded(task.getResult().toObjects(DashboardPost.class));

                } else {
                    onFirestoreTaskComplete.onError(task.getException());
                }
            }
        });
    }

    public void addPost() {
        dao.addPost(postLiveData);
    }

    public interface OnFirestoreTaskComplete {
        void postDataAdded(List<DashboardPost> dashboardPostList);

        void onError(Exception e);

    }

}
