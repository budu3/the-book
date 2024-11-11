package com.thebook.bottomnav;

import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //start snippet
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration =
          new AppBarConfiguration.Builder(
              R.id.navigation_home,
              R.id.navigation_dashboard,
              R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(
          this,
          R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(
          this,
          navController,
          appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        //end snippet
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
        NavController navController = Navigation.findNavController(
          this,
          R.id.nav_host_fragment);
        return navController.navigateUp();
    }
}
