package com.example.sunrun.menu.home;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.sunrun.R;
import com.example.sunrun.menu.fragments.ProgressFragment;
import com.example.sunrun.menu.fragments.HistoryFragment;
import com.example.sunrun.menu.fragments.ProbaGoalFragment;
import com.example.sunrun.menu.fragments.RunFragment;
import com.example.sunrun.menu.fragments.SettingsFragment;
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
                replaceFragment(new ProbaGoalFragment());
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

