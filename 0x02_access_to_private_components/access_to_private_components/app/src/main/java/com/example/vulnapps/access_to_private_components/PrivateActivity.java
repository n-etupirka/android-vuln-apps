package com.example.vulnapps.access_to_private_components;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PrivateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private);
    }
}