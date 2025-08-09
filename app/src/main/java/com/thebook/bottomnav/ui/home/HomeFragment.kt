package com.thebook.bottomnav.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thebook.bottomnav.R

/**
 * ========================================
 * HOME FRAGMENT - MAIN MOVIE SCREEN
 * ========================================
 * 
 * PURPOSE:
 * This is the main screen users see when they open the app. It displays
 * movie posters in three horizontal scrolling lists to mimic a Netflix-style interface.
 * 
 * WHAT IT SHOWS:
 * 1. Favorites list - movies marked as favorites
 * 2. Drama list - drama/thriller movies  
 * 3. Comedy list - comedy/action movies
 * 
 * HOW IT WORKS:
 * 1. Gets movie data from HomeViewModel
 * 2. Creates 3 RecyclerViews (horizontal scrolling lists)
 * 3. Uses same data for all lists (in real app, these would be filtered)
 * 4. Handles movie clicks to show details
 * 
 * USER INTERACTION:
 * - User swipes horizontally through movie posters
 * - Taps on a poster to see movie details
 * - Can navigate to other tabs via bottom navigation
 * 
 * ARCHITECTURE:
 * Fragment (UI) ← observes ← ViewModel (data) ← loads ← Repository (future)
 */
class HomeFragment : Fragment(), RecyclerViewAdapter.ItemClickListener {

    // =================================
    // COMPONENTS
    // =================================
    
    /**
     * VIEW MODEL - Manages movie data
     * Created automatically by Android, survives configuration changes
     */
    private val homeViewModel: HomeViewModel by viewModels()

    // =================================
    // LIFECYCLE
    // =================================
    
    /**
     * CREATE VIEW
     * Called when Android needs to display this screen
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "Creating HomeFragment view")
        
        // Inflate the layout (loads the XML into memory)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        
        // Set up all the movie lists
        setupMovieLists(root)
        
        return root
    }

    // =================================
    // UI SETUP
    // =================================
    
    /**
     * SETUP MOVIE LISTS
     * Creates and configures all three horizontal movie lists
     */
    private fun setupMovieLists(root: View) {
        Log.d(TAG, "Setting up movie lists")
        
        // Get movie URLs from ViewModel
        val movieUrls = homeViewModel.getMovieUrls()
        Log.d(TAG, "Got ${movieUrls.size} movie URLs")
        
        // Set up each movie category list
        setupSingleMovieList(root, R.id.fav_recyclerview, movieUrls, "Favorites")
        setupSingleMovieList(root, R.id.drama_recyclerview, movieUrls, "Drama")
        setupSingleMovieList(root, R.id.comedy_recyclerview, movieUrls, "Comedy")
    }
    
    /**
     * SETUP SINGLE MOVIE LIST
     * Helper method to reduce code duplication - sets up one RecyclerView
     * 
     * @param root The main view containing all RecyclerViews
     * @param recyclerViewId The ID of the RecyclerView to configure
     * @param movieUrls List of image URLs to display
     * @param categoryName Name for logging/debugging
     */
    private fun setupSingleMovieList(
        root: View, 
        recyclerViewId: Int, 
        movieUrls: List<String>, 
        categoryName: String
    ) {
        Log.d(TAG, "Setting up $categoryName list")
        
        // 1. Find the RecyclerView in the layout
        val recyclerView: RecyclerView = root.findViewById(recyclerViewId)
        
        // 2. Configure RecyclerView for horizontal scrolling
        recyclerView.apply {
            setHasFixedSize(true) // Optimization: all items are same size
            layoutManager = LinearLayoutManager(
                requireContext(), 
                LinearLayoutManager.HORIZONTAL, // Scroll left/right, not up/down
                false
            )
        }
        
        // 3. Create adapter to manage the movie items
        val adapter = RecyclerViewAdapter(requireContext(), movieUrls)
        adapter.setClickListener(this) // This fragment handles clicks
        
        // 4. Connect adapter to RecyclerView
        recyclerView.adapter = adapter
        
        Log.d(TAG, "$categoryName list setup complete")
    }

    // =================================
    // USER INTERACTION
    // =================================
    
    /**
     * HANDLE MOVIE CLICKS
     * Called when user taps on any movie poster
     */
    override fun onItemClick(view: View, position: Int) {
        Log.d(TAG, "Movie clicked at position: $position")
        
        // Get the movie that was clicked
        val movie = homeViewModel.getMovieAt(position)
        
        if (movie != null) {
            // Show movie title in a toast
            Toast.makeText(
                requireContext(),
                "Selected: ${movie.title}",
                Toast.LENGTH_SHORT
            ).show()
            
            // TODO: Navigate to movie details screen
            // findNavController().navigate(
            //     R.id.action_navigation_home_to_navigation_info,
            //     bundleOf("movieTitle" to movie.title, "movieUrl" to movie.posterUrl)
            // )
            
        } else {
            Log.w(TAG, "Invalid movie position: $position")
            Toast.makeText(requireContext(), "Movie not found", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val TAG = "HomeFragment"
    }
}
