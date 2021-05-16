package com.example.wilmacarefirebase.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import com.example.wilmacarefirebase.R;
import com.example.wilmacarefirebase.models.DashboardPost;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.UUID;

public class AddPostFragment extends Fragment {

    public static final String EXTRA_USERNAME = "com.example.WilmaCareFireBase.EXTRA_USERNAME";
    public static final String EXTRA_DESCRIPTION = "com.example.WilmaCareFireBase.EXTRA_DESCRIPTION";
    private CollectionReference collectionReference;
    private AddPostViewModel viewModel;
    private EditText editTextDescription, editTextUserName, editTextTitle;
    private ImageView photoView;
    private Button buttonAddPost;
    private String imageId;
    private DashboardPost newPost;

    @Override
    public View onCreate(@NonNull LayoutInflater inflater,
                         ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(AddPostViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_post, container, false);


        editTextDescription = root.findViewById(R.id.edtinputpost);
        editTextTitle = root.findViewById(R.id.edtTitle);
        editTextUserName = root.findViewById(R.id.edtwriteusername);
        buttonAddPost = root.findViewById(R.id.btnSavePost);
        photoView = root.findViewById(R.id.addphoto);

        //fandt eksempel til at oploade billeder online, men har senere glemt hvilken tutorial
        imageId = UUID.randomUUID().toString() + ".jpg";

        collectionReference = FirebaseFirestore.getInstance().collection("dashboardlist");

        editTextUserName.setText(newPost.getUsername());
        editTextTitle.setText(newPost.getTitle());
        editTextDescription.setText(newPost.getDescription());

        buttonAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPost.setUsername(editTextUserName.getText().toString());
                newPost.setTitle(editTextTitle.getText().toString());
                newPost.setDescription(editTextDescription.getText().toString());
                viewModel.addPost(newPost);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                DashboardFragment fragment = new DashboardFragment();
                fragmentTransaction.replace(R.id.navigation_dashboard, fragment);
                fragmentTransaction.commit();
            }
        });
        return root;
    }


}
