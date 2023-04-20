package com.example.vulnapps.steal_tokens;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class Util {
    private static final String TAG = "Util";

    public static String getToken(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences("com.example.vulnapps.steal_tokens_preferences", Context.MODE_PRIVATE);
        String token = sharedPref.getString("token", null);
        Log.d(TAG, "getToken: token = " + token);
        return token;
    }

    public static void setToken(Context context, String token) {
        SharedPreferences sharedPref = context.getSharedPreferences("com.example.vulnapps.steal_tokens_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("token", token);
        editor.apply();
        Log.d(TAG, "setToken: token = " + token);
    }
}
