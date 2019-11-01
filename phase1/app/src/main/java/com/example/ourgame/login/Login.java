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

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.ourgame.MainActivity;
import com.example.ourgame.R;



/**
 * The Login activity for a login page.
 */
public class Login extends AppCompatActivity implements LoginView {

    public final static String EXTRA_MESSAGE = "com.example.ourgame.MESSAGE";
    private ProgressBar progressBar;
    private EditText username;
    private EditText password;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = findViewById(R.id.progress);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        presenter = new LoginPresenter(this, new LoginInteractor(), new RegistrationInteractor());
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    /**
     * Display the loading image once the user has tapped login or register
     */
    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * Hide the loading image
     */
    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    /**
     * Display text telling the user that their login has failed
     */
    @Override
    public void setLoginError() {
        username.setError(getString(R.string.login_error));
    }

    /**
     * Display text informing the user that the username input is empty
     */
    @Override
    public void setUsernameEmpty() {
        username.setError(getString(R.string.username_empty));
    }

    /**
     * Display text telling the user that their desired username is already taken
     */
    @Override
    public void setRegisterError() {
        username.setError(getString(R.string.register_failed));
    }

    /**
     * Display text when the user leaves the password input empty
     */
    @Override
    public void setPasswordError() {
        password.setError(getString(R.string.password_empty));
    }

    /**
     * Method to navigate the user to the main activity page once they have successfully logged in
     *
     * @param username the user's username to be displayed on the main page
     */
    @Override
    public void navigateToHome(String username) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(EXTRA_MESSAGE, username);
        startActivity(intent);
        finish();
    }

    /**
     * Method to check whether the user's password and username is accepted
     *
     * @param view the button object that was tapped
     */
    public void validateCredentials(View view) {
        presenter.validateCredentials(username.getText().toString(), password.getText().toString());
    }

    /**
     * Method to create a new account for a new user
     *
     * @param view the button object that was tapped
     */
    public void validateRegistration(View view) {
        presenter.validateRegistration(username.getText().toString(), password.getText().toString());
    }
}
