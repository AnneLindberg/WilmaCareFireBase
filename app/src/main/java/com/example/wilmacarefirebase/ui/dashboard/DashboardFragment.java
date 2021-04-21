package com.example.wilmacarefirebase.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wilmacarefirebase.R;
import com.example.wilmacarefirebase.data.DashboardPost;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private View DashBordFragmentView;
    private RecyclerView myDashboardsList;

    private DatabaseReference DashboardPostRequest, UsersRef, DashboardRef;
    private FirebaseAuth mAuth;
    private String currentUserID;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        DashBordFragmentView = inflater.inflate(R.layout.user_display_post, container, false);

        DashboardPostRequest = FirebaseDatabase.getInstance().getReference().child("Dashboard Request");
        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        myDashboardsList = DashBordFragmentView.findViewById(R.id.chat_requests_list);
     //   myDashboardsList.setLayoutManager(new LinearLayoutManager(getContext()));


        return DashBordFragmentView;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<DashboardPost> options = new FirebaseRecyclerOptions.Builder<DashboardPost>().setQuery(DashboardPostRequest.child(currentUserID), DashboardPost.class).build();
        FirebaseRecyclerAdapter<DashboardPost, RequestViewHolder> adapter = new FirebaseRecyclerAdapter<DashboardPost, RequestViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull RequestViewHolder requestViewHolder, int i, @NonNull DashboardPost dashboardPost) {
        //    requestViewHolder.itemView.findViewById(R.id.request_accept_btn).setVisibility(View.VISIBLE);

            final String list_userid = getRef(i).getKey();

            DatabaseReference getTypeOfRef = getRef(i).child("request_type");
            }

            @NonNull
            @Override
            public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
               View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_display_post, parent, false);
               RequestViewHolder holder = new RequestViewHolder(view);
               return holder;
            }
        };
    }

    public static class  RequestViewHolder extends RecyclerView.ViewHolder{

        TextView username, usermessage;
        ImageView profileImage;
        Button AcceptButton;

        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.user_profile_name);
            usermessage = itemView.findViewById(R.id.user_message);
            profileImage = itemView.findViewById(R.id.users_profile_image);
          //  AcceptButton = itemView.findViewById(R.id.request_accept_btn);

        }
    }
}