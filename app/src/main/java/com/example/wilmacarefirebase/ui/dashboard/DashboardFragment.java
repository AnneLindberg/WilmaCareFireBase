package com.example.wilmacarefirebase.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wilmacarefirebase.R;
import com.example.wilmacarefirebase.models.DashboardPost;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DashboardFragment extends Fragment {

    public static final int ADD_POST_REQUEST = 1;
    private static final String TAG = "DashboardFragment";
    private ArrayList<DashboardPost> dashboardPostsList = new ArrayList<>();
    private DashPostAdapter adapterPost;
    private DashboardViewModel viewModel;
    private RecyclerView recyclerView;
    private View root;
    private Button buttonAddPost;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        buttonAddPost = root.findViewById(R.id.addNewPost);
        recyclerView = root.findViewById(R.id.recycler_view_dashboard);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.hasFixedSize();

        List<DashboardPost> dashboardPostList = new ArrayList<>();

        adapterPost = new DashPostAdapter();
        recyclerView.setAdapter(adapterPost);


        buttonAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), AddPostFragment.class);
                getActivity().startActivity(intent);
            }
        });
        return root;

    }

    public static DashboardFragment getInstance(){
        DashboardFragment fragment = new DashboardFragment();
        return fragment;

    }
    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(DashboardViewModel.class);
        viewModel.getPostLiveData().observe(getViewLifecycleOwner(), new Observer<List<DashboardPost>>() {
            @Override
            public void onChanged(List<DashboardPost> dashboardPostList) {
                adapterPost.setDashPost(dashboardPostList);
                adapterPost.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

    }

}


