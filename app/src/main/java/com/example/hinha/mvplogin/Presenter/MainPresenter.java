package com.example.hinha.mvplogin.Presenter;

import android.content.Intent;

import com.example.hinha.mvplogin.Model.LoginManager;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;
    private boolean isLoggedIn;
    private LoginManager mLoginManager;
    private Intent intent;

    public MainPresenter(MainContract.View mView) {
        this.mView = mView;
        this.mLoginManager = LoginManager.getsInstance();
    }

    @Override
    public void doLogin(String email, String password) {
        int loginCode = mLoginManager.validate(email, password);
        if (!isLoggedIn) {
            switch (loginCode) {
                case 0: mView.loginError("Harus memasukkan email anda"); return;
                case 1: mView.loginError("Harus memasukkan email dan password dengan benar"); return;
                case 2: mView.loginError("Password harus lebih dari 6");return;
                default:
                    isLoggedIn = true;
                    mView.loginSuccess("Login berhasil");
                    mView.btnSuccess("Logout");
                    return;
            }
        } else {
            isLoggedIn = false;
            mView.logout("Login");
            mView.loginSuccess("Logout berhasil");
        }
    }

    public void detachView() {
        mView = null;
    }
}
