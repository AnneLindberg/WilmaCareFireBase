package com.example.wilmacarefirebase.ui.calender;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wilmacarefirebase.R;
import com.example.wilmacarefirebase.models.CalenderPost;
import com.example.wilmacarefirebase.models.DashboardPost;

import java.util.ArrayList;
import java.util.List;

public class CalenderFragment extends Fragment {

    public static final int ADD_CALENDER_REQUEST = 1;
    private static final String TAG = "CalenderFragment";
    private ArrayList<CalenderPost> calenderPosts = new ArrayList<>();
    private CalenderAdapter calenderAdapter;
    private RecyclerView recyclerView;
    private View root;
    private Button buttonAddCalender;
    private CalenderViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel =
                new ViewModelProvider(this).get(CalenderViewModel.class);
        View root = inflater.inflate(R.layout.fragment_calender, container, false);

        buttonAddCalender = root.findViewById(R.id.addNewPost);
        recyclerView = root.findViewById(R.id.recycler_view_calender);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.hasFixedSize();

        List<CalenderPost> calenderPostList= new ArrayList<>();

        calenderPostList.add(new CalenderPost("Lægen", "Karen skal til læge", ""));
        calenderPostList.add(new CalenderPost("Kiroprakter", "Karefdsfdsfn skal til læge", ""));
        calenderPostList.add(new CalenderPost("dsfdsf", "Karedsfdsfdsfdsn skal til læge", ""));
        calenderPostList.add(new CalenderPost("sdffsddsf", "Karen skal fdsfstil læge", ""));
        calenderPostList.add(new CalenderPost("Lægsfdsfdsen", "Karefdsn skal tsdfdsil læge", ""));

        calenderAdapter = new CalenderAdapter(calenderPostList);
        recyclerView.setAdapter(calenderAdapter);
        return root;
    }
}