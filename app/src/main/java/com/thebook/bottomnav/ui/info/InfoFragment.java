package com.thebook.bottomnav.ui.info;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thebook.bottomnav.R;

public class InfoFragment extends Fragment {

    private InfoViewModel mViewModel;

    public static InfoFragment newInstance() {
        return new InfoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.info_fragment, container, false);
        final TextView textView = root.findViewById(R.id.text_info);
        final ImageView imageView = root.findViewById(R.id.info_image);
        mViewModel = new ViewModelProvider(this).get(InfoViewModel.class);
        mViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        String title = getArguments().getString("title");
        textView.setText(title);
        //imageView.setImageResource(getArguments().getInt("image"));
        String poster = getArguments().getString("poster");
        String pathToPicture = root.getContext().getCacheDir() + "/" + poster;
        imageView.setImageBitmap(BitmapFactory.decodeFile(pathToPicture));

        /*
        Log.d("Bundle", getArguments().getString("poster"));
        Log.d("Bundle", getArguments().getString("title"));
        Log.d("Bundle", "" + getArguments().getInt("image"));
        */
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(InfoViewModel.class);
        // TODO: Use the ViewModel
    }


}
