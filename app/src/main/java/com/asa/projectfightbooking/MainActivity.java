package com.asa.projectfightbooking;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.asa.projectfightbooking.app.BaseActivity;
import com.asa.projectfightbooking.data.local.UserSharePreference;

public class MainActivity extends BaseActivity {
    private Button btnRegister, btnHome;
    private TextView tvSkip;
    private EditText etUserLogin, etPassLogin;
    private Button btnLogin,btnOpenNestedRecyclerView;
    ActivityResultLauncher<Intent> activityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.d(TAG, "onActivityResult: ");

                    if(result.getResultCode() == 78) {
                        Intent intent = result.getData();
                        if (intent != null) {
                            String data = intent.getStringExtra("result");
                            etUserLogin.setText(data);
                        }
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

    }
    public void initView() {
        etUserLogin = findViewById(R.id.etUserLogin);
        etPassLogin = findViewById(R.id.etPassLogin);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUserLogin.getText().toString().trim();
                String password = etPassLogin.getText().toString().trim();
                if (username.isEmpty()) {
                    showMessage("Please enter username");
                    return;
                }
                if (password.isEmpty()) {
                    showMessage("Please enter password");
                    return;
                }
                if (!username.equals(USERNAME)) {
                    showMessage("Your username incorrect!");
                    return;
                }
                if (!password.equals(PASSWORD)) {
                    showMessage("Your password incorrect!");
                    return;
                }
                UserSharePreference.saveUserLogin(
                        MainActivity.this,
                        username,
                        password
                );
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
                showMessage("Login Success!");
            }
        });
    }
}