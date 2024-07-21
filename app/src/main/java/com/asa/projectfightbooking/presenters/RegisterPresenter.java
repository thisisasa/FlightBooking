package com.asa.projectfightbooking.presenters;

import com.asa.projectfightbooking.api.APIClient;
import com.asa.projectfightbooking.api.ApiInterface;
import com.asa.projectfightbooking.models.requests.ResgisterRequest;
import com.asa.projectfightbooking.models.response.BaseResponse;
import com.asa.projectfightbooking.views.RegisterView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter {
    private RegisterView view;
    private ApiInterface apiInterface;
    public RegisterPresenter(RegisterView view){
        this.view = view;
        apiInterface = APIClient.getClient().create(ApiInterface.class);
    }
    public void register(ResgisterRequest request){
        view.onLoading();
        apiInterface.registerUser(request).enqueue(new Callback<BaseResponse<String>>() {
            @Override
            public void onResponse(Call<BaseResponse<String>> call, Response<BaseResponse<String>> response) {
                view.onMidingLoading();
                if (response.isSuccessful()) {
                    view.onSuccess(response.body());
                } else {
                    view.onError("Invalid input ");
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<String>> call, Throwable throwable) {
                view.onMidingLoading();
                view.onError("Internal Server Error");
            }
        });
    }

}
