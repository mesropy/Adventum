package com.example.ourgame.login;

import android.os.Handler;
import android.text.TextUtils;

import com.example.ourgame.Statistics.WriteData;

/**
 * A class for a RegistrationInteractor, which determines whether the user can register a new
 * account or not
 */
class RegistrationInteractor {

    interface OnRegistrationFinishedListener {
        void onRegisterError();

        void onUsernameEmpty();

        void onPasswordEmpty();

        void onSuccess(String username);
    }

    /**
     * Method to check if the user's inputted username and password are eligible to create a new
     * account
     *
     * @param username   the desired username the user has provided
     * @param password   the password the user has provided
     * @param listener   the interface with methods that will be called depending on what the user
     *                   has inputted
     * @param dataWriter the data object containing accounts and logins, used to see if username is
     *                   already taken
     */
    void register(final String username, final String password, final OnRegistrationFinishedListener listener,
                         final WriteData dataWriter) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(username)) {
                    listener.onUsernameEmpty();
                } else if (TextUtils.isEmpty(password)) {
                    listener.onPasswordEmpty();
                } else if (dataWriter.checkUser(username)) {
                    // taken
                    listener.onRegisterError();
                } else {
                    dataWriter.addUser(username, password);
                    listener.onSuccess(username);
                }
            }
        };
        new Handler().postDelayed(runnable, 2000);
    }
}
