package com.thebook.bottomnav.ui.info

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * InfoViewModel - ViewModel for the Info screen
 * 
 * Converted to Kotlin from Java. This ViewModel manages data for the InfoFragment
 * which displays detailed information about selected movies.
 */
class InfoViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        private const val PREFS_NAME = "movie"
    }

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text

    init {
        val prefs = getApplication<Application>().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        
        // Store description in SharedPreferences
        prefs.edit().apply {
            putString("description", "This is an info fragment")
            apply()
        }

        // Retrieve and set the description
        val description = prefs.getString("description", "")
        _text.value = description
    }
}
