package com.example.wilmacarefirebase.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.wilmacarefirebase.data.WilmaRepository;
import com.example.wilmacarefirebase.models.DashboardPost;

public class DashboardViewModel extends ViewModel {


    private WilmaRepository repository;

    public DashboardViewModel() {
        this.repository = WilmaRepository.getInstance();
    }

    public LiveData<DashboardPost> getPost(){
        return repository.getPost();
    }

    public void updatePosts(DashboardPost dashboardPost){
        repository.updatePosts(dashboardPost);
    }

    public void insert(DashboardPost feedPost){
        repository.addPost(feedPost);
    }

    public void delete(DashboardPost feedPost){
        repository.delete(feedPost);
    }

    public void deleteAllFeedPost(){
        repository.deleteAllPost();
    }


}