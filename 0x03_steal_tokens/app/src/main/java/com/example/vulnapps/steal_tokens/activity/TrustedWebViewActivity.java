package com.example.vulnapps.steal_tokens.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.example.vulnapps.steal_tokens.R;
import com.example.vulnapps.steal_tokens.Util;

import java.util.HashMap;
import java.util.Map;

public class TrustedWebViewActivity extends AppCompatActivity {
    private static final String TAG = "TrustedWebViewActivity";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trusted_webview);

        WebView webView = findViewById(R.id.trusted_webview);
        webView.getSettings().setJavaScriptEnabled(true);

        String token = Util.getToken(getApplicationContext());
        Map<String, String> tokenHeader = new HashMap<>();
        tokenHeader.put("Token", token);

        Intent intent = getIntent();
        String uri = intent.getStringExtra("EXTRA_URL");
        Log.d(TAG, "onCreate: intent = " + intent + ", extra_url = " + uri);
        webView.loadUrl(uri, tokenHeader);
    }
}