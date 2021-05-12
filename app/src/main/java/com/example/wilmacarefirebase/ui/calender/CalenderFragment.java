package com.example.wilmacarefirebase.ui.calender;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.wilmacarefirebase.R;

public class CalenderFragment extends Fragment {

    private CalenderViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(CalenderViewModel.class);
        View root = inflater.inflate(R.layout.fragment_calender, container, false);

        return root;
    }
}