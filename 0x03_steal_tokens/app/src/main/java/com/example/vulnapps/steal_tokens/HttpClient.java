package com.example.vulnapps.steal_tokens;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.UiThread;
import androidx.annotation.WorkerThread;
import androidx.core.os.HandlerCompat;

import com.example.vulnapps.steal_tokens.activity.MainActivity;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpClient {
    private static final String TAG = "HttpClient";
    Context context;
    String endpoint, json;

    public HttpClient(Context context, String endpoint, String json) {
        this.context = context;
        this.endpoint = endpoint;
        this.json = json;
    }

    public void httpPostRequest() {
        Looper looper = Looper.getMainLooper();
        Handler handler = HandlerCompat.createAsync(looper);
        BackgroundTask backgroundTask = new BackgroundTask(handler, context, endpoint, json);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(backgroundTask);
    }
}

class BackgroundTask implements Runnable {
    private static final String TAG = "BackgroundTask";
    private final Handler _handler;
    Context context;
    String endpoint, json;

    public BackgroundTask(Handler handler, Context context, String endpoint, String json) {
        _handler = handler;
        this.context = context;
        this.endpoint = endpoint;
        this.json = json;
    }

    @WorkerThread
    @Override
    public void run() {
        okhttp3.MediaType mediaType = okhttp3.MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(this.json, mediaType);
        try {
            final Request request = new Request.Builder()
                    .url(new URL(this.endpoint))
                    .post(requestBody)
                    .build();
            OkHttpClient client = new OkHttpClient.Builder()
                    .build();
            Response response = client.newCall(request).execute();
            PostExecutor postExecutor = new PostExecutor(context, response);
            _handler.post(postExecutor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class PostExecutor implements Runnable {
    private static final String TAG = "PostExecutor";
    Context context;
    Response response;

    public PostExecutor(Context context, Response response) {
        this.context = context;
        this.response = response;
    }

    @UiThread
    @Override
    public void run() {
        try {
            String result = Objects.requireNonNull(this.response.body()).string();
            Intent intent = new Intent(context, MainActivity.class);
            if (!result.isEmpty()) {
                Util.setToken(context, result);
                Log.d(TAG, "PostRequest: result = " + result);
                context.startActivity(intent);
            } else {
                Toast.makeText(context, context.getString(R.string.login_failed), Toast.LENGTH_SHORT).show();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
