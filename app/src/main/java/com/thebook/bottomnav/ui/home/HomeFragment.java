package com.thebook.bottomnav.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.thebook.bottomnav.R;

public class HomeFragment extends Fragment
        implements View.OnClickListener{

    private HomeViewModel homeViewModel;
    private ImageView imageView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this)
                  .get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home,
                container,
                false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(
                getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        
        for (int i=4; i<16; i++){
            int id = getResources().getIdentifier(
                    "imageView" + i,"id",
                    getActivity().getPackageName());
            ImageView imgView = root.findViewById(id);
            imgView.setOnClickListener(this);
        }
        return root;
    }

    @Override
    public void onClick(View view) {
        Navigation.findNavController(view)
          .navigate(R.id.action_navigation_home_to_navigation_info);
    }
}
