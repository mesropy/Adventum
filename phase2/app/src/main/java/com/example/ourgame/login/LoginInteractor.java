/*
 *
 *  * Copyright (C) 2018 Antonio Leiva Gordillo.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.example.ourgame.login;

import android.os.Handler;
import android.text.TextUtils;

import com.example.ourgame.Utilities.WriteData;

/**
 * A class for a LoginInteractor, which determines if the user can login or not
 */
class LoginInteractor {

    interface OnLoginFinishedListener {
        void onUsernameEmpty();

        void onPasswordEmpty();

        void onLoginError();

        void onSuccess(String username);
    }

    /**
     * Method to check if the user's username and password are correct to determine if they can
     * login to their account
     *
     * @param username the user's inputted username
     * @param password the user's inputted password
     * @param listener the interface with methods that will be called depending on what the user
     *                 has inputted
     * @param data     the data object containing information of accounts and their credentials
     */
    void login(final String username, final String password, final OnLoginFinishedListener listener,
                      final WriteData data) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(username)) {
                    listener.onUsernameEmpty();
                } else if (TextUtils.isEmpty(password)) {
                    listener.onPasswordEmpty();
                } else if (!data.checkUser(username)) {
                    listener.onLoginError();
                } else if (!data.getPassword().equals(password)) {
                    listener.onLoginError();
                } else {
                    listener.onSuccess(username);
                }
            }
        };
        new Handler().postDelayed(runnable, 2000);
    }
}
