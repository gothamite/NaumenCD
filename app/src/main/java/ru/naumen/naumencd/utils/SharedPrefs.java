package ru.naumen.naumencd.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPrefs {
    private static final String COMPUTERS = "COMPUTERS";

    private final SharedPreferences sharedPreferences;

    public SharedPrefs(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }
    public void putComputers(String str) {
        sharedPreferences.edit().putString(COMPUTERS, str).apply();
    }

    public String getComputers() {
        return sharedPreferences.getString(COMPUTERS, null);
    }
}
