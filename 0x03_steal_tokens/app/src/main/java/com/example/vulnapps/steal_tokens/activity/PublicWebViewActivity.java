package com.example.vulnapps.steal_tokens.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.vulnapps.steal_tokens.R;

import java.net.URISyntaxException;

public class PublicWebViewActivity extends AppCompatActivity {
    private static final String TAG = "PublicWebViewActivity";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_webview);

        WebView webView = findViewById(R.id.public_webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @SuppressLint("QueryPermissionsNeeded")
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Uri uri = request.getUrl();
                if (uri.getScheme().equals("intent")) {
                    try {
                        Intent intent = Intent.parseUri(uri.toString(), Intent.URI_INTENT_SCHEME);
                        Log.d(TAG, "shouldOverrideUrlLoading: " + intent);
                        if (intent.resolveActivity(getPackageManager()) != null) {
                            startActivity(intent);
                            return true;
                        } else {
                            // Call Google Play
                            return false;
                        }
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
                return super.shouldOverrideUrlLoading(view, request);
            }
        });

        Intent intent = getIntent();
        Log.d(TAG, "onCreate: " + intent);

        Uri uri = intent.getData();
        String queryParameter = uri.getQueryParameter("url");
        webView.loadUrl(queryParameter);
    }
}