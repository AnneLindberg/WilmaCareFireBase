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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.wilmacarefirebase.R;
import com.example.wilmacarefirebase.login.Login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private static final int GALLERY_INTENT_CODE = 1023;
    TextView displayname, email, phonenumber;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    String userId;
    ImageView btnLogOut;
    FirebaseUser user;
    ImageView profileImage;
    StorageReference storageReference;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        phonenumber = root.findViewById(R.id.profilePhone);
        displayname = root.findViewById(R.id.profileName);
        email = root.findViewById(R.id.profileEmail);
        btnLogOut = root.findViewById(R.id.btnLogOut);
       // resetPassLocal = root.findViewById(R.id.resetPasswordLocal);


        //fetching user data from firestore
        firebaseAuth = FirebaseAuth.getInstance();

        //TODO:: why u no work
        userId = firebaseAuth.getCurrentUser().getUid();
        FirebaseUser user = firebaseAuth.getCurrentUser();

            phonenumber.setText(user.getPhoneNumber());
            displayname.setText(user.getDisplayName());
            email.setText(user.getEmail());

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