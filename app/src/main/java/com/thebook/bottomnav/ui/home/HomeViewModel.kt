package com.thebook.bottomnav.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * ========================================
 * HOME SCREEN VIEW MODEL
 * ========================================
 * 
 * PURPOSE:
 * This is the "brain" behind the Home screen. It manages all the movie data
 * and provides it to the UI in a lifecycle-aware way.
 * 
 * WHAT IT DOES:
 * 1. Loads movie data (currently from hardcoded list, but could be from network/database)
 * 2. Exposes movie list to UI via LiveData (auto-updates UI when data changes)
 * 3. Survives configuration changes (screen rotation, etc.)
 * 4. Provides different views of the same data (all movies, URLs only, etc.)
 * 
 * ARCHITECTURE PATTERN:
 * - MVVM (Model-View-ViewModel) pattern
 * - LiveData for reactive programming
 * - Repository pattern ready (data loading separated from UI logic)
 * 
 * DATA FLOW:
 * MovieData → HomeViewModel → HomeFragment → RecyclerView → User sees movies
 */
class HomeViewModel(application: Application) : AndroidViewModel(application) {

    // =================================
    // DATA STORAGE
    // =================================
    
    /**
     * LIVE DATA - Observable movie list
     * When this changes, the UI automatically updates
     * This is the "single source of truth" for movie data
     */
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies
    
    /**
     * INTERNAL STORAGE
     * Working list that we can modify, then push to LiveData
     */
    private val movieCollection = mutableListOf<Movie>()

    // =================================
    // INITIALIZATION
    // =================================
    
    init {
        // Load data as soon as ViewModel is created
        loadMovieData()
        Log.d(TAG, "HomeViewModel initialized")
    }

    // =================================
    // DATA LOADING
    // =================================
    
    /**
     * LOAD MOVIE DATA
     * Currently loads hardcoded sample data, but this is where you'd
     * connect to a real data source (API, database, etc.)
     */
    private fun loadMovieData() {
        Log.d(TAG, "Loading movie data...")
        
        // SAMPLE DATA - Replace with real data source
        val movieData = createSampleMovies()
        
        // Add to internal collection
        movieCollection.clear()
        movieCollection.addAll(movieData)
        
        // Notify UI of changes via LiveData
        _movies.value = movieCollection.toList()
        
        Log.d(TAG, "Loaded ${movieCollection.size} movies")
    }
    
    /**
     * CREATE SAMPLE MOVIE DATA
     * This would be replaced by real API calls or database queries
     */
    private fun createSampleMovies(): List<Movie> {
        // Using real image URLs from the project's GitHub repository
        val baseUrl = "https://raw.githubusercontent.com/budu3/the-book/master/code/assets/images/"
        
        return listOf(
            Movie("A Ghost Story", "${baseUrl}a-ghost-story.jpg"),
            Movie("Alien Covenant", "${baseUrl}alien-covenant.jpg"),
            Movie("Pirates of the Caribbean", "${baseUrl}pirates-of-the-caribbean.jpg"),
            Movie("Sleepless", "${baseUrl}sleepless.jpg"),
            Movie("The Dark Tower", "${baseUrl}dark-tower.jpg")
        )
    }

    // =================================
    // PUBLIC API
    // =================================
    
    /**
     * GET MOVIE URLS
     * Convenience method for RecyclerView adapter that only needs image URLs
     * 
     * @return List of poster URLs for Glide image loading
     */
    fun getMovieUrls(): List<String> {
        return movieCollection.map { it.posterUrl }
    }
    
    /**
     * GET MOVIE BY POSITION
     * Safely get a movie at a specific position (for click handling)
     * 
     * @param position Index in the movie list
     * @return Movie at that position, or null if invalid position
     */
    fun getMovieAt(position: Int): Movie? {
        return movieCollection.getOrNull(position)
    }
    
    /**
     * REFRESH DATA
     * Public method to reload movie data (for pull-to-refresh, etc.)
     */
    fun refreshMovies() {
        Log.d(TAG, "Refreshing movie data...")
        loadMovieData()
    }

    // =================================
    // LEGACY COMPATIBILITY
    // =================================
    
    /**
     * LEGACY METHOD - for backwards compatibility
     * TODO: Update HomeFragment to use 'movies' LiveData instead
     */
    fun getArrayList(): LiveData<List<Movie>> = movies

    companion object {
        private const val TAG = "HomeViewModel"
    }
}
