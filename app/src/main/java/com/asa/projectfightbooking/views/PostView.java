package com.asa.projectfightbooking.views;

import com.asa.projectfightbooking.models.response.PostsItem;

import java.util.List;

public interface PostView {
    void onLoading();
    void onMidingLoading();
    void onError(String message);
    void onSuccess(String message);
    void onGetPostSuccess(List<PostsItem> postsItemList);
    void onGetPostByIdSuccess(PostsItem data);
}
