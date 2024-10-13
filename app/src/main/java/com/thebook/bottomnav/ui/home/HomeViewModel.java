package com.thebook.bottomnav.ui.home;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

//You need to extend AndroidViewModel
public class HomeViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<ArrayList<SimpleData>> movieLiveData;
    private ArrayList<SimpleData> movieList;
    private static final String PREFS_NAME = "movie";

    public HomeViewModel(Application application) {
        super(application);
        movieLiveData = new MutableLiveData<>();
        movieList = new ArrayList<>();
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");

        SharedPreferences prefs =
          getApplication().getSharedPreferences(
            PREFS_NAME,
            Context.MODE_PRIVATE);
        String movieStr = prefs.getString("movie1","");

        try {
            JSONArray jsonArray = new JSONArray(movieStr);
            int len = jsonArray.length();
            //Log.d("len", String.valueOf(len));
            for (int i=0; i<len; i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Log.d("bottomnav:json",
                  jsonObject.toString(2));
                String poster = jsonObject.getString("poster");
                String title = jsonObject.getString("movie_title");
                Log.d("bottomnav:movie_title", title);
                int imgID = getApplication()
                  .getResources()
                  .getIdentifier(poster,
                    "drawable",
                    getApplication().getPackageName());
                SimpleData svm = new SimpleData();
                svm.setTitle(title);
                svm.setPoster(poster);
                svm.setImage(imgID);
                movieList.add(svm);
                Log.d("bottomnav:id", String.valueOf(imgID));
                Log.d("bottomnav:movielist", movieList.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mText.setValue(movieStr);
        movieLiveData.setValue(movieList);

    }

    public LiveData<ArrayList<SimpleData>> getArrayList() {
        return movieLiveData;
    }
}