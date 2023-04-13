package com.example.vulnapps.implicit_intents;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "MainActivity";
    static final int REQUEST_SELECT_CONTACT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button create_secret_data = findViewById(R.id.create_secret_data);
        Button send_implicit_intent = findViewById(R.id.send_implicit_intent);

        create_secret_data.setOnClickListener(view -> {
            SharedPreferences secrets = getSharedPreferences("secrets", MODE_PRIVATE);
            SharedPreferences.Editor editor = secrets.edit();
            editor.putString("AccessToken", "044b991bf5a551d3c188158f95fc2107");
            editor.apply();
        });

        send_implicit_intent.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_SELECT_CONTACT);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (!(requestCode == REQUEST_SELECT_CONTACT && resultCode == RESULT_OK)) {
            return;
        }
        Uri uri = data.getData();
        Log.d(TAG, "onActivityResult: uri = " + uri);
        File cacheFile = new File(getExternalCacheDir(), "temp");
        copyCache(uri, cacheFile);
    }

    private void copyCache(Uri uri, File toFile) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            OutputStream outputStream = Files.newOutputStream(toFile.toPath());
            Log.d(TAG, "copy: toFile = " + toFile.toPath());

            byte[] bytes = new byte[65536];
            while (true) {
                int read = inputStream.read(bytes);
                if (read == -1) {
                    break;
                }
                outputStream.write(bytes, 0, read);
            }

            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            Log.e(TAG, "copy: ", e);
        }
    }
}