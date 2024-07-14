package com.asa.projectfightbooking.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.asa.projectfightbooking.HomeActivity;
import com.asa.projectfightbooking.MainActivity;
import com.asa.projectfightbooking.NotificationActivity;
import com.asa.projectfightbooking.R;
import com.asa.projectfightbooking.TicketActivity;
import com.asa.projectfightbooking.data.local.UserSharePreference;

public class PostDetailActivity extends AppCompatActivity {
    private ImageView ivProfile, ivHome, ivTicket;
    private TextView title, body;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_post_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        title = findViewById(R.id.tvPostTt);
        body = findViewById(R.id.tvPostBd);
        Intent intent = getIntent();
        if(intent.getStringExtra("Title") != null){
            title.setText(intent.getStringExtra("Title"));
        }
        if(intent.getStringExtra("Body") != null){
            body.setText(intent.getStringExtra("Body"));
        }
        ivProfile = findViewById(R.id.ivProF);
        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserSharePreference.removeUserLogin(PostDetailActivity.this);
                Intent i = new Intent(PostDetailActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        ivHome = findViewById(R.id.ivHm);
        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserSharePreference.removeUserLogin(PostDetailActivity.this);
                Intent i = new Intent(PostDetailActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        });
        ivTicket = findViewById(R.id.ivTick);
        ivTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserSharePreference.removeUserLogin(PostDetailActivity.this);
                Intent i = new Intent(PostDetailActivity.this, TicketActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}