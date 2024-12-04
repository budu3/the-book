package com.thebook.bottomnav.ui.home;

import android.view.View;
import android.widget.ImageView;
import com.thebook.bottomnav.R;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder{
    ImageView myImageView;

    ViewHolder(View itemView) {
        super(itemView);
        myImageView = itemView.findViewById(R.id.imageView);
    }


}
