package com.thebook.bottomnav.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * NotificationsViewModel - ViewModel for the Notifications screen
 * 
 * Simple ViewModel that provides static text content for the Notifications fragment.
 * This follows the MVVM pattern and could be extended to handle notification-related
 * data, such as a list of user notifications, settings, or alerts.
 */
public class NotificationsViewModel extends ViewModel {

    // LiveData for holding text content
    private MutableLiveData<String> mText;

    /**
     * Constructor - Initializes the ViewModel with default text
     */
    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    /**
     * Provides access to the text LiveData for UI components to observe
     * @return LiveData containing text content for display
     */
    public LiveData<String> getText() {
        return mText;
    }
}