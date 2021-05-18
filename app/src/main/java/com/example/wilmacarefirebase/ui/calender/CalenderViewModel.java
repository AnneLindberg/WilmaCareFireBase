package com.example.wilmacarefirebase.ui.calender;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wilmacarefirebase.data.WilmaRepository;
import com.example.wilmacarefirebase.models.CalenderPost;

import java.util.List;

public class CalenderViewModel extends ViewModel implements WilmaRepository.OnFireStoreTaskCompleteCalender {

    private WilmaRepository repository = new WilmaRepository(this);
    private MutableLiveData<List<CalenderPost>> calenderLiveData = new MutableLiveData<>();

    public CalenderViewModel() {
       repository.getCalenderData();
    }

    public LiveData<List<CalenderPost>> getCalenderPostLiveData(){
        return calenderLiveData;
    }

    @Override
    public void calenderDataAdded(List<CalenderPost> calenderPostList) {
        calenderLiveData.setValue(calenderPostList);
    }

    @Override
    public void onError(Exception e) {

    }
}