package com.thebook.bottomnav

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * ========================================
 * MAIN ACTIVITY - APP ENTRY POINT
 * ========================================
 * 
 * PURPOSE:
 * This is the single activity that hosts all fragments in our movie app.
 * It uses Android's Navigation Component to manage navigation between different screens.
 * 
 * WHAT IT DOES:
 * 1. Sets up bottom navigation with 3 tabs: Home, Dashboard, Notifications
 * 2. Configures the app bar (top toolbar) to work with navigation
 * 3. Handles back button navigation between screens
 * 
 * ARCHITECTURE PATTERN:
 * - Single Activity Architecture (recommended by Google)
 * - Uses Navigation Component for fragment management
 * - Material Design bottom navigation pattern
 * 
 * USER FLOW:
 * App launches → MainActivity → Shows Home screen with movie lists
 */
class MainActivity : AppCompatActivity() {

    /**
     * Called when the app is first created
     * This is where we set up all the navigation infrastructure
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Load the main layout (contains bottom nav + fragment container)
        setContentView(R.layout.activity_main)
        
        setupNavigation()
    }

    /**
     * NAVIGATION SETUP
     * Sets up all the plumbing needed for bottom navigation to work
     */
    private fun setupNavigation() {
        // 1. Get the bottom navigation view from the layout
        val bottomNav: BottomNavigationView = findViewById(R.id.nav_view)
        
        // 2. Define which screens are "top-level" (no back arrow in toolbar)
        val topLevelDestinations = setOf(
            R.id.navigation_home,        // Movies home screen
            R.id.navigation_dashboard,   // Dashboard/stats screen  
            R.id.navigation_notifications // Notifications screen
        )
        
        // 3. Configure the app bar behavior
        val appBarConfig = AppBarConfiguration(topLevelDestinations)
        
        // 4. Get the navigation controller (manages fragment switching)
        val navController = findNavController(R.id.nav_host_fragment)
        
        // 5. Connect everything together:
        setupActionBarWithNavController(navController, appBarConfig) // Links toolbar to navigation
        bottomNav.setupWithNavController(navController)              // Links bottom nav to navigation
    }

    /**
     * BACK BUTTON HANDLING
     * Called when user presses back button - delegates to navigation system
     */
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        // Try navigation back first, fallback to system back if needed
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
