package com.example.wilmacarefirebase.ui.resident;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.wilmacarefirebase.R;

public class ResidentFragment extends Fragment {

    private ResidentViewModel viewModel;
    private View root;
    ImageView aboutResident;

    public static ResidentFragment newInstance() {
        return new ResidentFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(ResidentViewModel.class);
        root = inflater.inflate(R.layout.fragment_resident, container, false);
        aboutResident = root.findViewById(R.id.btnMoreAboutresident);


        //open about resident
        aboutResident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AboutResidentFragment nextFrag= new AboutResidentFragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.navigation_aboutresident, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        return root;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
            viewModel = new ViewModelProvider(this).get(ResidentViewModel.class);
            viewModel.getResidentLiveData();
    }



}