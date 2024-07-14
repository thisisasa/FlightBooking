package com.asa.projectfightbooking.api;

import com.asa.projectfightbooking.model.Product;
import com.asa.projectfightbooking.models.response.PostResponse;
import com.asa.projectfightbooking.models.response.PostsItem;
import com.asa.projectfightbooking.models.response.products.ProductResponse;
import com.asa.projectfightbooking.models.response.products.ProductsItem;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("/posts")
    Call<PostResponse> getAllPosts();
    @GET("/posts/{id}")
    Call<PostsItem> getPostById(@Path("id") int id);
    @GET("/products")
    Call<ProductResponse> getAllProducts();
    @GET("/products/{id}")
    Call<ProductsItem> getProductById(@Path("id") int id);
}
