package com.example.vulnapps.sharing_activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.List;

public class ShareActivity extends AppCompatActivity {

    static final String TAG = "ShareActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        Intent intent = getIntent();
        String action = intent.getAction();
        if (Intent.ACTION_SEND.equals(action)) {
            Uri uri = intent.getParcelableExtra(Intent.EXTRA_STREAM);
            processUri(uri);
        } else if (Intent.ACTION_SEND_MULTIPLE.equals(action)) {
            List<Uri> uris = intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
            for (Uri uri : uris) {
                processUri(uri);
            }
        }
    }

    private void processUri(Uri uri) {
        Log.d(TAG, "onCreate: uri = " + uri);
        if (!(new File(uri.getPath()).exists())) {
            Toast.makeText(this, "File not Found.", Toast.LENGTH_SHORT).show();
            return;
        }
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

            Toast.makeText(this, "Cached " + uri + ".", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}