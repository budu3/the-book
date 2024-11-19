package com.thebook.bottomnav.ui.home;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thebook.bottomnav.R;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<SimpleViewModel> data;

    private List<String> images;
    private LayoutInflater layoutInflater;
    private ItemClickListener mClickListener;
    private Context context;

    /*
    RecyclerViewAdapter(Context context, ArrayList<SimpleViewModel> data) {
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
        Log.d("RecyclerViewAdapter->", "Inside RecyclerViewAdapter");
    }
    */
    RecyclerViewAdapter(Context context, List<String> images) {
        this.layoutInflater = LayoutInflater.from(context);
        this.images = images;
        this.context = context;
        Log.d("RecyclerViewAdapter->", "Inside RecyclerViewAdapter");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("RecyclerViewAdapter->", "Inside onCreateViewHolder");
        View view = layoutInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        /*
        Log.d("RecyclerView->","Inside onBindViewHolder");
        //String pathToPicture = context.getCacheDir() + "/a-ghost-story.jpg";
        SimpleViewModel movie = data.get(position);
        String poster = movie.getPoster();
        String pathToPicture = context.getCacheDir() + "/" + poster;
        //holder.myImageView.setImageResource(movie.getImage());
        File imgFile = new File(pathToPicture);
        if (imgFile.exists()) {
            holder.myImageView.setImageBitmap(BitmapFactory.decodeFile(pathToPicture));
        } else {
            Log.d("RecyclerViewAdapter->", "Image file does not exist at: " + pathToPicture);
        }
        holder.myImageView.setImageBitmap(BitmapFactory.decodeFile(pathToPicture));
        Log.d("RecyclerView->",pathToPicture);

         */
        String url = images.get(position);
        Glide.with(context).load(url).into(holder.myImageView);
    }

    @Override
    public int getItemCount() {
        //Log.d("RecyclerViewAdapter->", "Data size in adapter: " + data.size());
        //return data.size();
        Log.d("RecyclerViewAdapter->", "Data size in adapter: " + images.size());
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView myTextView;
        ImageView myImageView;

        ViewHolder(View itemView) {
            super(itemView);
            myImageView = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
            Log.d("RecyclerViewAdapter->", "Inside ViewHolder");
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    /*
    SimpleViewModel getItem(int id) {
        return data.get(id);
    }
     */

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}