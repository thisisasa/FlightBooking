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

import com.asa.projectfightbooking.R;
import com.asa.projectfightbooking.app.BaseActivity;
import com.asa.projectfightbooking.models.requests.ResgisterRequest;
import com.asa.projectfightbooking.presenters.RegisterPresenter;
import com.asa.projectfightbooking.views.RegisterView;

public class RegisterActivity extends BaseActivity implements RegisterView {
    private EditText firstName, lastName, userName, email, phone, password;
    private Button btnRegister, btnBack;
    private ProgressBar progressBar;
    private RegisterPresenter registerPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        registerPresenter = new RegisterPresenter(this);
        initView();
    }
    private void initView(){
        progressBar = findViewById(R.id.progressBar);
        firstName = findViewById(R.id.etFirstName);
        lastName = findViewById(R.id.etLastName);
        userName = findViewById(R.id.etUserName);
        phone = findViewById(R.id.etPhone);
        email = findViewById(R.id.etEmail);
        firstName = findViewById(R.id.etFirstName);
        password = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResgisterRequest request = new ResgisterRequest();
                request.setFirstName(firstName.getText().toString().trim());
                request.setLastName(lastName.getText().toString().trim());
                request.setUsername(userName.getText().toString().trim());
                request.setPhone(phone.getText().toString().trim());
                request.setEmail(email.getText().toString().trim());
                request.setPassword(password.getText().toString().trim());
                registerPresenter.register(request);
            }
        });
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
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

    @Override
    public void onError(String message) {
        showMessage(message);
    }

    @Override
    public void onSuccess(Object message) {
        showMessage("Register Success");
    }
}