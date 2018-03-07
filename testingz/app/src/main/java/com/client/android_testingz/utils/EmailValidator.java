package com.client.android_testingz.utils;

import android.text.Editable;
import android.text.TextWatcher;

import java.util.regex.Pattern;

/**
 *  An Email format validator for {@link android.widget.EditText}.
 */

public class EmailValidator implements TextWatcher {

    public static final Pattern EMAIL_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );

    private boolean mIsValid = false;

    public boolean isValid() {
        return mIsValid;
    }

    /**
     * Validates if the given input is a valid email address.
     *
     * @param email        The email to validate.
     * @return {@code true} if the input is a valid email. {@code false} otherwise.
     */
    public static boolean isValidEmail(CharSequence email){
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) { /* do nothing */ }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) { /* do nothing */ }

    @Override
    public void afterTextChanged(Editable editableText) {
        mIsValid = isValidEmail(editableText);
    }
}
