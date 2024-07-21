package com.asa.projectfightbooking.app;

import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.asa.projectfightbooking.HomeActivity;
import com.asa.projectfightbooking.MainActivity;
import com.asa.projectfightbooking.TicketActivity;
import com.asa.projectfightbooking.data.local.UserSharePreference;

public class BaseActivity extends AppCompatActivity {

//    protected final static String USERNAME = "admin";
//    protected final static String PASSWORD = "123";
    protected void showMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onResume(){
        super.onResume();
//        if(!UserSharePreference.getUsername(this).equals("")){
//            Intent intent = new Intent(this, HomeActivity.class);
//            startActivity(intent);
//            finish();
//        }

    }
}
