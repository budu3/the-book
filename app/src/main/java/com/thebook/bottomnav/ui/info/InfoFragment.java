package com.thebook.bottomnav.ui.info;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.thebook.bottomnav.R;

/**
 * InfoFragment - Displays detailed information about selected movies
 * 
 * This fragment is navigated to when users click on movie items in the HomeFragment.
 * It receives data through navigation arguments (Bundle) and displays movie details.
 * The fragment uses InfoViewModel to manage and provide data for display.
 * 
 * Currently displays basic information and logs received bundle data for debugging.
 */
public class InfoFragment extends Fragment {

    // ViewModel for managing info screen data
    private InfoViewModel mViewModel;

    /**
     * Factory method for creating new instances of InfoFragment
     * @return A new instance of InfoFragment
     */
    public static InfoFragment newInstance() {
        return new InfoFragment();
    }

    /**
     * Creates and returns the view hierarchy associated with the fragment
     * Sets up ViewModel, observes data, and processes navigation arguments
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the fragment layout
        View root = inflater.inflate(R.layout.info_fragment, container, false);
        final TextView textView = root.findViewById(R.id.text_info);
        
        // Initialize ViewModel and observe text data
        mViewModel = new ViewModelProvider(this).get(InfoViewModel.class);
        mViewModel.getText().observe(getViewLifecycleOwner(),
          new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                // Update text view when ViewModel data changes
                textView.setText(s);
            }
        });
        
        // Process navigation arguments passed from HomeFragment
        Bundle args = getArguments();
        if (args != null) {
            // Log received data for debugging purposes
            Log.d("Bundle", "Poster: " + args.getString("poster"));
            Log.d("Bundle", "Title: " + args.getString("title"));
            Log.d("Bundle", "Position: " + args.getInt("position", -1));
        }

        return root;
    }

}
