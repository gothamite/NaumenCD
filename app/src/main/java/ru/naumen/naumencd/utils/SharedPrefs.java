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

    public void putComputers(int page) {
        sharedPreferences.edit().putInt(COMPUTERS, page).apply();
    }

    public int getComputers() {
        return sharedPreferences.getInt(COMPUTERS, 0);
    }
}
