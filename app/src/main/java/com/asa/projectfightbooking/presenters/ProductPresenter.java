package com.asa.projectfightbooking.presenters;

import com.asa.projectfightbooking.api.APIClient;
import com.asa.projectfightbooking.api.ApiInterface;
import com.asa.projectfightbooking.models.response.products.ProductResponse;
import com.asa.projectfightbooking.models.response.products.ProductsItem;
import com.asa.projectfightbooking.views.ProductView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductPresenter {
        private final ProductView view;
        private ApiInterface apiInterface;


        public ProductPresenter(ProductView view) {
            this.view = view;
            apiInterface = APIClient.getClient().create(ApiInterface.class);
        }
        public void getAllProduct(){
            view.onLoading();
            apiInterface.getAllProducts().enqueue(new Callback<ProductResponse>() {
                @Override
                public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                    view.onMidingLoading();
                    if(response.isSuccessful() && null != response.body()){
                        view.onGetProductSuccess(response.body().getProducts());
                    }
                }

                @Override
                public void onFailure(Call<ProductResponse> call, Throwable throwable) {
                    view.onMidingLoading();
                }
            });
        }
        public void getProductById(int id){
            view.onLoading();
            apiInterface.getProductById(id).enqueue(new Callback<ProductsItem>() {
                @Override
                public void onResponse(Call<ProductsItem> call, Response<ProductsItem> response) {
                    view.onMidingLoading();
                    if(response.isSuccessful() && response.body() != null){
                        view.onGetProductByIdSuccess(response.body());
                    }
                }

                @Override
                public void onFailure(Call<ProductsItem> call, Throwable throwable) {
                    view.onMidingLoading();
                    view.onSuccess(throwable.getLocalizedMessage());
                }
            });
        }
    }



