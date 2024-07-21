package com.asa.projectfightbooking.api;

import com.asa.projectfightbooking.models.requests.LoginRequest;
import com.asa.projectfightbooking.models.requests.ResgisterRequest;
import com.asa.projectfightbooking.models.response.BaseResponse;
import com.asa.projectfightbooking.models.response.post.PostResponse;
import com.asa.projectfightbooking.models.response.post.PostsItem;
import com.asa.projectfightbooking.models.response.login.LoginResponse;
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
    @POST("/users/add")
    Call<BaseResponse<String>> registerUser(@Body ResgisterRequest request);
    @POST("/user/login")
    Call<LoginResponse> loginUser(@Body LoginRequest request);
}
