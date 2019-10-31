package com.example.ourgame.login;

import android.os.Handler;
import android.text.TextUtils;

import com.example.ourgame.WriteData;

public class RegistrationInteractor {

    interface OnRegistrationFinishedListener {
        void onRegisterError();

        void onUsernameEmpty();

        void onPasswordEmpty();

        void onSuccess(String username);
    }

    public void register(final String username, final String password, final OnRegistrationFinishedListener listener,
                         final WriteData dataWriter) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(username)) {
                    listener.onUsernameEmpty();
                }
                else if (TextUtils.isEmpty(password)) {
                    listener.onPasswordEmpty();
                }
                else if (dataWriter.checkUser(username)) {
                    // taken
                    listener.onRegisterError();
                }else {
                    dataWriter.addUser(username, password);
                    listener.onSuccess(username);
                }
            }
        };
        new Handler().postDelayed(runnable,2000);
    }
}
