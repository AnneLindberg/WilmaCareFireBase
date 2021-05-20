package com.example.wilmacarefirebase.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.wilmacarefirebase.R;
import com.example.wilmacarefirebase.login.EditProfile;
import com.example.wilmacarefirebase.login.Login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.Executor;

public class HomeFragment extends Fragment {

    private HomeViewModel viewModel;
    private static final int GALLERY_INTENT_CODE = 1023;
    TextView displayname, email, phonenumber;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    String userId;
    ImageView btnLogOut, changeProfile;
    FirebaseUser user;
    ImageView profileImage;
    StorageReference storageReference;
    private String phonenumberts, displaynamets;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        phonenumber = root.findViewById(R.id.profilePhone);
        displayname = root.findViewById(R.id.profileName);
        email = root.findViewById(R.id.profileEmail);
        btnLogOut = root.findViewById(R.id.btnLogOut);
        changeProfile = root.findViewById(R.id.btnChangeProfile);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        userId = firebaseAuth.getCurrentUser().getUid();

        DocumentReference documentReference = firebaseFirestore.collection("users").document(userId);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot value, FirebaseFirestoreException error) {
                displayname.setText(value.getString("displayname"));
                phonenumber.setText(value.getString("phone"));
                email.setText(value.getString("email"));
            }
        });

        FirebaseAuth.getInstance().addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull @NotNull FirebaseAuth firebaseAuth) {
                userId = firebaseAuth.getCurrentUser().getUid();
                firebaseAuth = FirebaseAuth.getInstance();
                firebaseFirestore = FirebaseFirestore.getInstance();
                user = firebaseAuth.getCurrentUser();
                storageReference = FirebaseStorage.getInstance().getReference();
                displaynamets = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getDisplayName();

                phonenumber.setText(user.getPhoneNumber());
                displayname.setText(user.getDisplayName());
                email.setText(user.getEmail());
            }
        });

            //logout
            btnLogOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getContext(), Login.class));
                }
            });



        return root;
}

}