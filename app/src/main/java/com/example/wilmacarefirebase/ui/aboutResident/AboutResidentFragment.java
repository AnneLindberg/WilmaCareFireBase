package com.example.wilmacarefirebase.ui.aboutResident;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.wilmacarefirebase.R;


public class AboutResidentFragment extends Fragment {


    private AboutResidentViewModel viewModel;
    private Button aboutResident;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(AboutResidentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_about_resident, container, false);

        return root;

    }

}
