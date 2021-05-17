package com.example.wilmacarefirebase.ui.resident;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wilmacarefirebase.R;
import com.example.wilmacarefirebase.models.HealthCareWorker;
import com.example.wilmacarefirebase.ui.aboutResident.AboutResidentFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ResidentFragment extends Fragment {

    private ResidentViewModel viewModel;
    private View root;
    ImageView aboutResident;
    private EditText editTextFullname;
    private TextView fullname, email, phonenumber, worksat;
    private String workerID;

    public static ResidentFragment newInstance() {
        return new ResidentFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(ResidentViewModel.class);
        root = inflater.inflate(R.layout.fragment_resident, container, false);
        aboutResident = root.findViewById(R.id.btnMoreAboutresident);

        fullname = root.findViewById(R.id.txtContactPersonName);
        email = root.findViewById(R.id.txtEmailContactPerson);
        phonenumber = root.findViewById(R.id.txtContactPersonPhone);


        DocumentReference documentReference = FirebaseFirestore.getInstance().collection("healthcareworker").document("MAK1");
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                HealthCareWorker worker = documentSnapshot.toObject(HealthCareWorker.class);
                fullname.setText(worker.getFullname());
                email.setText(worker.getEmail());
                phonenumber.setText(worker.getPhonenumber());
            }
        });

        //open about resident
        aboutResident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AboutResidentFragment nextFrag = new AboutResidentFragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.navigation_aboutresident, nextFrag)
                        .addToBackStack(null)
                        .commit();
            }
        });


        return root;
    }





        @Override
        public void onActivityCreated (@Nullable Bundle savedInstanceState){
            super.onActivityCreated(savedInstanceState);
            viewModel = new ViewModelProvider(this).get(ResidentViewModel.class);
            viewModel.getWorkerList();
        }


    }