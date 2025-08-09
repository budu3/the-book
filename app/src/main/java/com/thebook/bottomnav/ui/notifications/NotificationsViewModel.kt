package com.thebook.bottomnav.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * NotificationsViewModel - ViewModel for the Notifications screen
 * 
 * Converted to Kotlin from Java. Simple ViewModel that provides static
 * text content for the Notifications fragment following the MVVM pattern.
 */
class NotificationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications fragment"
    }
    val text: LiveData<String> = _text
}
