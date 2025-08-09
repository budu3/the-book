package com.thebook.bottomnav.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.thebook.bottomnav.R;
import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView;

/**
 * RecyclerViewAdapter - Adapter for displaying movie items in horizontal lists
 * 
 * This adapter manages the display of movie items in RecyclerView components.
 * It handles the creation and binding of ViewHolder objects that represent
 * individual movie cards in the horizontal scrolling lists.
 * 
 * The adapter follows the standard RecyclerView pattern with:
 * - ViewHolder inner class for holding view references
 * - ItemClickListener interface for handling click events
 * - Standard adapter methods for item management
 */
public class RecyclerViewAdapter extends
  RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    // List of movie data to display
    private ArrayList<SimpleViewModel> data;
    
    // Layout inflater for creating views
    private LayoutInflater layoutInflater;
    
    // Click listener for handling item clicks
    private ItemClickListener mClickListener;

    /**
     * Constructor - Initializes the adapter with context and data
     * @param context The context for accessing layout inflater
     * @param data The list of movie data to display
     */
    RecyclerViewAdapter(Context context, ArrayList<SimpleViewModel> data) {
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }

    /**
     * Creates new ViewHolder instances when needed
     * Called by RecyclerView when it needs a new view to display
     * @param parent The ViewGroup into which the new View will be added
     * @param viewType The view type of the new View (not used here)
     * @return A new ViewHolder that holds a View of the given view type
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the item layout for each movie card
        View view = layoutInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Binds data to ViewHolder for display
     * Called by RecyclerView to display data at the specified position
     * @param holder The ViewHolder which should be updated
     * @param position The position of the item within the adapter's data set
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Get movie data for this position
        SimpleViewModel movie = data.get(position);
        
        // Set the movie poster image
        holder.myImageView.setImageResource(movie.getImage());
    }

    /**
     * Returns the total number of items in the data set
     * @return The total number of items in this adapter
     */
    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * ViewHolder - Holds references to views for each movie item
     * 
     * This inner class implements the ViewHolder pattern for efficient
     * view recycling in RecyclerView. It holds references to the UI
     * components and handles click events for individual movie items.
     */
    public class ViewHolder extends RecyclerView.ViewHolder
      implements View.OnClickListener {

        // UI components (TextView currently unused but kept for future use)
        TextView myTextView;
        ImageView myImageView;

        /**
         * Constructor - Initializes view references and sets up click listener
         * @param itemView The item view that this ViewHolder will manage
         */
        ViewHolder(View itemView) {
            super(itemView);
            
            // Get reference to the movie poster ImageView
            myImageView = itemView.findViewById(R.id.movie_poster);
            
            // Set this ViewHolder as the click listener for the entire item
            itemView.setOnClickListener(this);
        }

        /**
         * Handles click events on movie items
         * Delegates to the adapter's click listener if set
         * @param view The clicked view
         */
        @Override
        public void onClick(View view) {
            if (mClickListener != null) {
                // Forward click event to the registered listener with position
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    /**
     * Gets the movie item at the specified position
     * @param id The position/index of the item to retrieve
     * @return The SimpleViewModel object at the specified position
     */
    SimpleViewModel getItem(int id) {
        return data.get(id);
    }

    /**
     * Sets the click listener for handling item click events
     * @param itemClickListener The listener to handle click events
     */
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    /**
     * Interface for handling click events on RecyclerView items
     * Implementing classes can respond to item clicks
     */
    public interface ItemClickListener {
        /**
         * Called when a RecyclerView item is clicked
         * @param view The clicked view
         * @param position The position of the clicked item
         */
        void onItemClick(View view, int position);
    }
}