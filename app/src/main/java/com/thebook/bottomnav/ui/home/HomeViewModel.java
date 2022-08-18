package com.thebook.bottomnav.ui.home;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.thebook.bottomnav.MyWorker;
import com.thebook.bottomnav.ui.MyImageWorker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

//You need to extend AndroidViewModel
public class HomeViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<ArrayList<SimpleViewModel>> movieLiveData;
    private ArrayList<SimpleViewModel> movieList;
    private static final String PREFS_NAME = "movie";

    public HomeViewModel(Application application) {
        super(application);
        movieLiveData = new MutableLiveData<>();
        movieList = new ArrayList<>();
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");

        SharedPreferences prefs = getApplication().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String movieStr = prefs.getString("movie1","");

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

                    try {
                        JSONArray jsonArray = new JSONArray(output);
                        for (int i=0; i<jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String poster = jsonObject.getString("poster");
                            String title = jsonObject.getString("movie_title");
                            Log.d("HomeViewModel->", " poster " + poster);
                            SimpleViewModel svm = new SimpleViewModel();
                            svm.setTitle(title);
                            svm.setPoster(poster.replace("images/",""));
                            //svm.setImage(imgID);
                            movieList.add(svm);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        /*
        try {
            JSONArray jsonArray = new JSONArray(movieStr);
            int len = jsonArray.length();
            //Log.d("len", String.valueOf(len));
            for (int i=0; i<len; i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                //Log.d("bottomnav:json", jsonObject.toString(2));
                String poster = jsonObject.getString("poster");
                String title = jsonObject.getString("movie_title");
                //Log.d("bottomnav:movie_title", title);
                int imgID = getApplication().getResources().getIdentifier(poster,"drawable",getApplication().getPackageName());
                SimpleViewModel svm = new SimpleViewModel();
                svm.setTitle(title);
                svm.setPoster(poster);
                svm.setImage(imgID);
                movieList.add(svm);
                //Log.d("bottomnav:id", "" + imgID);
                //Log.d("bottomnav:movielist", movieList.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        */
        mText.setValue(movieStr);
        movieLiveData.setValue(movieList);

    }

    public LiveData<ArrayList<SimpleViewModel>> getArrayList() {
        return movieLiveData;
    }
}