package com.example.wilmacarefirebase.ui.dashboard;

import androidx.lifecycle.ViewModel;

import com.example.wilmacarefirebase.data.WilmaRepository;
import com.example.wilmacarefirebase.models.DashboardPost;

public class AddPostViewModel extends ViewModel {

    private WilmaRepository repository;

    public AddPostViewModel() {
        repository = new WilmaRepository();
    }


    public void addPost(DashboardPost post){
        repository.addPost();
    }
}
