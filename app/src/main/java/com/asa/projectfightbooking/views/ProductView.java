package com.asa.projectfightbooking.views;

import com.asa.projectfightbooking.models.response.products.ProductsItem;

import java.util.List;

public interface ProductView {
    void onLoading();
    void onMidingLoading();
    void onError(String message);
    void onSuccess(String message);
    void onGetProductSuccess(List<ProductsItem> productsItemsList);
    void onGetProductByIdSuccess(ProductsItem data);
}
