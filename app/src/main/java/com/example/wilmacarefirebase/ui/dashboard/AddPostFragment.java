package com.example.wilmacarefirebase.ui.dashboard;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.wilmacarefirebase.R;
import com.example.wilmacarefirebase.models.DashboardPost;
import com.example.wilmacarefirebase.ui.MainActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AddPostFragment extends AppCompatActivity {

    private CollectionReference collectionReference;
    private AddPostViewModel viewModel;
    private EditText editTextDescription, editTextUserName, editTextTitle;
    private ImageView photoView;
    private Button buttonAddPost;
    private String imageId;
    private DashboardPost newPost;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    String userID;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_post);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        editTextDescription =findViewById(R.id.edtinputpost);
        editTextTitle = findViewById(R.id.edtTitle);
        editTextUserName = findViewById(R.id.edtwriteusername);
        buttonAddPost = findViewById(R.id.btnSavePost);
        photoView = findViewById(R.id.addphoto);

        //fandt eksempel til at oploade billeder online, men har senere glemt hvilken tutorial
        imageId = UUID.randomUUID().toString() + ".jpg";

        collectionReference = FirebaseFirestore.getInstance().collection("dashboardlist");

        buttonAddPost.setOnClickListener(view -> {
            final String username = editTextUserName.getText().toString().trim();
            final String title = editTextTitle.getText().toString().trim();
            final String description = editTextDescription.getText().toString().trim();
            userID = firebaseAuth.getCurrentUser().getUid();
            DocumentReference documentReference = firebaseFirestore.collection("dashboardlist").document(userID);
            Map<String, Object> post = new HashMap<>();
            post.put("username", username);
            post.put("title", title);
            post.put("description", description);
            post.put("image", null);
            documentReference.set(post).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.d("ms","onSuccess: post is added" + userID);
                }
            });
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            //TODO:: open dashboard fragment instead
        });
    }


}
