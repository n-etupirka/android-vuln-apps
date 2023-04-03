package com.example.vulnapps.steal_tokens.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.vulnapps.steal_tokens.HttpClient;
import com.example.vulnapps.steal_tokens.R;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText emailEditText = findViewById(R.id.email);
        EditText passwordEditText = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.login);

        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            String json = "{\"email\":\"" + email + "\",\"password\":\"" + password + "\"}";
            HttpClient httpClient = new HttpClient(LoginActivity.this, getString(R.string.url_login), json);
            httpClient.httpPostRequest();
        });
    }
}
