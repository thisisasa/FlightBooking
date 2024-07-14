package com.asa.projectfightbooking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asa.projectfightbooking.R;
import com.asa.projectfightbooking.models.response.PostsItem;
import com.asa.projectfightbooking.models.response.products.ProductsItem;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>{
    private List<ProductsItem> productsItemList;
    private Context context;
    public ProductsAdapter(List<ProductsItem> productsItemList, Context context) {
        this.productsItemList = productsItemList;
        this.context = context;
    }


    @NonNull
    @Override
    public ProductsAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_product,null,false);
        return new ProductsAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ProductViewHolder holder, int position) {
        ProductsItem productsItem = productsItemList.get(position);
        if(productsItem.getTitle() != null){
            holder.title.setText(productsItem.getTitle().toString());
        }
        if(productsItem.getPrice() != null){
            holder.price.setText(productsItem.getPrice().toString());
        }
    }

    @Override
    public int getItemCount() {
        return productsItemList.size();
    }
    public static class ProductViewHolder extends RecyclerView.ViewHolder{
        TextView title, price;
        CardView productCard;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvProductTitle);
            price = itemView.findViewById(R.id.tvProductPrice);
            productCard = itemView.findViewById(R.id.productCard);
        }
    }
}
