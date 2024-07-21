package com.asa.projectfightbooking.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asa.projectfightbooking.GetStartActivity;
import com.asa.projectfightbooking.HomeActivity;
import com.asa.projectfightbooking.R;
import com.asa.projectfightbooking.adapter.ProductsAdapter;
import com.asa.projectfightbooking.app.BaseActivity;
import com.asa.projectfightbooking.models.response.products.ProductsItem;
import com.asa.projectfightbooking.presenters.ProductPresenter;
import com.asa.projectfightbooking.views.ProductView;

import java.util.List;

public class AllProductActivity extends BaseActivity implements ProductView {
    private ProductsAdapter productsAdapter;
    private ProgressBar progressBar;
    private RecyclerView recyclerViewProduct;
    private ProductPresenter productPresenter;
    private ImageView ivBackHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_all_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });
        productPresenter = new ProductPresenter(this);
        recyclerViewProduct = findViewById(R.id.recyclerViewProduct);
        progressBar = findViewById(R.id.progressBar);
        productPresenter.getAllProduct();

        ivBackHome = findViewById(R.id.ivBackHome);
        ivBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AllProductActivity.this, HomeActivity.class);
                startActivity(i);
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

    }

    @Override
    public void onSuccess(String message) {

    }

    @Override
    public void onGetProductSuccess(List<ProductsItem> productsItemList) {
        productsAdapter = new ProductsAdapter(productsItemList, this);
        recyclerViewProduct.setAdapter(productsAdapter);
        recyclerViewProduct.setLayoutManager(new GridLayoutManager(this,1));
    }

    @Override
    public void onGetProductByIdSuccess(ProductsItem data) {

    }
}