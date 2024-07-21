package com.asa.projectfightbooking.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.asa.projectfightbooking.HomeActivity;
import com.asa.projectfightbooking.R;
import com.asa.projectfightbooking.app.BaseActivity;
import com.asa.projectfightbooking.models.requests.LoginRequest;
import com.asa.projectfightbooking.presenters.LoginPresenter;
import com.asa.projectfightbooking.views.LoginView;

public class LoginActivity extends BaseActivity implements LoginView {
    private EditText userName, passWord;
    private Button btnLogin, btnRegiSter;
    private ProgressBar progressBar;
    private LoginPresenter loginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loginPresenter = new LoginPresenter( this);
        initView();
    }
    private void initView() {
        progressBar = findViewById(R.id.progressBar);
        userName = findViewById(R.id.etPhoneNumber);
        passWord = findViewById(R.id.etPassWord);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginRequest req = new LoginRequest();
                req.setUsername(userName.getText().toString().trim());
                req.setPassword(passWord.getText().toString().trim());
                loginPresenter.login(req);
//                if(userName && passWord == ){
//                    Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
//                    startActivity(i);
//                    finish();
//                }

            }

        });
        btnRegiSter = findViewById(R.id.btnRegiSter);
        btnRegiSter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onMidingLoading() {
        progressBar.setVisibility(View.GONE);
    }

    public void onError(String message) {
        showMessage(message);
    }

    @Override
    public void onSuccess(Object message) {
        showMessage("Login Success");
    }
}