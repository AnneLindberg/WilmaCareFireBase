package com.example.wilmacarefirebase.ui.dashboard;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wilmacarefirebase.data.WilmaRepository;
import com.example.wilmacarefirebase.models.DashboardPost;

import java.util.ArrayList;

public class AddPostViewModel extends ViewModel {

    private WilmaRepository repository;

    public AddPostViewModel() {
        repository = WilmaRepository.getInstance();
    }

    public MutableLiveData<ArrayList<DashboardPost>> getPost(){
        return repository.getPost();
    }

    public void addPost(DashboardPost post){
        repository.addPost(post);
    }




}
