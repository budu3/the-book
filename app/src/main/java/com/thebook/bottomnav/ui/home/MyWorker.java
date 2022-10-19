package com.thebook.bottomnav.ui.home;


import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorker extends Worker {

    public MyWorker (Context context, WorkerParameters workerParams){
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        String remoteData;
        remoteData = getRemoteData();

        Data outputData = new Data.Builder()
                .putString("RemoteData", remoteData)
                .build();
        return Result.success(outputData);
    }

    private String getRemoteData(){
        Log.d("Worker->", "I got here");

        URL url = null;
        BufferedReader br = null;
        String result = null;
        //start snippet
        try {
            url = new URL("https://raw.githubusercontent.com/budu3/the-book/master/code/myflix/movies.json");

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            Log.d("Service->","connected");
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String output;
            StringBuilder builder = new StringBuilder();
            while ((output = br.readLine()) != null){
                builder.append(output);
            }
            br.close();
            Log.d("Worker->", builder.toString());
            result = builder.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //end snippet
        return result;
    }

}
