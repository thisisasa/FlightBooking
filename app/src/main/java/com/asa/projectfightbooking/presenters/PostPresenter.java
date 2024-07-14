package com.asa.projectfightbooking.presenters;

import com.asa.projectfightbooking.api.APIClient;
import com.asa.projectfightbooking.api.ApiInterface;
import com.asa.projectfightbooking.models.response.PostResponse;
import com.asa.projectfightbooking.models.response.PostsItem;
import com.asa.projectfightbooking.views.PostView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostPresenter {
    private final PostView view;
    private ApiInterface apiInterface;


    public PostPresenter(PostView view) {
        this.view = view;
        apiInterface = APIClient.getClient().create(ApiInterface.class);
    }
    public void getAllPosts(){
        view.onLoading();
        apiInterface.getAllPosts().enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
               view.onMidingLoading();
               if(response.isSuccessful() && null != response.body()){
                   view.onGetPostSuccess(response.body().getPosts());
               }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable throwable) {
                view.onMidingLoading();
            }
        });
    }
    public void getPostById(int id){
        view.onLoading();
        apiInterface.getPostById(id).enqueue(new Callback<PostsItem>() {
            @Override
            public void onResponse(Call<PostsItem> call, Response<PostsItem> response) {
                view.onMidingLoading();
                if(response.isSuccessful() && response.body() != null){
                    view.onGetPostByIdSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<PostsItem> call, Throwable throwable) {
                view.onMidingLoading();
                view.onSuccess(throwable.getLocalizedMessage());
            }
        });
    }
}
