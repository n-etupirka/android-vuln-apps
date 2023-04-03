package com.example.vulnapps.steal_tokens.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.vulnapps.steal_tokens.R;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationItemView homeButtom = findViewById(R.id.navigation_home);
        BottomNavigationItemView notificationsButtom = findViewById(R.id.navigation_notifications);
        BottomNavigationItemView settingsButtom = findViewById(R.id.navigation_settings);

        Intent intent = new Intent(getApplication(), TrustedWebViewActivity.class);

        homeButtom.setOnClickListener(v -> {
            Log.d(TAG, "onCreate: Click home");
        });

        settingsButtom.setOnClickListener(v -> {
            intent.putExtra("EXTRA_URL", getString(R.string.url_settings));
            startActivity(intent);
            Log.d(TAG, "onCreate: Click settings");
        });

        notificationsButtom.setOnClickListener(v -> {
            intent.putExtra("EXTRA_URL", getString(R.string.url_notifications));
            startActivity(intent);
            Log.d(TAG, "onCreate: Click notifications");
        });
    }
}
