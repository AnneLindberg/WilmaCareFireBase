package com.example.wilmacarefirebase.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wilmacarefirebase.R;
import com.example.wilmacarefirebase.ui.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    EditText editTextFullName, editTextEmail, editTextPassword, editTextPhone;
    Button mRegisterBtn;
    TextView alreadyRegistered;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;
    FirebaseFirestore firebaseFirestore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextFullName = findViewById(R.id.edtextPersonName);
        editTextEmail = findViewById(R.id.edtextEmail);
        editTextPassword = findViewById(R.id.edtextPassword);
        editTextPhone = findViewById(R.id.edtextPhone);
        mRegisterBtn = findViewById(R.id.btnRegister);
        alreadyRegistered = findViewById(R.id.txtCreateText);

        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);
        firebaseFirestore = FirebaseFirestore.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        mRegisterBtn.setOnClickListener(v -> {
            final String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            final String fullName = editTextFullName.getText().toString();
            final String phone    = editTextPhone.getText().toString();

            if(TextUtils.isEmpty(email)){
                editTextEmail.setError("Email is Required.");
                return;
            }

            if(TextUtils.isEmpty(password)){
                editTextPassword.setError("Password is Required.");
                return;
            }

            if(password.length() < 6){
                editTextPassword.setError("Password Must be more than 6 Characters");
                return;
            }


            progressBar.setVisibility(View.VISIBLE);
            //register user in firebase

            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if(task.isSuccessful()){

                    //verify email

                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                    firebaseUser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(Register.this, "Verification Email Has been Sent.", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("ms", "onFailure: Email not sent " + e.getMessage());
                        }
                    });

                    Toast.makeText(Register.this,"User created.", Toast.LENGTH_SHORT).show();
                    userID = firebaseAuth.getCurrentUser().getUid();
                    DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);
                    Map<String, Object> user = new HashMap<>();
                    user.put("editTextFullName", fullName);
                    user.put("editTextEmail", email);
                    user.put("editTextPhone", phone);
                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d("ms","onSuccess: user profile is created" + userID);
                        }
                    });
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {
                    Toast.makeText(Register.this, "Error !!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            });

        });

        //TODO: ask Kasper why this is not working...
        alreadyRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
    }
}