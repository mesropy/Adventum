package com.example.ourgame.login;

import android.os.Handler;
import android.text.TextUtils;

public class RegistrationInteractor {

    interface OnRegistrationFinishedListener {
        void onRegisterError();

        void onUsernameError();

        void onSuccess();
    }

    public void register(final String username, final String password, final OnRegistrationFinishedListener listener) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(username)) {
                    listener.onUsernameError();
                    return;
                }
                else if (TextUtils.isEmpty(username)) {
                    // taken
                    listener.onRegisterError();
                    return;
                }
                listener.onSuccess();
            }
        };
        new Handler().postDelayed(runnable,2000);
    }
}
