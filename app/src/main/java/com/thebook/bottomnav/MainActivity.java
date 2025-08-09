package com.thebook.bottomnav;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.thebook.bottomnav.data.MovieData;

import org.json.JSONException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

/**
 * MainActivity - The main entry point of the app
 * 
 * This activity sets up the bottom navigation UI and initializes the app's movie data.
 * It uses Android's Navigation Component to manage fragment transitions between
 * Home, Dashboard, and Notifications screens.
 */
public class MainActivity extends AppCompatActivity {
    // SharedPreferences key for storing movie data
    private static final String PREFS_NAME = "movie";

    /**
     * Called when the activity is first created.
     * Sets up the bottom navigation, configures the Navigation Component,
     * and initializes movie data in SharedPreferences.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Get reference to the bottom navigation view
        BottomNavigationView navView = findViewById(R.id.bottom_navigation);

        // Configure which destinations are considered top-level (no back arrow)
        AppBarConfiguration appBarConfiguration =
          new AppBarConfiguration.Builder(
            R.id.navigation_home,
            R.id.navigation_dashboard, 
            R.id.navigation_notifications)
                .build();
        
        // Get the NavController that manages fragment navigation
        NavController navController = Navigation
          .findNavController(this, R.id.nav_host_fragment);
        
        // Connect the action bar to the navigation controller
        NavigationUI
          .setupActionBarWithNavController(
            this,
            navController,
            appBarConfiguration);
        
        // Connect the bottom navigation to the navigation controller
        NavigationUI
          .setupWithNavController(navView, navController);

        // Initialize movie data in SharedPreferences for the app to use
        try {
            saveToSharedPreferences(this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called when the user presses the back button in the action bar.
     * Delegates navigation to the NavController.
     */
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController =
          Navigation.findNavController(this, R.id.nav_host_fragment);
        return navController.navigateUp();
    }

    /**
     * Saves movie data to SharedPreferences for persistence across app sessions.
     * This data will be read by HomeViewModel to populate the movie lists.
     * 
     * @param context The application context
     * @throws JSONException if there's an error processing the JSON data
     */
    private void saveToSharedPreferences(Context context) throws JSONException {
        // Get SharedPreferences instance for storing movie data
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor preferencesEditor = prefs.edit();
        
        // Store the movie JSON array from MovieData class
        preferencesEditor.putString("movie1", MovieData.MOVIE_JSON_ARRAY);
        
        // Apply changes asynchronously
        preferencesEditor.apply();
    }

}
