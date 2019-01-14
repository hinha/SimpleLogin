package com.example.hinha.mvplogin.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hinha.mvplogin.Presenter.MainContract;
import com.example.hinha.mvplogin.Presenter.MainPresenter;
import com.example.hinha.mvplogin.R;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity implements MainContract.View {

    private MainPresenter mPresenter;
    private EditText txtEmail, txtPassword;
    private Button btnLogin, btnSignup;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

        mPresenter = new MainPresenter(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.doLogin(txtEmail.getText().toString(), txtPassword.getText().toString());
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void loginSuccess(String title) {
        Toasty.success(this, title, Toast.LENGTH_SHORT).show();
        intent = new Intent(LoginActivity.this, HomeActivity.class);
        String email = txtEmail.getText().toString();
        intent.putExtra("email", email.substring(0, 7));
        startActivity(intent);
        finish();
    }

    @Override
    public void logout(String title) {
        btnLogin.setText(title);
    }

    @Override
    public void loginError(String msg) {
        Toasty.error(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void btnSuccess(String title) {
        btnLogin.setText(title);
        txtEmail.setText("");
        txtPassword.setText("");
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }
}
