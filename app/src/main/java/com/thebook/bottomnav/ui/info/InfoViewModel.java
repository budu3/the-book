package com.thebook.bottomnav.ui.info;

import android.content.Context;
import android.content.SharedPreferences;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * InfoViewModel - ViewModel for the Info screen
 * 
 * This ViewModel manages data for the InfoFragment, which displays detailed
 * information about selected movies. Currently it provides basic text content
 * stored in SharedPreferences, but could be extended to handle specific
 * movie detail data.
 */
public class InfoViewModel extends AndroidViewModel {
    // LiveData for holding text content to display
    private MutableLiveData<String> mText;
    
    // SharedPreferences key (currently unused but kept for potential future use)
    private static String MY_PREFS_NAME = "filename";

    /**
     * Constructor - Initializes the ViewModel and sets up text content
     * @param application Application context for accessing SharedPreferences
     */
    public InfoViewModel(@NonNull Application application) {
        super(application);
        
        // Access SharedPreferences for storing/retrieving data
        SharedPreferences prefs = getApplication()
          .getSharedPreferences("movie", Context.MODE_PRIVATE);

        // Initialize LiveData
        mText = new MutableLiveData<>();

        // Store a description in SharedPreferences
        // (This demonstrates SharedPreferences usage but could be improved)
        SharedPreferences.Editor preferencesEditor = prefs.edit();
        preferencesEditor
          .putString("description", "This is a dash board fragment");
        preferencesEditor.apply();

        // Retrieve the description and set it as the text value
        String description = prefs.getString("description", "");
        mText.setValue(description);
    }

    /**
     * Provides access to the text LiveData for UI components to observe
     * @return LiveData containing text content for display
     */
    public LiveData<String> getText() {
        return mText;
    }
}
