package com.asa.projectfightbooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asa.projectfightbooking.adapter.CategoryAdapter;
import com.asa.projectfightbooking.app.BaseActivityCategory;
import com.asa.projectfightbooking.data.local.UserSharePreference;
import com.asa.projectfightbooking.model.Category;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivityCategory {
    private RecyclerView rvCategory;
    private CategoryAdapter categoryAdapter;
    private List<Category> categoryList;

    private TextView tvUsername, tvSeeAll;
    private ImageView ivProfile, ivTicket, ivNotification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.homePage), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
        //get category
        getCategories();

        //show username on screen
        tvUsername = findViewById(R.id.tvUsername);
        if(!UserSharePreference.getUsername(this).equals("")){
            tvUsername.setText(UserSharePreference.getUsername(this));
        }
        //logout
        ivProfile = findViewById(R.id.ivProF);
        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserSharePreference.removeUserLogin(HomeActivity.this);
                Intent i = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        ivTicket = findViewById(R.id.ivTick);
        ivTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserSharePreference.removeUserLogin(HomeActivity.this);
                Intent i = new Intent(HomeActivity.this, TicketActivity.class);
                startActivity(i);
                finish();
            }
        });
//
        ivNotification = findViewById(R.id.ivNotifi);
        ivNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserSharePreference.removeUserLogin(HomeActivity.this);
                Intent i = new Intent(HomeActivity.this, NotificationActivity.class);
                startActivity(i);
                finish();
            }
        });
        tvSeeAll = findViewById(R.id.tvSeeAll);
        tvSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ProductActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initView(){
        rvCategory = findViewById(R.id.rvCategory);
//        ivBack = findViewById(R.id.ivBack);
//        ivBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

    }

    private void getCategories(){
        categoryList = new ArrayList<>();
        Category softDrink = new Category();
        softDrink.setId(1);
        softDrink.setName("Suggested for you");
        softDrink.setProductList(getAllProvinceList());
        categoryList.add(softDrink);

        Category beer = new Category();
        beer.setId(2);
        beer.setName("Royal Orchid Plus");
        beer.setProductList(getAllProductList());
        categoryList.add(beer);

        categoryAdapter = new CategoryAdapter(this, categoryList);
        rvCategory.setAdapter(categoryAdapter);
        rvCategory.setLayoutManager(new GridLayoutManager(this,1));
    }

}