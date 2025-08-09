package com.thebook.bottomnav.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.thebook.bottomnav.R;
import java.util.ArrayList;

/**
 * HomeFragment - The main screen displaying movie lists
 * 
 * This fragment displays three horizontal RecyclerView lists:
 * 1. Favorites - starts at position 0
 * 2. Drama - starts at position 2  
 * 3. Comedy - starts at position 3
 * 
 * Each list shows the same movie data but with different starting positions
 * to create the illusion of different categories. The fragment observes
 * movie data from HomeViewModel and sets up RecyclerViews when data changes.
 * 
 * Implements ItemClickListener to handle movie item clicks and navigate
 * to the InfoFragment with movie details.
 */
public class HomeFragment extends Fragment
  implements RecyclerViewAdapter.ItemClickListener{
    // ViewModel for managing movie data
    private HomeViewModel homeViewModel;
    /**
     * Called to create the view hierarchy associated with the fragment.
     * Sets up the ViewModel, inflates the layout, and observes movie data changes.
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        // Initialize ViewModel to handle movie data
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        
        // Inflate the fragment layout
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);

        // Observe movie data changes from ViewModel
        homeViewModel.getArrayList().observe(getViewLifecycleOwner(),
          new Observer<ArrayList<SimpleViewModel>>() {
            @Override
            public void onChanged(@Nullable ArrayList<SimpleViewModel> movieList){
                Context context = getContext();
                
                // Setup three horizontal RecyclerViews with different starting positions
                setupRecyclerView(root, context, movieList, R.id.fav_recyclerview, 0);    // Favorites
                setupRecyclerView(root, context, movieList, R.id.drama_recyclerview, 2);  // Drama 
                setupRecyclerView(root, context, movieList, R.id.comedy_recyclerview, 3); // Comedy
            }
        });
        return root;
    }

    /**
     * Helper method to set up a RecyclerView with movie data
     * Reduces code duplication by centralizing RecyclerView configuration
     * 
     * @param root The root view containing the RecyclerView
     * @param context The context for creating layout manager and adapter
     * @param movieList The list of movies to display
     * @param recyclerViewId The resource ID of the RecyclerView to configure
     * @param scrollPosition The initial scroll position (0 = start, >0 = scroll to position)
     */
    private void setupRecyclerView(View root, Context context, ArrayList<SimpleViewModel> movieList, 
                                  int recyclerViewId, int scrollPosition) {
        // Find the RecyclerView by ID
        RecyclerView recyclerView = root.findViewById(recyclerViewId);
        
        // Set fixed size optimization for the favorites list only
        if (recyclerViewId == R.id.fav_recyclerview) {
            recyclerView.setHasFixedSize(true);
        }
        
        // Configure horizontal scrolling layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(context, 
                                    LinearLayoutManager.HORIZONTAL, false));
        
        // Create and configure the adapter
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(context, movieList);
        adapter.setClickListener(this);  // Set this fragment as the click listener
        recyclerView.setAdapter(adapter);
        
        // Scroll to specific position if needed (for Drama and Comedy lists)
        if (scrollPosition > 0) {
            recyclerView.scrollToPosition(scrollPosition);
        }
    }

    /**
     * Generic click handler (currently not used but kept for potential future use)
     * Extracts resource name from view ID and navigates to info screen
     */
    public void onClick(View view) {
        String resourceName;
        Bundle bundle = new Bundle();

        // Extract resource name from the view's ID
        resourceName = view.getResources()
          .getResourceName(view.getId()).split("/")[1];
        bundle.putString("id", resourceName);
        
        // Navigate to info fragment with resource name
        Navigation.findNavController(view).navigate(
          R.id.action_navigation_home_to_navigation_info, bundle);
    }

    /**
     * Handles click events on movie items in RecyclerView lists
     * Implementation of RecyclerViewAdapter.ItemClickListener interface
     * 
     * @param view The clicked view
     * @param position The position of the clicked item in the list
     */
    @Override
    public void onItemClick(View view, int position) {
        // Create bundle to pass data to info fragment
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);

        // Show feedback to user
        Toast.makeText(getContext(),
          "You clicked on position number " + position,
          Toast.LENGTH_SHORT).show();
        
        // Navigate to info fragment with movie position data
        Navigation.findNavController(view).navigate(
          R.id.action_navigation_home_to_navigation_info,
          bundle);
    }
}
