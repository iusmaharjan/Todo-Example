package com.lftechnology.todo;

/**
 * Interactor that serves as a model to authenticate the users
 */
public interface LoginInteractor {

    /**
     * Attempts to login user
     *
     * @param email Email of the user
     * @param password Password
     * @param loginCallbacks Callbacks invoked on success, failure or cancellation of login
     */
    void login(String email, String password, LoginCallbacks loginCallbacks);

    /**
     * Callbacks that will be invoked on success, failure and cancellation of user login
     */
    interface LoginCallbacks {
        void onLoginSuccess();
        void onLoginFailure(String errorMessage);
        void onCancelled();
    }
}
