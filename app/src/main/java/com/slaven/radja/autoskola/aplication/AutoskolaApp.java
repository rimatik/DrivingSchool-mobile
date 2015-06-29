package com.slaven.radja.autoskola.aplication;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;

/**
 * Created by slaven.radja on 7.10.2014..
 */
public class AutoskolaApp extends Application {

    private static Context context;
    private static int position;
    private static int length;
    private static int selectedFragmentId;
    private static Fragment fragment;

    public void onCreate() {
        super.onCreate();
        AutoskolaApp.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return AutoskolaApp.context;
    }
    public static int getPosition() {
        return position;
    }
    public static void setPosition(int position) {
        AutoskolaApp.position = position;
    }
    public static int getLength() {
        return length;
    }
    public static void setLength(int length) {
        AutoskolaApp.length = length;
    }
    public static int getSelectedFragmentId() {
        return selectedFragmentId;
    }

    public static void setSelectedFragmentId(int selectedFragmentId) {
        AutoskolaApp.selectedFragmentId = selectedFragmentId;
    }
    public static Fragment getFragment() {
        return fragment;
    }
    public static void setFragment(Fragment fragment) {
        AutoskolaApp.fragment = fragment;
    }

    }



