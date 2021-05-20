package com.example.wilmacarefirebase.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.wilmacarefirebase.R;
import com.example.wilmacarefirebase.login.EditProfile;
import com.example.wilmacarefirebase.login.Login;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class HomeFragment extends Fragment {

    private HomeViewModel viewModel;
    private static final int GALLERY_INTENT_CODE = 1023;
    TextView displayname, email, phonenumber;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    StorageReference storageReference;
    String userId;
    ImageView btnLogOut, btnChangeProfile, profileImage;
    FirebaseUser user;
    private String phonenumberts, displaynamets;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        phonenumber = root.findViewById(R.id.profilePhone);
        displayname = root.findViewById(R.id.profileName);
        email = root.findViewById(R.id.profileEmail);
        btnLogOut = root.findViewById(R.id.btnLogOut);
      //  btnChangeProfile = root.findViewById(R.id.btnChangeProfile);
        profileImage = root.findViewById(R.id.profileImage);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();


        StorageReference profileRef = storageReference.child("users/" + firebaseAuth.getCurrentUser().getUid() + "/profile.jpeg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profileImage);
            }
        });

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


//        btnChangeProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), EditProfile.class);
//                intent.putExtra("displayname", displayname.getText().toString());
//                intent.putExtra("phone", phonenumber.getText().toString());
//                intent.putExtra("email", firebaseAuth.getCurrentUser().getEmail());
//                startActivity(intent);
//
//
////                Intent openGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
////                startActivityForResult(openGallery, 1000);
//            }
//        });



        return root;
}

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 1000){
            if(resultCode == Activity.RESULT_OK){
                Uri imageUri = data.getData();
               // profileImage.setImageURI(imageUri);

                uploadeImageToFireBase(imageUri);
            }
        }
    }

    private void uploadeImageToFireBase(Uri imageUri) {
        final StorageReference fileRef = storageReference.child("users/" + firebaseAuth.getCurrentUser().getUid() + "/profile.jpeg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
               fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                   @Override
                   public void onSuccess(Uri uri) {
                       Picasso.get().load(uri).into(profileImage);
                   }
               });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(getActivity(), "Fejl", Toast.LENGTH_SHORT).show();
            }
        });


    }
}

