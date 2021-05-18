package com.example.wilmacarefirebase.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.wilmacarefirebase.models.CalenderPost;
import com.example.wilmacarefirebase.models.DashboardPost;
import com.example.wilmacarefirebase.models.HealthCareWorker;
import com.example.wilmacarefirebase.models.Resident;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WilmaRepository {

    private final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private final CollectionReference collectionReferencDashPost = firebaseFirestore.collection("dashboardlist");
    private final CollectionReference collectionReferenceCalender = firebaseFirestore.collection("calenderpost");
    private final CollectionReference collectionReferenceResident = firebaseFirestore.collection("resident");
    private final CollectionReference collectionReferenceWorker = firebaseFirestore.collection("healthcareworker");


    private MutableLiveData<List<DashboardPost>> postLiveData = new MutableLiveData<>();
    private static WilmaRepository instance;


    private OnFirestoreTaskCompleteDashPost onFirestoreTaskCompleteDashPost;
    private OnFireStoreTaskCompleteResident onFireStoreTaskCompleteResident;
    private OnFireStoreTaskCompleteHealthCareWorker onFireStoreTaskCompleteHealthCareWorker;
    private OnFireStoreTaskCompleteCalender onFireStoreTaskCompleteCalender;

    private static final String TAG = "WilmaRepository";

    public WilmaRepository(OnFireStoreTaskCompleteHealthCareWorker onFireStoreTaskCompleteHealthCareWorker) {
        this.onFireStoreTaskCompleteHealthCareWorker = onFireStoreTaskCompleteHealthCareWorker;
    }

    public WilmaRepository(OnFirestoreTaskCompleteDashPost onFirestoreTaskComplete) {
        this.onFirestoreTaskCompleteDashPost = onFirestoreTaskComplete;
    }

    public WilmaRepository(OnFireStoreTaskCompleteResident onFireStoreTaskCompleteResident) {
        this.onFireStoreTaskCompleteResident = onFireStoreTaskCompleteResident;
    }

    public WilmaRepository(OnFireStoreTaskCompleteCalender onFireStoreTaskCompleteCalender){
        this.onFireStoreTaskCompleteCalender = onFireStoreTaskCompleteCalender;
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

    public void getCalenderData(){
        collectionReferenceCalender.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    onFireStoreTaskCompleteCalender.calenderDataAdded(task.getResult().toObjects(CalenderPost.class));
                }else {
                    onFireStoreTaskCompleteCalender.onError(task.getException());
                }
            }
        });
    }
    public void getResidentData(){
        collectionReferenceResident.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                   // onFireStoreTaskCompleteResident.residentDataAdded(task.getResult().toObjects(Resident.class));
                }
            }
        });
    }

    public void getHealthCareWorkerData(){
        collectionReferenceWorker.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    onFireStoreTaskCompleteHealthCareWorker.healthcareDataAdded(task.getResult().toObjects(HealthCareWorker.class));
                }
            }
        });
    }
    public void addPost() {

    }

    public interface OnFirestoreTaskCompleteDashPost {
        void postDataAdded(List<DashboardPost> dashboardPostList);
        void onError(Exception e);
    }

    public interface  OnFireStoreTaskCompleteResident{
        void residentDataAdded(List<Resident> residentList);
        void onError(Exception e);
    }

    public interface OnFireStoreTaskCompleteHealthCareWorker{
        void healthcareDataAdded(List<HealthCareWorker> careWorkerList);
        void onError(Exception e);
    }

    public interface OnFireStoreTaskCompleteCalender{
        void calenderDataAdded(List<CalenderPost> calenderPostList);
        void onError(Exception e);
    }

}
