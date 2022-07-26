package com.thebook.bottomnav;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.thebook.bottomnav.ui.MyImageWorker;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.work.OneTimeWorkRequest;
import androidx.work.Operation;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

//import this to have access to the MyBinder class
//import com.thebook.bottomnav.SimpleService.LocalBinder;

public class MainActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "movie";
    //private SimpleService myBoundService;
    private boolean bounded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        //final WorkRequest workRequest = new OneTimeWorkRequest.Builder(MyWorker.class).build();
        //final WorkRequest imageRequest = new OneTimeWorkRequest.Builder(MyImageWorker.class).build();

        final OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(MyWorker.class).build();
        final OneTimeWorkRequest imageRequest = new OneTimeWorkRequest.Builder(MyImageWorker.class).build();

        //WorkManager.getInstance().enqueue(workRequest);
        WorkManager.getInstance().beginWith(workRequest).then(imageRequest).enqueue();
        WorkManager.getInstance().getWorkInfoByIdLiveData(workRequest.getId()).observeForever(new Observer<WorkInfo>() {
            String output = "";
            @Override
            public void onChanged(WorkInfo workInfo) {
                Log.d("MainActivity->", "" + workInfo.getState());
                if (workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                    Log.d("MainActivity->", "I got here");
                    output = workInfo.getOutputData().getString("RemoteData");
                    Log.d("MainActivity->",output);
                }
            }
        });


        try {
            saveToSharedPreferences(this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void onStart() {
        String remoteData = null;

        super.onStart();
        /*
        Intent intent = new Intent(this, SimpleService.class);
        startService(intent);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        Log.d("Bounded OnStart->", ""+bounded);
         */
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.d("Bounded run->", ""+bounded);
                if (bounded){
                    String remoteData = myBoundService.getRemoteData();
                    Log.d("Remote->", remoteData);
                }
            }
        };

        Handler handler = new Handler();
        handler.postDelayed(runnable, 3000);
         */
    }

    protected void onStop() {
        super.onStop();
        /*
        if (bounded) {
            unbindService(serviceConnection);
            bounded = false;
        }
        Log.d("Bounded","onStop");
         */
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return navController.navigateUp();
    }

    //user defined method
    private void saveToSharedPreferences(Context context) throws JSONException {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor preferencesEditor = prefs.edit();

        String movieJSONArray = "[{" +
                "\"director_name\": \"James Cameron\"," +
                "\"actor_2_name\": \"Joel David Moore\"," +
                "\"genres\": \"Action|Adventure|Fantasy|Sci-Fi\"," +
                "\"actor_1_name\": \"CCH Pounder\"," +
                "\"movie_title\": \"Avatar\"," +
                "\"plot_keywords\": \"avatar|future|marine|native|paraplegic\"," +
                "\"title_year\": 2009," +
                "\"poster\": \"tt37\"" +
                "},"

                + "{" +
                "    \"director_name\": \"Gore Verbinski\"," +
                "        \"actor_2_name\": \"Orlando Bloom\"," +
                "    \"genres\": \"Action|Adventure|Fantasy\"," +
                "    \"actor_1_name\": \"Johnny Depp\"," +
                "    \"movie_title\": \"Pirates of the Caribbean: At World's End\"," +
                "    \"plot_keywords\": \"goddess|marriage ceremony|marriage proposal|pirate|singapore\"," +
                "        \"title_year\": 2007," +
                "    \"poster\": \"tt38\"" +
                "  },"

                + "{" +
                "    \"director_name\": \"Sam Mendes\"," +
                "        \"actor_2_name\": \"Rory Kinnear\"," +
                "    \"genres\": \"Action|Adventure|Thriller\"," +
                "    \"actor_1_name\": \"Christoph Waltz\"," +
                "    \"movie_title\": \"Spectre\"," +
                "    \"plot_keywords\": \"bomb|espionage|sequel|spy|terrorist\"," +
                "        \"title_year\": 2015, " +
                "    \"poster\": \"tt39\"" +
                "  },"

                +"{" +
                "    \"director_name\": \"Christopher Nolan\"," +
                "        \"actor_2_name\": \"Christian Bale\"," +
                "    \"genres\": \"Action|Thriller\"," +
                "    \"actor_1_name\": \"Tom Hardy\"," +
                "    \"movie_title\": \"The Dark Knight Rises\"," +
                "    \"plot_keywords\": \"deception|imprisonment|lawlessness|police officer|terrorist plot\"," +
                "        \"title_year\": 2012," +
                "    \"poster\": \"tt40\"" +
                "  },"

                + "{" +
                "    \"director_name\": \"Doug Walker\"," +
                "        \"actor_2_name\": \"Rob Walker\"," +
                "    \"genres\": \"Documentary\"," +
                "    \"actor_1_name\": \"Doug Walker\"," +
                "    \"movie_title\": \"Star Wars: Episode VII - The Force Awakens\"," +
                "    \"plot_keywords\": \"\"," +
                "        \"title_year\": \"\"," +
                "    \"poster\": \"tt41\"" +
                "  },"

                + "{" +
                "    \"director_name\": \"Andrew Stanton\"," +
                "        \"actor_2_name\": \"Samantha Morton\"," +
                "    \"genres\": \"Action|Adventure|Sci-Fi\"," +
                "    \"actor_1_name\": \"Daryl Sabara\"," +
                "    \"movie_title\": \"John Carter\"," +
                "    \"plot_keywords\": \"alien|american civil war|male nipple|mars|princess\"," +
                "        \"title_year\": 2012," +
                "    \"poster\": \"tt42\"" +
                "  }]";

        preferencesEditor.putString("movie1",movieJSONArray);
        preferencesEditor.apply();

    }

    /*
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            LocalBinder myBinder = (LocalBinder)service;
            myBoundService = myBinder.getService();
            bounded = true;
            Log.d("Bounded onServiceConnected->", ""+bounded);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            bounded = false;
            myBoundService = null;
        }
    };
    */
}
