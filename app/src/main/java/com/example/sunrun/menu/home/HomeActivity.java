package com.example.sunrun.menu.home;


import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.sunrun.R;
import com.example.sunrun.menu.fragments.progress.ProgressFragment;
import com.example.sunrun.menu.fragments.history.HistoryFragment;
import com.example.sunrun.menu.fragments.months.MonthsFragment;
import com.example.sunrun.menu.fragments.run.RunFragment;
import com.example.sunrun.menu.fragments.settings.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);


        BottomNavigationView bottomNavView = findViewById(R.id.bottomMenu);
        bottomNavView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.progress) {
                replaceFragment(new ProgressFragment());
                return true;
            } else if (itemId == R.id.run) {
                replaceFragment(new RunFragment());
                return true;
            } else if (itemId == R.id.history) {
                replaceFragment(new HistoryFragment());
                return true;
            } else if (itemId == R.id.settings) {
                replaceFragment(new SettingsFragment());
                return true;
            } else if (itemId == R.id.gallery) {
                replaceFragment(new MonthsFragment());
                return true;
            }
            return false;
        });

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (!fragmentManager.isStateSaved()) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, fragment);
            fragmentTransaction.commit();
        }
    }


}


