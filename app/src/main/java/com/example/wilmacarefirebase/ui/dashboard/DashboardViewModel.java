package com.example.wilmacarefirebase.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wilmacarefirebase.data.WilmaRepository;
import com.example.wilmacarefirebase.models.DashboardPost;

import java.util.List;

public class DashboardViewModel extends ViewModel implements WilmaRepository.OnFirestoreTaskCompleteDashPost {


    private WilmaRepository repository = new WilmaRepository(this);
    private MutableLiveData<List<DashboardPost>> postLiveData = new MutableLiveData<>();

    public DashboardViewModel() {
        repository.getPostData();
    }

    public LiveData<List<DashboardPost>> getPostLiveData() {
        return postLiveData;
    }


    @Override
    public void postDataAdded(List<DashboardPost> dashboardPostList) {
        postLiveData.setValue(dashboardPostList);
    }

    @Override
    public void onError(Exception e) {

    }

}


