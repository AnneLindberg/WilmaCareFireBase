package com.example.wilmacarefirebase.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NotificationsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Her kommer kalenderen til at være. Her kan i lave nye aftaler, eller redigerer i gamle aftaler for den pågældende borger. Derudover kan man slette gamle aftaler");
    }

    public LiveData<String> getText() {
        return mText;
    }
}