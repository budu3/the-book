package com.thebook.bottomnav.ui.home

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

/**
 * ========================================
 * IMAGE CACHE WORKER - BACKGROUND TASK
 * ========================================
 * 
 * PURPOSE:
 * This class downloads movie poster images in the background and saves them
 * to device storage for faster loading and offline viewing.
 * 
 * WHY WE NEED THIS:
 * - Movie posters load faster from local cache than internet
 * - Users can see movies even when offline
 * - Reduces data usage (download once, view many times)
 * - Better user experience with instant image loading
 * 
 * HOW IT WORKS:
 * 1. Downloads images from remote URLs
 * 2. Converts to Android Bitmap format
 * 3. Saves compressed JPEG to app cache directory
 * 4. Glide automatically uses cached versions
 * 
 * WHEN IT RUNS:
 * - After downloading movie data
 * - Periodically to refresh image cache
 * - When user adds new movies to favorites
 * - During app idle time (battery-friendly)
 * 
 * CACHE MANAGEMENT:
 * - Images stored in app cache directory
 * - Android automatically cleans old cache when storage is low
 * - Compressed to 80% quality to save space
 */
class MyImageWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    companion object {
        // Base URL for movie poster images
        private const val POSTER_BASE_URL = "https://raw.githubusercontent.com/budu3/the-book/master/code/assets/images/"
        private const val TAG = "ImageCacheWorker"
        
        // Standard movie poster files to cache
        private val POSTER_FILES = listOf(
            "a-ghost-story.jpg",
            "alien-covenant.jpg", 
            "pirates-of-the-caribbean.jpg",
            "sleepless.jpg",
            "dark-tower.jpg"
        )
        
        // Image compression quality (80% = good quality, smaller file size)
        private const val JPEG_QUALITY = 80
    }

    /**
     * MAIN WORK FUNCTION
     * Downloads all movie poster images to local cache
     */
    override fun doWork(): Result {
        Log.d(TAG, "Starting background image cache task")
        
        return try {
            val successCount = downloadAndCacheImages()
            
            if (successCount > 0) {
                Log.d(TAG, "Successfully cached $successCount/${POSTER_FILES.size} images")
                Result.success()
            } else {
                Log.w(TAG, "Failed to cache any images - will retry")
                Result.retry()
            }
            
        } catch (e: Exception) {
            Log.e(TAG, "Image caching failed", e)
            Result.failure()
        }
    }

    /**
     * DOWNLOAD AND CACHE ALL IMAGES
     * Processes each poster file and tracks success rate
     * 
     * @return Number of successfully cached images
     */
    private fun downloadAndCacheImages(): Int {
        var successCount = 0
        
        POSTER_FILES.forEach { filename ->
            if (downloadSingleImage(filename)) {
                successCount++
            }
        }
        
        return successCount
    }

    /**
     * DOWNLOAD SINGLE IMAGE
     * Downloads one poster image and saves to cache
     * 
     * @param filename Name of the image file to download
     * @return true if successful, false if failed
     */
    private fun downloadSingleImage(filename: String): Boolean {
        Log.d(TAG, "Caching image: $filename")
        
        return try {
            // 1. Check if already cached
            val cacheFile = File(applicationContext.cacheDir, filename)
            if (cacheFile.exists()) {
                Log.d(TAG, "Image already cached: $filename")
                return true
            }
            
            // 2. Download from remote URL
            val imageUrl = "$POSTER_BASE_URL$filename"
            val url = URL(imageUrl)
            val connection = url.openConnection() as HttpURLConnection
            
            // 3. Configure connection
            connection.apply {
                doInput = true
                connectTimeout = 10000 // 10 seconds
                readTimeout = 30000    // 30 seconds
            }
            connection.connect()
            
            // 4. Download and decode image
            val inputStream = connection.inputStream
            val bufferedStream = BufferedInputStream(inputStream)
            val bitmap = BitmapFactory.decodeStream(bufferedStream)
            inputStream.close()
            
            // 5. Save to cache directory
            if (bitmap != null) {
                FileOutputStream(cacheFile).use { outputStream ->
                    bitmap.compress(Bitmap.CompressFormat.JPEG, JPEG_QUALITY, outputStream)
                }
                
                Log.d(TAG, "Successfully cached: $filename (${cacheFile.length()} bytes)")
                true
            } else {
                Log.w(TAG, "Failed to decode image: $filename")
                false
            }
            
        } catch (e: IOException) {
            Log.e(TAG, "Network error downloading $filename", e)
            false
        } catch (e: Exception) {
            Log.e(TAG, "Unexpected error caching $filename", e)
            false
        }
    }
}
