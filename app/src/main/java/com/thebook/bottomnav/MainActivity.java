package com.thebook.bottomnav;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    //private static final String PREFS_NAME = "movie";
    private boolean bounded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        //start snippet
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        //end snippet
        /*
        try {
            saveToSharedPreferences(this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
         */
    }

    protected void onStart() {
        //String remoteData = null;
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return navController.navigateUp();
    }

    //user defined method
    /*
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
     */
}
