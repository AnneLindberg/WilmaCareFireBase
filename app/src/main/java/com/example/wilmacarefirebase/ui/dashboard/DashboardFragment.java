package com.example.wilmacarefirebase.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wilmacarefirebase.R;
import com.example.wilmacarefirebase.data.OnDataAdded;
import com.example.wilmacarefirebase.models.DashboardPost;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class DashboardFragment extends Fragment implements OnDataAdded {

    public static final int ADD_POST_REQUEST = 1;
    private static final String TAG = "DashboardFragment";
    private ArrayList<DashboardPost> dashboardPostsList = new ArrayList<>();
    private DashPostAdapter dashPostAdapter;
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


        final DashPostAdapter adapter = new DashPostAdapter();
        recyclerView.setAdapter(dashPostAdapter);

        viewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        viewModel.init(DashboardFragment.this);
        viewModel.getPost().observe(getViewLifecycleOwner(), new Observer<ArrayList<DashboardPost>>() {
            @Override
            public void onChanged(ArrayList<DashboardPost> dashboardPosts) {
                dashPostAdapter.setPost(dashboardPosts);
            }
        });

        buttonAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddPostFragment.class);
                startActivityForResult(intent, ADD_POST_REQUEST);
            }
        });
        return root;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_POST_REQUEST || requestCode == RESULT_OK){
            String username = data.getStringExtra(AddPostFragment.EXTRA_USERNAME);
            String post = data.getStringExtra(AddPostFragment.EXTRA_DESCRIPTION);

            DashboardPost p = new DashboardPost(username,post, null);
        viewModel.insert(p);
            Log.e("added note","added post to db");
        }else {
            Log.e("ERROR","Post not saved to db");
        }
    }

    @Override
    public void added() {
        dashPostAdapter.notifyDataSetChanged();

    }


    @Override
    public void onStart() {
        super.onStart();

            }

    }


