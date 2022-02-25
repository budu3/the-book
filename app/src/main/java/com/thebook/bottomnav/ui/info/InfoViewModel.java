package com.thebook.bottomnav.ui.info;

import android.content.Context;
import android.content.SharedPreferences;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class InfoViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<String> mText;
    private static String MY_PREFS_NAME = "filename";

    public InfoViewModel(@NonNull Application application) {
        super(application);
        SharedPreferences prefs = getApplication().getSharedPreferences("movie", Context.MODE_PRIVATE);

        mText = new MutableLiveData<>();

        SharedPreferences.Editor preferencesEditor = prefs.edit();
        preferencesEditor.putString("description", "This is a dash board fragment");
        //preferencesEditor.putInt("color", mCurrentColor);
        preferencesEditor.apply();

        String description = prefs.getString("description", "");
        mText.setValue(description);
    }

    public LiveData<String> getText() {
        return mText;
    }
}
