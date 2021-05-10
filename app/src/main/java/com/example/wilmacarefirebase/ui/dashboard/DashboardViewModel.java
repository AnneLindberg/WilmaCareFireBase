package com.example.wilmacarefirebase.ui.dashboard;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wilmacarefirebase.data.WilmaRepository;
import com.example.wilmacarefirebase.models.DashboardPost;

import java.util.ArrayList;
import java.util.List;

public class DashboardViewModel extends ViewModel {


    private MutableLiveData<ArrayList<DashboardPost>> dashPostList;
    private WilmaRepository repository = new WilmaRepository();


    public void init(DashboardFragment context){
        if(dashPostList == null) {
            return;
        }
        dashPostList = WilmaRepository.getInstance(context).getPost();
    }

    public LiveData<ArrayList<DashboardPost>> getPost(){
        return repository.getPost();
    }

    public void updatePosts(DashboardPost dashboardPost){
        repository.updatePosts(dashboardPost);
    }

    public void insert(DashboardPost feedPost){
        repository.insert(feedPost);
    }


    public void update(DashboardPost feedPost){
        repository.update(feedPost);
    }


    public void delete(DashboardPost feedPost){
        repository.delete(feedPost);
    }

    public void deleteAllFeedPost(){
        repository.deleteAllPost();
    }


}