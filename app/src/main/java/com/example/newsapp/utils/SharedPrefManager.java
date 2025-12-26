package com.example.newsapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    private static final String PREF_NAME = "NewsAppPrefs";
    private static final String KEY_EMAIL = "user_email";
    private static final String KEY_PASSWORD = "user_password";
    private static final String KEY_FIRST_NAME = "first_name";
    private static final String KEY_LAST_NAME = "last_name";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedPrefManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public boolean register(String email, String password, String firstName, String lastName) {
        String savedEmail = sharedPreferences.getString(KEY_EMAIL, null);
        if (savedEmail != null && savedEmail.equals(email)) {
            return false;
        }
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_FIRST_NAME, firstName);
        editor.putString(KEY_LAST_NAME, lastName);
        editor.apply();
        return true;
    }

    public boolean login(String email, String password) {
        String savedEmail = sharedPreferences.getString(KEY_EMAIL, null);
        String savedPassword = sharedPreferences.getString(KEY_PASSWORD, null);

        return email.equals(savedEmail) && password.equals(savedPassword);
    }

    public String getUserEmail() {
        return sharedPreferences.getString(KEY_EMAIL, null);
    }

    public String getUserFullName() {
        String firstName = sharedPreferences.getString(KEY_FIRST_NAME, "");
        String lastName = sharedPreferences.getString(KEY_LAST_NAME, "");
        return firstName + " " + lastName;
    }

}
