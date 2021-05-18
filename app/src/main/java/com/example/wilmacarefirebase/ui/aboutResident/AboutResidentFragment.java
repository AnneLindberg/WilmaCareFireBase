package com.example.wilmacarefirebase.ui.aboutResident;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.wilmacarefirebase.R;


public class AboutResidentFragment extends AppCompatActivity {


    private AboutResidentViewModel viewModel;
    private Button aboutResident;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_about_resident);

    }
}
