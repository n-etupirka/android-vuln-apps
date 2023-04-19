package com.example.vulnapps.exported_providers;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button create_secret_data = findViewById(R.id.create_secret_data);

        create_secret_data.setOnClickListener(view -> {
            SharedPreferences secrets = getSharedPreferences("secrets", MODE_PRIVATE);
            SharedPreferences.Editor editor = secrets.edit();
            editor.putString("AccessToken", "044b991bf5a551d3c188158f95fc2107");
            editor.apply();
            Log.d(TAG, "onCreate: Created secrets.xml.");
            Toast.makeText(this, "Created secrets.xml.", Toast.LENGTH_SHORT).show();
        });
    }
}