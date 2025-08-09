package com.thebook.bottomnav.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.thebook.bottomnav.R

/**
 * ========================================
 * DASHBOARD FRAGMENT - STATS SCREEN
 * ========================================
 * 
 * PURPOSE:
 * This is a placeholder screen for showing app statistics, user analytics,
 * or administrative features. Currently shows simple text.
 * 
 * POTENTIAL FEATURES (future):
 * - User viewing statistics
 * - Popular movies chart
 * - App usage analytics
 * - User preferences
 * - Account settings
 * 
 * CURRENT STATE:
 * Simple text display using MVVM pattern as demonstration
 */
class DashboardFragment : Fragment() {

    /**
     * VIEW MODEL
     * Manages data for this screen - currently just static text
     */
    private val viewModel: DashboardViewModel by viewModels()

    /**
     * CREATE VIEW
     * Sets up the dashboard screen layout and connects to ViewModel
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Load the dashboard layout
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        
        // Find the text display component
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        
        // Connect ViewModel data to UI (reactive programming)
        viewModel.text.observe(viewLifecycleOwner) { text ->
            textView.text = text
        }
        
        return root
    }
}
