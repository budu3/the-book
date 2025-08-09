package com.thebook.bottomnav.ui.home

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * ========================================
 * MOVIE DATA WORKER - BACKGROUND TASK
 * ========================================
 * 
 * PURPOSE:
 * This class runs in the background to fetch movie data from a remote server.
 * It's part of Android's WorkManager system for reliable background work.
 * 
 * WHEN IT RUNS:
 * - When the app needs fresh movie data
 * - Periodically to sync with server
 * - When user pulls to refresh
 * - Even if app is closed (WorkManager handles this)
 * 
 * WHAT IT DOES:
 * 1. Connects to remote movie database (JSON API)
 * 2. Downloads movie information (titles, posters, details)
 * 3. Returns data to the app for display
 * 4. Handles network errors gracefully
 * 
 * WORKMANAGER BENEFITS:
 * - Runs even if app is killed
 * - Respects battery optimization
 * - Retries on failure
 * - Works across Android versions
 * 
 * INTEGRATION:
 * HomeViewModel can use this to get real movie data instead of hardcoded samples
 */
class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    companion object {
        // Remote movie database URL
        private const val MOVIE_API_URL = "https://raw.githubusercontent.com/budu3/the-book/master/code/myflix/movies.json"
        private const val TAG = "MovieDataWorker"
        
        // Output data keys
        const val OUTPUT_MOVIE_DATA = "RemoteData"
    }

    /**
     * MAIN WORK FUNCTION
     * This is where the background work happens
     * Called by Android's WorkManager system
     */
    override fun doWork(): Result {
        Log.d(TAG, "Starting background movie data fetch")
        
        return try {
            // Fetch movie data from remote server
            val movieData = fetchMovieDataFromServer()
            
            if (movieData != null) {
                // Success: Return data to whoever requested the work
                val outputData = Data.Builder()
                    .putString(OUTPUT_MOVIE_DATA, movieData)
                    .build()
                    
                Log.d(TAG, "Movie data fetch successful")
                Result.success(outputData)
            } else {
                // Failed: Tell WorkManager to retry later
                Log.w(TAG, "Movie data fetch failed - will retry")
                Result.retry()
            }
        } catch (e: Exception) {
            // Error: Don't retry, something is fundamentally wrong
            Log.e(TAG, "Movie data fetch error", e)
            Result.failure()
        }
    }

    /**
     * FETCH MOVIE DATA FROM SERVER
     * Downloads JSON movie data from remote API
     * 
     * @return JSON string with movie data, or null if failed
     */
    private fun fetchMovieDataFromServer(): String? {
        Log.d(TAG, "Connecting to movie API: $MOVIE_API_URL")
        
        return try {
            // 1. Open connection to movie API
            val url = URL(MOVIE_API_URL)
            val connection = url.openConnection() as HttpURLConnection
            
            // 2. Configure connection
            connection.apply {
                connectTimeout = 10000 // 10 seconds
                readTimeout = 30000    // 30 seconds
                requestMethod = "GET"
            }
            
            Log.d(TAG, "Connection established, downloading data...")
            
            // 3. Read response data
            val reader = BufferedReader(InputStreamReader(connection.inputStream))
            val movieData = reader.use { it.readText() }
            
            // 4. Validate and return data
            if (movieData.isNotEmpty()) {
                Log.d(TAG, "Downloaded ${movieData.length} characters of movie data")
                movieData
            } else {
                Log.w(TAG, "Received empty movie data")
                null
            }
            
        } catch (e: IOException) {
            Log.e(TAG, "Network error while fetching movie data", e)
            null
        } catch (e: Exception) {
            Log.e(TAG, "Unexpected error while fetching movie data", e)
            null
        }
    }
}
