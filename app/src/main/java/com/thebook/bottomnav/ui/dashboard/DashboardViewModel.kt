package com.thebook.bottomnav.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * ========================================
 * DASHBOARD VIEW MODEL
 * ========================================
 * 
 * PURPOSE:
 * Manages data for the Dashboard screen. Currently provides static text,
 * but could be extended to provide real dashboard analytics.
 * 
 * POTENTIAL DATA (future):
 * - User statistics (movies watched, favorites count)
 * - Popular movies trending data  
 * - User preferences and settings
 * - App usage metrics
 * 
 * MVVM PATTERN:
 * This ViewModel survives configuration changes and provides
 * data to the UI via LiveData (reactive programming)
 */
class DashboardViewModel : ViewModel() {

    /**
     * DASHBOARD TEXT
     * Currently static, but could be dynamic based on user data
     */
    private val _text = MutableLiveData<String>().apply {
        value = "🎬 Dashboard\n\nThis screen would show:\n• Movie statistics\n• User analytics\n• App preferences"
    }
    val text: LiveData<String> = _text
    
    /**
     * Future methods could include:
     * - fun loadUserStats()
     * - fun updatePreferences()
     * - fun getPopularMovies()
     */
}
