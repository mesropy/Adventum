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

import android.content.Context;

import com.example.ourgame.Statistics.DataWriter;


/**
 * A class for a LoginPresenter, which determines if login credentials are correct
 */
public class LoginPresenter implements LoginInteractor.OnLoginFinishedListener, RegistrationInteractor.OnRegistrationFinishedListener {

    private LoginView loginView;
    private LoginInteractor loginInteractor;
    private DataWriter dataWriter;
    private RegistrationInteractor registrationInteractor;

    LoginPresenter(LoginView loginView, LoginInteractor loginInteractor, RegistrationInteractor registrationInteractor) {
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
        this.registrationInteractor = registrationInteractor;
        dataWriter = new DataWriter((Context) loginView);
    }

    /**
     * Method that checks and calls methods depending on what the user inputs as their username
     * and password (login successful, login failed, username empty, etc.)
     *
     * @param username the username the user has inputted
     * @param password the password the user has inputted
     */
    void validateCredentials(String username, String password) {
        if (loginView != null) {
            loginView.showProgress();
        }

        loginInteractor.login(username, password, this, dataWriter);
    }

    /**
     * Method that attempts to register a new account for the user, given that the username is not
     * already taken
     *
     * @param username the username the user inputted
     * @param password the password the user inputted
     */
    void validateRegistration(String username, String password) {
        if (loginView != null) {
            loginView.showProgress();
        }

        registrationInteractor.register(username, password, this, dataWriter);
    }

    void onDestroy() {
        loginView = null;
    }

    /**
     * Hides the loading image and displays text informing the user that they left the username
     * field blank
     */
    @Override
    public void onUsernameEmpty() {
        if (loginView != null) {
            loginView.setUsernameEmpty();
            loginView.hideProgress();
        }
    }

    /**
     * Hides the loading image and displays text informing the user that they left the password
     * field blank
     */
    @Override
    public void onPasswordEmpty() {
        if (loginView != null) {
            loginView.setPasswordError();
            loginView.hideProgress();
        }
    }

    /**
     * Hides the loading image and displays text telling the user that the login was unsuccessful
     */
    @Override
    public void onLoginError() {
        if (loginView != null) {
            loginView.setLoginError();
            loginView.hideProgress();
        }
    }

    /**
     * Hides the loading bar and displays text telling the user that registration was unsuccessful
     */
    @Override
    public void onRegisterError() {
        if (loginView != null) {
            loginView.setRegisterError();
            loginView.hideProgress();
        }
    }

    /**
     * If the login was successful, bring the user to the main home activity
     *
     * @param username the username of the user, to be displayed on the activity homepage
     */
    @Override
    public void onSuccess(String username) {
        if (loginView != null) {
            loginView.navigateToHome(username);
        }
    }
}
