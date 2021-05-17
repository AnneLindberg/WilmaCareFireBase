package com.example.wilmacarefirebase.ui.aboutResident;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wilmacarefirebase.data.WilmaRepository;
import com.example.wilmacarefirebase.models.DashboardPost;
import com.example.wilmacarefirebase.models.HealthCareWorker;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AboutResidentViewModel extends ViewModel implements WilmaRepository.OnFireStoreTaskCompleteHealthCareWorker {

    private WilmaRepository repository = new WilmaRepository(this);
    private MutableLiveData<List<HealthCareWorker>> careWorkerData = new MutableLiveData<>();

    public AboutResidentViewModel() {

        repository.getHealthCareWorkerData();
    }


    public LiveData<List<HealthCareWorker>> getPostWorkerData() {
        return careWorkerData;
    }

    @Override
    public void healthcareDataAdded(List<HealthCareWorker> careWorkerList) {

    }

    @Override
    public void onError(Exception e) {

    }
}
