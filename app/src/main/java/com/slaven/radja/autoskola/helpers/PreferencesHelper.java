package com.slaven.radja.autoskola.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class PreferencesHelper {

    public static SharedPreferences getSharedPrefs(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        getSharedPrefs(context).edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        return getSharedPrefs(context).getBoolean(key, defaultValue);
    }
}
