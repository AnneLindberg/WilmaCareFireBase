//package com.example.wilmacarefirebase.data;
//
//import android.util.Log;
//import androidx.annotation.NonNull;
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
//import androidx.lifecycle.Observer;
//
//import com.example.wilmacarefirebase.models.CalenderPost;
//import com.example.wilmacarefirebase.models.DashboardPost;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//import com.google.firebase.firestore.QuerySnapshot;
//
//import org.jetbrains.annotations.NotNull;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class WilmaCareDao {
//
//    private static final String TAG = "Dao";
//    private MutableLiveData<MutableLiveData<List<DashboardPost>>> dashPostML;
//    private MutableLiveData<CalenderPost> calenderPostMutableLiveData;
//    private FirebaseUser user;
//    FirebaseAuth firebaseAuth;
//    private FirebaseFirestore db;
//    private static WilmaCareDao instance;
//    private String userID;
//
//    public WilmaCareDao() {
//        db = FirebaseFirestore.getInstance();
//        dashPostML = new MutableLiveData<MutableLiveData<List<DashboardPost>>>();
//
//        readFromDB();
//        readPostFromDB();
//
//        DocumentReference documentReference = db.collection("dashpost").document("randompost");
//        Map<String, Object> dashboardPosts = new HashMap<>();
//        dashboardPosts.put("Læge", "title");
//        dashboardPosts.put("I dag skal Karen til læge", "beskrivelse");
//
//
//        documentReference.set(dashboardPosts).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                Log.d("ms","onSuccess: post is created" + dashboardPosts.toString());
//            }
//        });
//
//    }
//
//
//    public void readFromDB() {
//        db.collection("dashpost")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d(TAG, document.getId() + " => " + document.getData());
//                            }
//                        } else {
//                            Log.w(TAG, "Error getting documents.", task.getException());
//                        }
//                    }
//                });    }
//
//
//    public static synchronized WilmaCareDao getInstance() {
//        if (instance == null)
//            instance = new WilmaCareDao();
//        return instance;
//    }
//
//    public void addPost(MutableLiveData<List<DashboardPost>> post) {
//        dashPostML.setValue(post);
//    }
//
//
//    public void readPostFromDB(){
//       db.collection("dashpost").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//           @Override
//           public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
//               if (task.isSuccessful()) {
//                   for (QueryDocumentSnapshot document : task.getResult()) {
//                       Log.d(TAG, document.getId() + " => " + document.getData());
//                   }
//               } else {
//                   Log.d(TAG, "Error getting documents: ", task.getException());
//               }
//           }
//       });
//
//    }
//
//    public LiveData<MutableLiveData<List<DashboardPost>>> getDashPostFB() {
//        return dashPostML;
//
//
//    }
//
//    public void updatePost(DashboardPost dashboardPost) {
//    }
//
//
//    public FirebaseUser getUser() {
//        return user;
//    }
//
//
//}
