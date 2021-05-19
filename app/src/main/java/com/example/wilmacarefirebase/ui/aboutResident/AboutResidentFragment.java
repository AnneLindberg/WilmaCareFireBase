package com.example.wilmacarefirebase.ui.aboutResident;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.wilmacarefirebase.R;
import com.example.wilmacarefirebase.models.HealthCareWorker;
import com.example.wilmacarefirebase.models.Resident;
import com.example.wilmacarefirebase.ui.resident.ResidentViewModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class AboutResidentFragment extends AppCompatActivity {

    private AboutResidentViewModel viewModel;
    private TextView residentName, desc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_about_resident);

        residentName = findViewById(R.id.residentName);
        desc = findViewById(R.id.residentDescription);


        DocumentReference documentReference = FirebaseFirestore.getInstance().collection("resident").document("oy6H0JPPm7ooEMSjKqni");
        documentReference.get().addOnSuccessListener(documentSnapshot -> {
            Resident resident = documentSnapshot.toObject(Resident.class);
            residentName.setText(resident.getFirstname() + " " + resident.getLastname());
            desc.setText(resident.getDescription());
        });

    }


}
