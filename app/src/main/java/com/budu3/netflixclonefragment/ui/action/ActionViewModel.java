package com.budu3.netflixclonefragment.ui.action;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ActionViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<String> mText;

    public ActionViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is an action fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
