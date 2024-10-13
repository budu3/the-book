package com.thebook.bottomnav.ui.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.thebook.bottomnav.R;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder{

    TextView myTextView;
    ImageView myImageView;

    ViewHolder(View itemView) {
        super(itemView);
        myImageView = itemView.findViewById(R.id.imageView);
    }


}
