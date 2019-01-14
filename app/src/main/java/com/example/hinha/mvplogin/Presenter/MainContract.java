package com.example.hinha.mvplogin.Presenter;

public interface MainContract {

    interface View {
        void loginSuccess(String msg);
        void logout(String title);
        void loginError(String msg);
        void btnSuccess(String title);
    }

    interface Presenter {
        void doLogin(String email, String password);
    }

}
