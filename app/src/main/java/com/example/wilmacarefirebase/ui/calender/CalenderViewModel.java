package com.example.wilmacarefirebase.ui.calender;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalenderViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CalenderViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Her kommer kalenderen til at være. Her kan i lave nye aftaler, eller redigerer i gamle aftaler for den pågældende borger. Derudover kan man slette gamle aftaler");
    }

    public LiveData<String> getText() {
        return mText;
    }
}