package com.asa.projectfightbooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.asa.projectfightbooking.data.local.UserSharePreference;
import com.asa.projectfightbooking.ui.LoginActivity;

public class TicketActivity extends AppCompatActivity {
    private ImageView ivProfile, ivHome, ivNotification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ticket);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ivProfile = findViewById(R.id.ivProfile);
        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserSharePreference.removeUserLogin(TicketActivity.this);
                Intent i = new Intent(TicketActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        ivHome = findViewById(R.id.ivHm);
        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserSharePreference.removeUserLogin(TicketActivity.this);
                Intent i = new Intent(TicketActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        });
        ivNotification = findViewById(R.id.ivNotication);
        ivNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserSharePreference.removeUserLogin(TicketActivity.this);
                Intent i = new Intent(TicketActivity.this, NotificationActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
    @Override
    protected void onResume(){
        super.onResume();
    }
}