package com.example.wilmacarefirebase.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.wilmacarefirebase.models.DashboardPost;
import com.example.wilmacarefirebase.models.Resident;
import com.example.wilmacarefirebase.models.UserData;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.StorageReference;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UserRepository {

    private final UserLiveData currentUser;
    private final Application application;
    private static UserRepository instance;
    private FirebaseFirestore firebaseFirestore;
    private CollectionReference collectionReferenceUser = firebaseFirestore.collection("users");
    private StorageReference storageReference;
    private UserData temp;
    private String imageURL;
    private MutableLiveData<List<UserData>> allProfiles;

    private OnFireStoreTaskCompleteUser onFireStoreTaskCompleteUser;


    public UserRepository(Application app) {
        this.application = app;
        currentUser = new UserLiveData();
    }


    public static synchronized UserRepository getInstance(Application app) {
        if (instance == null)
            instance = new UserRepository(app);
        return instance;
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return currentUser;
    }

    public void signOut() {
        AuthUI.getInstance().signOut(application.getApplicationContext());
    }



public interface  OnFireStoreTaskCompleteUser{
    void userData(List<Resident> residentList);
    void onError(Exception e);
}
}

