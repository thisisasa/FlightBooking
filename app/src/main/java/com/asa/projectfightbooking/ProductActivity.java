package com.asa.projectfightbooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asa.projectfightbooking.adapter.PostAdapter;
import com.asa.projectfightbooking.adapter.ProductsAdapter;
import com.asa.projectfightbooking.app.BaseActivity;
import com.asa.projectfightbooking.models.response.PostsItem;
import com.asa.projectfightbooking.models.response.products.ProductsItem;
import com.asa.projectfightbooking.presenters.ProductPresenter;
import com.asa.projectfightbooking.views.ProductView;

import java.util.List;

public class ProductActivity extends BaseActivity implements ProductView {
    private ProductsAdapter productsAdapter;
    private ProgressBar progressBar;
    private ProductPresenter productPresenter;
    private ImageView ivBack;
    private TextView title, price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        productPresenter = new ProductPresenter(this);
        productPresenter.getAllProduct();
        title = findViewById(R.id.tvProductTitle);
        price = findViewById(R.id.tvProductPrice);
        Intent intent = getIntent();
        if(intent.getStringExtra("Title") != null){
            title.setText(intent.getStringExtra("Title"));
        }
        if(intent.getStringExtra("Price") != null){
            price.setText(intent.getStringExtra("Price"));
        }
        initView();
    }
    private void initView(){
        ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
    public void onSuccess(String message) {
        showMessage(message);
    }
    @Override
    public void onGetProductSuccess(List<ProductsItem> productsItemList) {

//        recyclerViewProdcut.setAdapter(postAdapter);
//        recyclerViewPost.setLayoutManager(new GridLayoutManager(this,1));
    }
    @Override
    public void onGetProductByIdSuccess(ProductsItem data) {
        if(data != null){
            Intent intent = new Intent(this, ProductActivity.class);
            intent.putExtra("Title",data.getTitle());
            intent.putExtra("Price", data.getPrice().toString());
            startActivity(intent);
        }
    }
}