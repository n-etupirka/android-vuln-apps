package com.example.vulnapps.implicit_intents_poc;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int check = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (check == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(MainActivity.this,new String[] { Manifest.permission.READ_EXTERNAL_STORAGE },0);
        }

        Button read_secret = findViewById(R.id.read_secret);

        read_secret.setOnClickListener(view -> {
            try {
                String text = "";
                String path = "/storage/emulated/0/Android/data/com.example.vulnapps.implicit_intents/cache/temp";
                if (!(new File(path).exists())) {
                    Toast.makeText(this, "File not Found.", Toast.LENGTH_SHORT).show();
                    return;
                }
                FileInputStream fileInputStream = new FileInputStream(path);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream, StandardCharsets.UTF_8));
                String lineBuffer;
                while (true){
                    lineBuffer = reader.readLine();
                    if (lineBuffer != null){
                        text += lineBuffer;
                    }
                    else {
                        break;
                    }
                }
                Log.d(TAG, "onCreate: " + text);
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}