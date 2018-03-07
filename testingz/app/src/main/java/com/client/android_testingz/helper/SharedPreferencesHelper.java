package com.client.android_testingz.helper;

import android.content.SharedPreferences;

import com.client.android_testingz.model.SharedPreferenceEntry;

import java.util.Calendar;

/**
 *
 */

public class SharedPreferencesHelper {
    // Keys for saving values in SharedPreferences.
    public static final String KEY_NAME = "key_name";
    public static final String KEY_DOB = "key_dob_millis";
    public static final String KEY_EMAIL = "key_email";

    // implementation used for persistence
    private final SharedPreferences mSharedPreferences;


    /**
     * Constructor with dependency injection.
     *
     * @param sharedPreferences The {@link SharedPreferences} that will be used in this DAO.
     */
    public SharedPreferencesHelper(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }

    /**
     * Saves the given {@link SharedPreferenceEntry} that contains the user's settings to
     * {@link SharedPreferences}.
     *
     * @param sharedPreferenceEntry contains data to save to {@link SharedPreferences}.
     * @return {@code true} if writing to {@link SharedPreferences} succeeded. {@code false}
     *         otherwise.
     */
    public boolean savePersonalInfo(SharedPreferenceEntry sharedPreferenceEntry){
        // Start a SharedPreferences transaction
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(KEY_NAME, sharedPreferenceEntry.getName());
        editor.putLong(KEY_DOB, sharedPreferenceEntry.getDateOfBirth().getTimeInMillis());
        editor.putString(KEY_EMAIL, sharedPreferenceEntry.getEmail());
        // Save changes
        return editor.commit();
    }

    /**
     * Retrieves the {@link SharedPreferenceEntry} containing the user's personal information from
     * {@link SharedPreferences}.
     *
     * @return the Retrieved {@link SharedPreferenceEntry}.
     */
    public SharedPreferenceEntry getPersonalInfo() {
        // Get data from sharedPreferences
        String name = mSharedPreferences.getString(KEY_NAME,"");
        Long dateOfBirthMseconds = mSharedPreferences.getLong(KEY_DOB, Calendar.getInstance().getTimeInMillis());

        Calendar dateOfBrth = Calendar.getInstance();
        dateOfBrth.setTimeInMillis(dateOfBirthMseconds);

        String email = mSharedPreferences.getString(KEY_EMAIL, "");

        return new SharedPreferenceEntry(name, dateOfBrth, email);
    }
}
