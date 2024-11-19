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
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thebook.bottomnav.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment implements RecyclerViewAdapter.ItemClickListener {

    //private HomeViewModel homeViewModel;
    private RecyclerView recyclerViewFav;
    private RecyclerView recyclerViewDrama;
    private RecyclerView recyclerViewComedy;
    private RecyclerViewAdapter adapterFav;
    private RecyclerViewAdapter adapterDrama;
    private RecyclerViewAdapter adapterComedy;
    private HomeFragment home;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Initialize ViewModel
        Context context = getContext();
        //homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        String url = "https://raw.githubusercontent.com/budu3/the-book/master/code/assets/images/";

        List<String> imageUrls = Arrays.asList(
          url+"a-ghost-story.jpg",
          url+"alien-covenant.jpg",
          url+"pirates-of-the-caribbean.jpg",
          url+"sleepless.jpg",
          url+"dark-tower.jpg");

        // Inflate the layout for this fragment
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        home = this;

        // Set up RecyclerView for Favorites
        recyclerViewFav = root.findViewById(R.id.fav_recyclerview);
        recyclerViewFav.setHasFixedSize(true);
        recyclerViewFav.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        adapterFav = new RecyclerViewAdapter(context, imageUrls);
        adapterFav.setClickListener(home);
        recyclerViewFav.setAdapter(adapterFav);

        // Set up RecyclerView for Drama
        recyclerViewDrama = root.findViewById(R.id.drama_recyclerview);
        recyclerViewDrama.setHasFixedSize(true);
        recyclerViewDrama.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        adapterDrama = new RecyclerViewAdapter(context, imageUrls);
        adapterDrama.setClickListener(home);
        recyclerViewDrama.setAdapter(adapterDrama);

        // Set up RecyclerView for Comedy
        recyclerViewComedy = root.findViewById(R.id.comedy_recyclerview);
        recyclerViewComedy.setHasFixedSize(true);
        recyclerViewComedy.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        adapterComedy = new RecyclerViewAdapter(context, imageUrls);
        adapterComedy.setClickListener(home);
        recyclerViewComedy.setAdapter(adapterComedy);

        // Observe the ViewModel data to update RecyclerViews when data is available
        /*
        homeViewModel.getArrayList().observe(getViewLifecycleOwner(), new Observer<ArrayList<SimpleViewModel>>() {
            @Override
            public void onChanged(@Nullable ArrayList<SimpleViewModel> movieList) {
                Context context = getContext();

                Log.d("HomeFragment->",""+movieList.size());
                if (context == null || movieList == null) {
                    Log.d("HomeFragment->", "Context or movieList is null");
                    return;
                }

                // Set up RecyclerView for Favorites
                recyclerViewFav = root.findViewById(R.id.fav_recyclerview);
                recyclerViewFav.setHasFixedSize(true);
                recyclerViewFav.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                adapterFav = new RecyclerViewAdapter(context, movieList);
                adapterFav.setClickListener(home);
                recyclerViewFav.setAdapter(adapterFav);

                // Set up RecyclerView for Drama
                recyclerViewDrama = root.findViewById(R.id.drama_recyclerview);
                recyclerViewDrama.setHasFixedSize(true);
                recyclerViewDrama.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                adapterDrama = new RecyclerViewAdapter(context, movieList);
                adapterDrama.setClickListener(home);
                recyclerViewDrama.setAdapter(adapterDrama);

                // Set up RecyclerView for Comedy
                recyclerViewComedy = root.findViewById(R.id.comedy_recyclerview);
                recyclerViewComedy.setHasFixedSize(true);
                recyclerViewComedy.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                adapterComedy = new RecyclerViewAdapter(context, movieList);
                adapterComedy.setClickListener(home);
                recyclerViewComedy.setAdapter(adapterComedy);
            }
        });
        */
        return root;
    }

    @Override
    public void onItemClick(View view, int position) {
        /*
        Bundle bundle = new Bundle();
        SimpleViewModel item = adapterFav.getItem(position); // Assuming you’re working with fav_recyclerview

        // Retrieve item details
        int image = item.getImage();
        String poster = item.getPoster();
        String title = item.getTitle();

        // Populate the bundle for navigation
        bundle.putInt("image", image);
        bundle.putString("poster", poster);
        bundle.putString("title", title);
        */
        // Display a Toast and navigate
        Toast.makeText(getContext(), "You clicked on position " + position, Toast.LENGTH_SHORT).show();
        //Navigation.findNavController(view).navigate(R.id.action_navigation_home_to_navigation_info, bundle);
    }
}
