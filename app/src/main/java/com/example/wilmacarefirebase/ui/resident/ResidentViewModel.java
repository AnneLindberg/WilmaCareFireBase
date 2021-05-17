package com.example.wilmacarefirebase.ui.resident;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wilmacarefirebase.data.WilmaRepository;
import com.example.wilmacarefirebase.models.Resident;

import java.util.List;

public class ResidentViewModel extends ViewModel implements WilmaRepository.OnFireStoreTaskCompleteResident {

    private WilmaRepository repository = new WilmaRepository(this);
    private MutableLiveData<List<Resident>> residentData = new MutableLiveData<>();

    public ResidentViewModel() {
        repository.getResidentData();
    }

    public LiveData<List<Resident>> getResidentLiveData(){
        return residentData;
    }
    @Override
    public void residentDataAdded(List<Resident> residentList) {
        residentData.setValue(residentList);
    }

    @Override
    public void onError(Exception e) {

    }
}