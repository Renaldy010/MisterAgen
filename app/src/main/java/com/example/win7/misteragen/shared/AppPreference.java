package com.example.win7.misteragen.shared;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreference {

    private static final String PREF_NAME = "MisterAgen";
    private static final int PREF_MODE = 0;
    private static final String USER_ID = "userid";
    private static final String USER_EMAIL = "useremail";
    private static final String USER_PASSWORD = "password";
    private static final String USER_ADDRESS = "address";
    private static final String USER_PHONENUMBER = "phonenumber";
    private static final String USER_NAME = "NAME";

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public AppPreference(Context context) {
        mSharedPreferences = context.getSharedPreferences(PREF_NAME, PREF_MODE);
    }

    public String getUserId() {
        return mSharedPreferences.getString(USER_ID, "");
    }

    public void setUserId(String userId) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(USER_ID, "");
        mEditor.apply();
    }

    public String getUserEmail() {
        return mSharedPreferences.getString(USER_EMAIL, "");
    }

    public void setUserEmail(String userEmail) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(USER_EMAIL, userEmail);
        mEditor.apply();
    }

    public String getUserPassword() {
        return mSharedPreferences.getString(USER_PASSWORD, "");
    }

    public void setUserPassword(String userPassword) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(USER_PASSWORD, userPassword);
        mEditor.apply();
    }

    public String getUserPhonenumber() {
        return mSharedPreferences.getString(USER_PHONENUMBER, "");
    }

    public void setUserPhonenumber(String userPhonenumber) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(USER_PHONENUMBER, userPhonenumber);
        mEditor.apply();
    }

    public String getUserAddress() {
        return mSharedPreferences.getString(USER_ADDRESS, "");
    }

    public void setUserAddress(String userAddress) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(USER_ADDRESS, userAddress);
        mEditor.apply();
    }

    public String getUserName() {
        return mSharedPreferences.getString(USER_NAME, "");
    }

    public void setUserName(String userName) {
        mEditor = mSharedPreferences.edit();
        mEditor.putString(USER_NAME, userName);
        mEditor.apply();
    }
}

