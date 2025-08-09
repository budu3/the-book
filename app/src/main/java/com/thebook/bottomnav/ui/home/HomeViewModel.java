package com.thebook.bottomnav.ui.home;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * HomeViewModel - ViewModel for the Home fragment
 * 
 * This class extends AndroidViewModel to handle data operations for the Home screen.
 * It reads movie data from SharedPreferences, parses the JSON, and converts it into
 * a list of SimpleViewModel objects that can be observed by the UI.
 * 
 * The ViewModel pattern ensures data survives configuration changes (like screen rotation)
 * and provides a clean separation between UI and data logic.
 */
public class HomeViewModel extends AndroidViewModel {
    // LiveData for holding text content (currently unused but kept for potential future use)
    private MutableLiveData<String> mText;
    
    // LiveData for holding the list of movies - observed by HomeFragment
    private MutableLiveData<ArrayList<SimpleViewModel>> movieLiveData;
    
    // Internal list to hold movie data
    private ArrayList<SimpleViewModel> movieList;
    
    // SharedPreferences key for retrieving movie data
    private static final String PREFS_NAME = "movie";

    /**
     * Constructor - Initializes the ViewModel and loads movie data
     * @param application Application context needed for accessing SharedPreferences and resources
     */
    public HomeViewModel(Application application) {
        super(application);
        
        // Initialize LiveData objects
        movieLiveData = new MutableLiveData<>();
        movieList = new ArrayList<>();
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");

        // Retrieve movie data from SharedPreferences (saved by MainActivity)
        SharedPreferences prefs = getApplication()
          .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String movieStr = prefs.getString("movie1","");

        // Parse the JSON string and convert to SimpleViewModel objects
        try {
            JSONArray jsonArray = new JSONArray(movieStr);
            int len = jsonArray.length();

            // Process each movie object in the JSON array
            for (int i=0; i<len; i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                // Extract movie data from JSON
                String poster = jsonObject.getString("poster");
                String title = jsonObject.getString("movie_title");
                
                // Convert poster resource name to drawable resource ID
                int imgID = getApplication().getResources()
                  .getIdentifier(
                    poster,                           // Resource name (e.g., "tt37")
                    "drawable",                       // Resource type
                    getApplication().getPackageName()); // Package name
                
                // Create SimpleViewModel object and populate with data
                SimpleViewModel svm = new SimpleViewModel();
                svm.setTitle(title);
                svm.setPoster(poster);
                svm.setImage(imgID);
                
                // Add to the movie list
                movieList.add(svm);
            }
        } catch (JSONException e) {
            // Handle JSON parsing errors
            e.printStackTrace();
        }

        // Update LiveData objects to notify observers (HomeFragment)
        mText.setValue(movieStr);
        movieLiveData.setValue(movieList);
    }
    /**
     * Provides access to the movie list LiveData for UI components to observe
     * @return LiveData containing the list of movies for display
     */
    public LiveData<ArrayList<SimpleViewModel>> getArrayList() {
        return movieLiveData;
    }
}