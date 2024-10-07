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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

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
        final View root = inflater.inflate(R.layout.fragment_home,
          container,
          false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(),
          new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //build the images here
                textView.setText(s);
                String poster = "";
                int length = 0;
                int rand = 0;

                try {
                    JSONArray jsonArray = new JSONArray(s);
                    length = jsonArray.length();

                    for (int i=4; i<16; i++){
                        rand = new Random()
                          .nextInt(length - 1);
                        JSONObject jsonObject = jsonArray
                          .getJSONObject(rand);
                        poster = jsonObject.getString("poster");

                        // get id for an image view
                        int id = getResources()
                          .getIdentifier("imageView" + i,"id",
                                getActivity().getPackageName());
                        ImageView imgView = root.findViewById(id);

                        //get id for an image
                        int imgID = getResources().getIdentifier(poster,
                          "drawable" ,
                          getActivity().getPackageName()) ;
                        imgView.setImageResource(imgID);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        //add onClickListener to scrolling images
        for (int i=4; i<16; i++){
            int id = getResources().getIdentifier("imageView" + i,
              "id",
              getActivity().getPackageName());
            ImageView imgView = root.findViewById(id);
            imgView.setOnClickListener(this);
        }
        return root;
    }

    @Override
    public void onClick(View view) {
        String resourceName;
        Bundle bundle = new Bundle();

        resourceName = view.getResources()
          .getResourceName(view.getId()).split("/")[1];
        bundle.putString("id", resourceName);
        Navigation.findNavController(view)
          .navigate(R.id.action_navigation_home_to_navigation_info,
            bundle);
    }
}
