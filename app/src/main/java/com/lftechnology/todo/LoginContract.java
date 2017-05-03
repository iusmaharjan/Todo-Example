package com.lftechnology.todo;

/**
 * Contract for login process
 */
public interface LoginContract {

    /**
     * Interfaces for Login View
     */
    interface View {
        void onLoginSuccess();
        void displayErrorMessage(String errorMessage);
        void showLoginProgress(boolean show);
        void setEmailError(String errorMessage);
        void setPasswordError(String errorMessage);
    }

    /**
     * Interfaces for login presenter
     */
    interface Presenter {
        void login(String email, String password);
    }

}
