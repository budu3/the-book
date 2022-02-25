package com.thebook.bottomnav.ui.home;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Map;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

//You need to extend AndroidViewModel
public class HomeViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;
    private static final String PREFS_NAME = "movie";

    public HomeViewModel(Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");

        SharedPreferences prefs = getApplication().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String movieStr = prefs.getString("movie1","");

        mText.setValue(movieStr);

    }

    public LiveData<String> getText() {
        return mText;
    }
}