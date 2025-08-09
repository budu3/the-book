package com.thebook.bottomnav.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * DashboardViewModel - ViewModel for the Dashboard screen
 * 
 * Simple ViewModel that provides static text content for the Dashboard fragment.
 * This follows the MVVM pattern and could be extended to provide more complex
 * dashboard-related data and functionality.
 */
public class DashboardViewModel extends ViewModel {

    // LiveData for holding text content
    private MutableLiveData<String> mText;

    /**
     * Constructor - Initializes the ViewModel with default text
     */
    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    /**
     * Provides access to the text LiveData for UI components to observe
     * @return LiveData containing text content for display
     */
    public LiveData<String> getText() {
        return mText;
    }
}