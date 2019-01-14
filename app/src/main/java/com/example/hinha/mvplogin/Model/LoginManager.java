package com.example.hinha.mvplogin.Model;

import android.text.TextUtils;
import android.util.Patterns;


public class LoginManager {

    private static LoginManager sInstance;

    public static LoginManager getsInstance() {
        if (sInstance == null) {
            sInstance = new LoginManager();
        }
        return sInstance;
    }

    public int validate(String email, String password) {
        int isTrue = 1;
        if (TextUtils.isEmpty(email))
            return 0;
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return 1;
        else if (password.length() <= 6)
            return 2;
        else
            return isTrue = isValidEmail(email) && isPasswordCorrect(password) ? -1 : 1;
    }

    private boolean isValidEmail(String email) {
        return email.equalsIgnoreCase("gtester@gmail.com");
    }

    private boolean isPasswordCorrect(String password) {
        return password.equalsIgnoreCase("qwerty123");
    }

}
