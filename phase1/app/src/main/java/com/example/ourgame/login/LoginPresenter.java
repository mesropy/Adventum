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

import com.example.ourgame.DataWriter;
import com.example.ourgame.WriteData;

public class LoginPresenter implements LoginInteractor.OnLoginFinishedListener, RegistrationInteractor.OnRegistrationFinishedListener{

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

    void validateCredentials(String username, String password) {
        if (loginView != null) {
            loginView.showProgress();
        }

        loginInteractor.login(username, password, this, dataWriter);
    }

    void validateRegistration(String username, String password) {
        if (loginView != null){
            loginView.showProgress();
        }

        registrationInteractor.register(username, password, this, dataWriter);
    }

    void onDestroy() {
        loginView = null;
    }

    @Override
    public void onUsernameEmpty() {
        if (loginView != null) {
            loginView.setUsernameEmpty();
            loginView.hideProgress();
        }
    }

    @Override
    public void onPasswordEmpty() {
        if (loginView != null) {
            loginView.setPasswordError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onLoginError() {
        if (loginView != null) {
            loginView.setLoginError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onRegisterError() {
        if (loginView != null) {
            loginView.setRegisterError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onSuccess(String username) {
        if (loginView != null) {
            loginView.navigateToHome(username);
        }
    }
}
