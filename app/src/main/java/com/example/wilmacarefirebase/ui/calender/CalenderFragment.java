package com.example.wilmacarefirebase.ui.calender;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wilmacarefirebase.R;
import com.example.wilmacarefirebase.data.CalenderAdapter;
import com.example.wilmacarefirebase.models.CalenderPost;
import com.example.wilmacarefirebase.models.DashboardPost;
import com.example.wilmacarefirebase.ui.dashboard.AddPostFragment;
import com.example.wilmacarefirebase.ui.dashboard.DashboardViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CalenderFragment extends Fragment {

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

        buttonAddCalender = root.findViewById(R.id.btnNewCalenderPost);
        recyclerView = root.findViewById(R.id.recycler_view_calender);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.hasFixedSize();

        List<CalenderPost> calenderPostList= new ArrayList<>();

        calenderAdapter = new CalenderAdapter();
        recyclerView.setAdapter(calenderAdapter);

        buttonAddCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), AddCalenderPost.class);
                getActivity().startActivity(intent);
            }
        });
        return root;
    }

    public static CalenderFragment getInstance(){
        CalenderFragment fragment = new CalenderFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(CalenderViewModel.class);
        viewModel.getCalenderPostLiveData().observe(getViewLifecycleOwner(), new Observer<List<CalenderPost>>() {
            @Override
            public void onChanged(List<CalenderPost> calenderPostList) {
                calenderAdapter.setCalenderPosts(calenderPostList);
                calenderAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

    }
}