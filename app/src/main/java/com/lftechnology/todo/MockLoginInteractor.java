package com.lftechnology.todo;

import android.os.AsyncTask;

/**
 * Implemntation of {@link LoginInteractor} that authenticates existing user
 */
public class MockLoginInteractor implements LoginInteractor {

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    /**
     * A reference to callbacks that will be invoked when the login process completes
     */
    private LoginCallbacks loginCallbacks;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };

    @Override
    public void login(String email, String password, LoginCallbacks loginCallbacks) {
        this.loginCallbacks = loginCallbacks;
        this.mAuthTask = new UserLoginTask(email, password);
        this.mAuthTask.execute();
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }

            // TODO: register the new account here.
            // return true;
            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;

            // TODO: Hide progress in presenter
            // showProgress(false);

            if (success) {
                loginCallbacks.onLoginSuccess();
                // finish();
            } else {
                loginCallbacks.onLoginFailure("The password is incorrect");
                // mPasswordView.setError(getString(R.string.error_incorrect_password));
                // mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            loginCallbacks.onCancelled();
            // showProgress(false);
        }
    }

}
