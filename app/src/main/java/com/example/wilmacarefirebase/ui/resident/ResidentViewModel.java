package com.example.wilmacarefirebase.ui.resident;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wilmacarefirebase.data.WilmaRepository;
import com.example.wilmacarefirebase.models.HealthCareWorker;

import java.util.List;

public class ResidentViewModel extends ViewModel implements WilmaRepository.OnFireStoreTaskCompleteHealthCareWorker {

    private WilmaRepository repository = new WilmaRepository(this);
    private MutableLiveData<List<HealthCareWorker>> liveData = new MutableLiveData<>();

    public ResidentViewModel() {
        repository.getResidentData();
    }

    public void init(){
        repository.getHealthCareWorkerData();
    }
    public LiveData<List<HealthCareWorker>> getWorkerList(){
        return liveData;
    }


    @Override
    public void healthcareDataAdded(List<HealthCareWorker> careWorkerList) {
        liveData.setValue(careWorkerList);
    }

    @Override
    public void onError(Exception e) {

    }
}