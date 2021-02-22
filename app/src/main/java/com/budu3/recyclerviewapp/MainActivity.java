package com.budu3.recyclerviewapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // data to populate the RecyclerView with
        ArrayList<FeedItem> movieList = new ArrayList<>();
        movieList.add(new FeedItem("Wonder Woman 1", R.drawable.wonder_woman_1984_thumb));
        movieList.add(new FeedItem("Wonder Woman 2", R.drawable.wonder_woman_1984_thumb));
        movieList.add(new FeedItem("Wonder Woman 3", R.drawable.wonder_woman_1984_thumb));
        movieList.add(new FeedItem("Wonder Woman 4", R.drawable.wonder_woman_1984_thumb));
        movieList.add(new FeedItem("Wonder Woman 5", R.drawable.wonder_woman_1984_thumb));

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvMovies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, movieList);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}
