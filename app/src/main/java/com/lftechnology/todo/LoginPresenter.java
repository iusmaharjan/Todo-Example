package com.lftechnology.todo;

import android.text.TextUtils;

/**
 * Presenter that handles login logic. It validates the login credentials and authenticates user
 * using the {@link LoginInteractor}.
 */
public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View loginView;
    private LoginInteractor loginInteractor;
    private LoginInteractor.LoginCallbacks loginCallbacks;

    public LoginPresenter(LoginInteractor loginInteractor) {
        this.loginInteractor = loginInteractor;
        this.loginCallbacks = new LoginCallbacksImpl();
    }

    public void setLoginView(LoginContract.View loginView) {
        this.loginView = loginView;
    }

    @Override
    public void login(String email, String password) {
//        if (mAuthTask != null) {
//            return;
//        }

        // Reset errors.
        loginView.setEmailError(null);
        loginView.setPasswordError(null);

        // Store values at the time of the login attempt.
//        String email = mEmailView.getText().toString();
//        String password = mPasswordView.getText().toString();

        boolean cancel = false;
//        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            loginView.setPasswordError("This password is too short");
//            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            loginView.setEmailError("This field is required");
//            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            loginView.setEmailError("This email address is invalid");
//            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
//            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            loginView.showLoginProgress(true);
//            mAuthTask = new UserLoginTask(email, password);
//            mAuthTask.execute((Void) null);
            loginInteractor.login(email, password, loginCallbacks);
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    public class LoginCallbacksImpl implements LoginInteractor.LoginCallbacks {
        @Override
        public void onLoginSuccess() {
            loginView.showLoginProgress(false);
            loginView.onLoginSuccess();
        }

        @Override
        public void onLoginFailure(String errorMessage) {
            loginView.showLoginProgress(false);
            loginView.displayErrorMessage(errorMessage);
        }

        @Override
        public void onCancelled() {
            loginView.showLoginProgress(false);
        }
    }
}
