package com.thebook.bottomnav.ui.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.thebook.bottomnav.R

/**
 * ========================================
 * MOVIE POSTER ADAPTER
 * ========================================
 * 
 * PURPOSE:
 * This class manages the display of movie posters in horizontal scrolling lists.
 * It's the "middleman" between the data (movie URLs) and the UI (RecyclerView).
 * 
 * HOW RECYCLERVIEW WORKS:
 * 1. RecyclerView asks: "How many items do you have?" → getItemCount()
 * 2. RecyclerView says: "Create view for position 0" → onCreateViewHolder()
 * 3. RecyclerView says: "Fill view 0 with data" → onBindViewHolder()
 * 4. User scrolls, RecyclerView reuses views for efficiency
 * 
 * WHAT THIS ADAPTER DOES:
 * - Takes a list of image URLs
 * - Creates movie poster cards for each URL
 * - Uses Glide library to download and display images
 * - Handles user clicks on movie posters
 * 
 * IMAGE LOADING:
 * Uses Glide library which automatically:
 * - Downloads images from internet
 * - Caches images for faster loading
 * - Handles loading states and errors
 * - Resizes images efficiently
 */
class RecyclerViewAdapter(
    private val context: Context,
    private val movieUrls: List<String>
) : RecyclerView.Adapter<RecyclerViewAdapter.MovieViewHolder>() {

    // =================================
    // PROPERTIES
    // =================================
    
    /**
     * CLICK LISTENER
     * Fragment implements this interface to handle movie poster clicks
     */
    private var clickListener: ItemClickListener? = null

    // =================================
    // RECYCLERVIEW REQUIRED METHODS
    // =================================
    
    /**
     * CREATE VIEW HOLDER
     * Called when RecyclerView needs a new view (first time or after scrolling)
     * This is where we inflate the movie poster layout
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        Log.d(TAG, "Creating new ViewHolder")
        
        // Inflate the movie poster layout from XML
        val view = LayoutInflater.from(context)
            .inflate(R.layout.recyclerview_item, parent, false)
            
        return MovieViewHolder(view)
    }

    /**
     * BIND VIEW HOLDER
     * Called when RecyclerView wants to display data in a view
     * This is where we load the actual movie poster image
     */
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val imageUrl = movieUrls[position]
        Log.d(TAG, "Loading image at position $position: $imageUrl")
        
        // Use Glide to load image from URL into ImageView
        Glide.with(context)
            .load(imageUrl)
            .placeholder(R.drawable.ic_home_black_24dp)  // Show while loading
            .error(R.drawable.ic_home_black_24dp)        // Show if load fails
            .transition(DrawableTransitionOptions.withCrossFade()) // Smooth fade-in
            .into(holder.posterImageView)
    }

    /**
     * GET ITEM COUNT
     * Tells RecyclerView how many items we have
     */
    override fun getItemCount(): Int = movieUrls.size

    // =================================
    // PUBLIC API
    // =================================
    
    /**
     * SET CLICK LISTENER
     * Allows external components (like HomeFragment) to handle movie clicks
     */
    fun setClickListener(itemClickListener: ItemClickListener) {
        clickListener = itemClickListener
        Log.d(TAG, "Click listener set")
    }

    // =================================
    // VIEW HOLDER CLASS
    // =================================
    
    /**
     * MOVIE VIEW HOLDER
     * Holds references to views for each movie poster item
     * Implements click handling for the entire movie card
     */
    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        
        /**
         * UI COMPONENTS
         * Direct references to views in the movie poster layout
         */
        val posterImageView: ImageView = itemView.findViewById(R.id.imageView)

        init {
            // Set this ViewHolder to handle clicks on the entire movie card
            itemView.setOnClickListener(this)
            Log.d(TAG, "ViewHolder initialized for position ${adapterPosition}")
        }

        /**
         * HANDLE CLICKS
         * Called when user taps on this movie poster
         */
        override fun onClick(view: View) {
            val position = adapterPosition
            Log.d(TAG, "Movie poster clicked at position: $position")
            
            // Make sure the position is valid and we have a click listener
            if (position != RecyclerView.NO_POSITION && clickListener != null) {
                clickListener?.onItemClick(view, position)
            }
        }
    }

    // =================================
    // INTERFACES
    // =================================
    
    /**
     * ITEM CLICK LISTENER INTERFACE
     * Contract that external components must implement to handle movie clicks
     */
    interface ItemClickListener {
        /**
         * Called when a movie poster is clicked
         * @param view The clicked view
         * @param position The position of the clicked movie in the list
         */
        fun onItemClick(view: View, position: Int)
    }

    companion object {
        private const val TAG = "MovieAdapter"
    }
}
