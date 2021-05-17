package com.example.wilmacarefirebase.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.wilmacarefirebase.models.DashboardPost;
import com.example.wilmacarefirebase.models.Resident;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WilmaRepository {

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private CollectionReference collectionReferencDashPost = firebaseFirestore.collection("dashboardlist");
    private CollectionReference collectionReferenceCalender = firebaseFirestore.collection("calenderpost");
    private CollectionReference collectionReferenceResident = firebaseFirestore.collection("resident");

    private WilmaCareDao dao;
    private MutableLiveData<List<DashboardPost>> postLiveData = new MutableLiveData<>();
    private static WilmaRepository instance;


    private OnFirestoreTaskCompleteDashPost onFirestoreTaskCompleteDashPost;
    private OnFireStoreTaskCompleteResident onFireStoreTaskCompleteResident;

    private static final String TAG = "WilmaRepository";

    public WilmaRepository(OnFirestoreTaskCompleteDashPost onFirestoreTaskComplete) {
        this.onFirestoreTaskCompleteDashPost = onFirestoreTaskComplete;
    }

    public WilmaRepository(OnFireStoreTaskCompleteResident onFireStoreTaskCompleteResident) {
        this.onFireStoreTaskCompleteResident = onFireStoreTaskCompleteResident;
    }

    public WilmaRepository() {

    }

    public void getPostData() {
        collectionReferencDashPost.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    onFirestoreTaskCompleteDashPost.postDataAdded(task.getResult().toObjects(DashboardPost.class));

                } else {
                    onFirestoreTaskCompleteDashPost.onError(task.getException());
                }
            }
        });
    }

    public void getResidentData(){
        collectionReferenceResident.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    onFireStoreTaskCompleteResident.residentDataAdded(task.getResult().toObjects(Resident.class));
                }
            }
        });
    }

    public void addPost() {
        dao.addPost(postLiveData);
    }

    public interface OnFirestoreTaskCompleteDashPost {
        void postDataAdded(List<DashboardPost> dashboardPostList);
        void onError(Exception e);
    }

    public interface  OnFireStoreTaskCompleteResident{
        void residentDataAdded(List<Resident> residentList);
        void onError(Exception e);
    }

}
