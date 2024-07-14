package com.asa.projectfightbooking.app;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.asa.projectfightbooking.HomeActivity;
import com.asa.projectfightbooking.R;
import com.asa.projectfightbooking.data.local.UserSharePreference;
import com.asa.projectfightbooking.model.Product;

import java.util.ArrayList;
import java.util.List;

public class BaseActivityCategory extends AppCompatActivity {
    protected List<Product> getAllProductList() {
        List<Product> productList = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            if (i % 2 == 0) {
                productList.add(new Product(
                        i,
                        "Pre-Select Meal " ,
                        "",
                        R.drawable.preselectmeal,
                        12 + i
                ));
                productList.add(new Product(
                        i,
                        "Credit Card Officer " ,
                        "",
                        R.drawable.creditcard,
                        12 + i
                ));
                productList.add(new Product(
                        i,
                        "Cash Plus Mile On OA  " ,
                        "",
                        R.drawable.cashmile,
                        12 + i
                ));
            }
        }
        return productList;
    }
    protected List<Product> getAllProvinceList() {
        List<Product> productList = new ArrayList<>();
        for (int i = 1; i <= 25; i++) {
            if (i % 2 == 0) {
                productList.add(new Product(
                        i,
                        "Pnom Penh " ,
                        "",
                        R.drawable.pp,
                        12 + i
                ));
                productList.add(new Product(
                        i,
                        "Battambang " ,
                        "",
                        R.drawable.btb,
                        12 + i
                ));
            }
        }
        return productList;
    }
}
