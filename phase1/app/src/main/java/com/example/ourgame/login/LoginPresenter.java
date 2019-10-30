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

public class LoginPresenter implements LoginInteractor.OnLoginFinishedListener, RegistrationInteractor.OnRegistrationFinishedListener{

    private LoginView loginView;
    private LoginInteractor loginInteractor;
    private RegistrationInteractor registrationInteractor;

    LoginPresenter(LoginView loginView, LoginInteractor loginInteractor, RegistrationInteractor registrationInteractor) {
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
        this.registrationInteractor = registrationInteractor;
    }

    void validateCredentials(String username, String password) {
        if (loginView != null) {
            loginView.showProgress();
        }

        loginInteractor.login(username, password, this);
    }

    void validateRegistration(String username, String password) {
        if (loginView != null){
            loginView.showProgress();
        }

        registrationInteractor.register(username, password, this);
    }

    void onDestroy() {
        loginView = null;
    }

    @Override
    public void onUsernameError() {
        if (loginView != null) {
            loginView.setUsernameError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onPasswordError() {
        if (loginView != null) {
            loginView.setPasswordError();
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
    public void onSuccess() {
        if (loginView != null) {
            loginView.navigateToHome();
        }
    }
}