package com.example.wilmacarefirebase.ui.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.wilmacarefirebase.R;
import com.example.wilmacarefirebase.login.Login;
import com.example.wilmacarefirebase.ui.MainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.StorageReference;

import java.util.ResourceBundle;
import java.util.concurrent.Executor;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private static final int GALLERY_INTENT_CODE = 1023;
    TextView fullName, email, phone, verifyMsg;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    String userId;
    Button resendCode;
    Button resetPassLocal, changeProfileImage;
    FirebaseUser user;
    ImageView profileImage;
    StorageReference storageReference;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        phone = root.findViewById(R.id.profilePhone);
        fullName = root.findViewById(R.id.profileName);
        email = root.findViewById(R.id.profileEmail);
        resetPassLocal = root.findViewById(R.id.resetPasswordLocal);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        userId = firebaseAuth.getCurrentUser().getUid();
        FirebaseUser user = firebaseAuth.getCurrentUser();


        //hvorfor får den kun fat i email?????? ser ud som om den godt kan finde email fra din cloud firestore. OBS kan også finde Uid, men ikke navn og telefon :(
        //TODO: find ud af det homie

            phone.setText(user.getDisplayName());
            fullName.setText(user.getDisplayName());
            email.setText(user.getEmail());



        return root;
}

}