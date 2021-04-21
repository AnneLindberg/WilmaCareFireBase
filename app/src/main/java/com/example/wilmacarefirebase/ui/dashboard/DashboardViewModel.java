package com.example.wilmacarefirebase.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Dette er opslagstavlen. Her kan både familien og primære kontaktperson poste");
    }

    public LiveData<String> getText() {
        return mText;
    }
}