package com.thebook.bottomnav.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.thebook.bottomnav.R

/**
 * NotificationsFragment - Simple notifications screen
 * 
 * Converted to Kotlin from Java. This fragment represents the Notifications
 * tab in the bottom navigation with basic text content.
 */
class NotificationsFragment : Fragment() {

    private val notificationsViewModel: NotificationsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        
        val textView: TextView = root.findViewById(R.id.text_notifications)
        
        notificationsViewModel.text.observe(viewLifecycleOwner) { text ->
            textView.text = text
        }
        
        return root
    }
}
