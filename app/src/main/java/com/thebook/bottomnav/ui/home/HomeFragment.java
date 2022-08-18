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
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.thebook.bottomnav.MyWorker;
import com.thebook.bottomnav.R;
import com.thebook.bottomnav.ui.MyImageWorker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class HomeFragment extends Fragment implements RecyclerViewAdapter.ItemClickListener{

    private HomeViewModel homeViewModel;
    private ImageView imageView;
    private  RecyclerView recyclerView;
    private RecyclerView recyclerViewDrama;
    private RecyclerViewAdapter adapter;
    private RecyclerViewAdapter adapterDrama;
    private HomeFragment home;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ArrayList<SimpleViewModel> movieList = new ArrayList<>();

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        home = this;

        homeViewModel.getArrayList().observe(getViewLifecycleOwner(), new Observer<ArrayList<SimpleViewModel>>() {
            @Override
            public void onChanged(@Nullable ArrayList<SimpleViewModel> movieList) {
                // data to populate the RecyclerView with

                Context context = getContext();
                recyclerView = root.findViewById(R.id.fav_recyclerview);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                adapter = new RecyclerViewAdapter(context, movieList);
                adapter.setClickListener(home);
                recyclerView.setAdapter(adapter);

                recyclerView = root.findViewById(R.id.drama_recyclerview);
                recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                adapter = new RecyclerViewAdapter(context, movieList);
                adapter.setClickListener(home);
                recyclerView.setAdapter(adapter);
                recyclerView.scrollToPosition(2);

                recyclerView = root.findViewById(R.id.comedy_recyclerview);
                recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                adapter = new RecyclerViewAdapter(context, movieList);
                adapter.setClickListener(home);
                recyclerView.setAdapter(adapter);
                recyclerView.scrollToPosition(3);
            }
        });
        //End of viewModel

        /*
        final OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(MyWorker.class).build();
        final OneTimeWorkRequest imageRequest = new OneTimeWorkRequest.Builder(MyImageWorker.class).build();

        WorkManager.getInstance().beginWith(workRequest).then(imageRequest).enqueue();
        WorkManager.getInstance().getWorkInfoByIdLiveData(workRequest.getId()).observeForever(new Observer<WorkInfo>() {
            String output = "";
            @Override
            public void onChanged(WorkInfo workInfo) {
                Log.d("HomeFragment->", "" + workInfo.getState());
                if (workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                    Log.d("HomeFragment->", "I got here");
                    output = workInfo.getOutputData().getString("RemoteData");
                    Log.d("HomeFragment->",output);
                }
            }
        });
         */

        return root;
    }

    //@Override
    //todo: pass movielist to the bundle here
    public void onClick(View view) {
        String resourceName;
        Bundle bundle = new Bundle();

        resourceName = view.getResources().getResourceName(view.getId()).split("/")[1];
        bundle.putString("id", resourceName);
        Navigation.findNavController(view).navigate(R.id.action_navigation_home_to_navigation_info, bundle);
    }

    @Override
    public void onItemClick(View view, int position) {
        Bundle bundle = new Bundle();
        SimpleViewModel item = adapter.getItem(position);
        int image = item.getImage();
        String poster = item.getPoster();
        String title = item.getTitle();

        bundle.putInt("image", image);
        bundle.putString("poster", poster);
        bundle.putString("title", title);

        //Toast.makeText(getContext(), "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
        Toast.makeText(getContext(), "You clicked  on position number " + position, Toast.LENGTH_SHORT).show();
        Navigation.findNavController(view).navigate(R.id.action_navigation_home_to_navigation_info, bundle);
    }
}
