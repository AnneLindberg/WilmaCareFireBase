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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    EditText mFullName, mEmail, mPassword, mPhone;
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

        mFullName = findViewById(R.id.edtextPersonName);
        mEmail = findViewById(R.id.edtextEmail);
        mPassword = findViewById(R.id.edtextPassword);
        mPhone = findViewById(R.id.edtextPhone);
        mRegisterBtn = findViewById(R.id.btnRegister);
        alreadyRegistered = findViewById(R.id.txtCreateText);

        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);
        firebaseFirestore = FirebaseFirestore.getInstance();


        //check if user already exist, if yes just go to MainActivity
        if(firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        mRegisterBtn.setOnClickListener(v -> {
            final String email = mEmail.getText().toString().trim();
            String password = mPassword.getText().toString().trim();
            final String fullName = mFullName.getText().toString();
            final String phone    = mPhone.getText().toString();

            if(TextUtils.isEmpty(email)){
                mEmail.setError("Email is Required.");
                return;
            }

            if(TextUtils.isEmpty(password)){
                mPassword.setError("Password is Required.");
                return;
            }

            if(password.length() < 6){
                mPassword.setError("Password Must be more than 6 Characters");
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
                    user.put("displayname", fullName);
                    user.put("email", email);
                    user.put("phone", phone);
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

        alreadyRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
    }
}