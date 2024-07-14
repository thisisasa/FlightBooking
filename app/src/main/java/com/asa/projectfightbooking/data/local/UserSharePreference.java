package com.asa.projectfightbooking.data.local;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSharePreference {
    private final static String KEY_PAIR = "USERS";
    private final static String USERNAME = "USERNAME";
    private final static String PASSWORD = "PASSWORD";

    public static void saveUserLogin(Context context, String username, String password){
        SharedPreferences preference = context.getSharedPreferences(KEY_PAIR, 0);
        SharedPreferences.Editor editor = preference.edit();
        editor.putString(USERNAME,username);
        editor.putString(PASSWORD, password);
        editor.apply();
    }

    public static String getUsername (Context context){
        SharedPreferences preference = context.getSharedPreferences(KEY_PAIR, 0);
        return preference.getString(USERNAME,"");
    }

    public static void removeUserLogin (Context context){
        SharedPreferences preference = context.getSharedPreferences(KEY_PAIR, 0);
        SharedPreferences.Editor editor = preference.edit();
        editor.remove(USERNAME);
        editor.remove(PASSWORD);
        editor.apply();
    }
}
