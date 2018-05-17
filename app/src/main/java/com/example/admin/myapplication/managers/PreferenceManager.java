package com.example.admin.myapplication.managers;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.admin.myapplication.R;

public class PreferenceManager {
    public static final String PREFERENCE_NAME = "myapppreference";
    private static SharedPreferences sSharedPreferences;
    private static final String PREF_KEY_ISDATAFETCH = "isDataFetched";

    public static SharedPreferences getInstance(Context context) {

        if(sSharedPreferences != null) {
            return sSharedPreferences;
        } else {
            sSharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
            return sSharedPreferences;
        }
    }

    public void setDataFetched(boolean isDataFetched) {
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putBoolean(PREF_KEY_ISDATAFETCH, isDataFetched);
        editor.commit();
    }

    public boolean isDataFetched() {
        boolean isFetched = sSharedPreferences.getBoolean(PREF_KEY_ISDATAFETCH, false);
        return isFetched;
    }
}
