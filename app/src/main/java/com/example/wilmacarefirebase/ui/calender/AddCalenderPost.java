package com.example.wilmacarefirebase.ui.calender;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.wilmacarefirebase.R;
import com.example.wilmacarefirebase.ui.MainActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class AddCalenderPost extends AppCompatActivity {

    private CollectionReference collectionReference;
    private AddCalenderPost viewModel;
    private EditText editTextDescription, editTextTitle, editTextdate;
    private Button buttonAddCalenderPost;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private String userID;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_calender_post);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        buttonAddCalenderPost = findViewById(R.id.btnSaveCalenderPost);
        editTextDescription = findViewById(R.id.edtCalDesc);
        editTextTitle = findViewById(R.id.edtCalenderTitle);
        editTextdate = findViewById(R.id.editTextDate);

        collectionReference = FirebaseFirestore.getInstance().collection("calenderpost");

        buttonAddCalenderPost.setOnClickListener(view -> {
            final String title = editTextTitle.getText().toString().trim();
            final String date = editTextdate.getText().toString();
            final String description = editTextDescription.getText().toString().trim();
            userID = firebaseAuth.getCurrentUser().getUid();

            DocumentReference reference = firebaseFirestore.collection("calenderpost").document(userID);
            Map<String, Object> calenderpost = new HashMap<>();
            calenderpost.put("title", title);
            calenderpost.put("description", description);
            calenderpost.put("date", date);
            reference.set(calenderpost).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d("ms","onSuccess: calenderpost is added" + userID);
                }
            });
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        });
        //TODO:: open calender fragment instead

    }
    }
