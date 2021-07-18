package com.example.vulnapps.access_to_private_components;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {
    private static final String TAG = "WebViewActivity";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        WebView webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);

        Intent intent = getIntent();
        Log.d(TAG, "onCreate: " + intent);

        Uri uri = intent.getData();
        String queryParameter = uri.getQueryParameter("url");
        webView.loadUrl(queryParameter);
    }
}