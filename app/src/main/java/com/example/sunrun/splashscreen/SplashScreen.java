package com.example.sunrun.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sunrun.R;
import com.example.sunrun.menu.home.HomeActivity;

public class SplashScreen extends AppCompatActivity {
    private static final int DELAY = 3000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_layout);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreen.this, HomeActivity.class);
                startActivity(intent);

                finish();
            }
        }, DELAY);
    }
}
