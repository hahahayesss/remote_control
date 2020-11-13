package com.r00t.remotecontrol.presenters.main;

import android.content.Intent;

import com.r00t.remotecontrol.models.ApiVoidResponse;
import com.r00t.remotecontrol.models.LocationVector;
import com.r00t.remotecontrol.models.TextData;
import com.r00t.remotecontrol.services.ApiController;
import com.r00t.remotecontrol.services.ControlListener;
import com.r00t.remotecontrol.utils.NotificationUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainView.Presenter {
    private MainView view;
    private NotificationUtils notificationUtils;

    public MainPresenter(MainView view) {
        this.view = view;

        view.initViews();
        view.initListeners();

        notificationUtils = new NotificationUtils(view.getContext());
        notificationUtils.startNotification();
    }

    @Override
    public void onMouseMove(LocationVector locationVector) {
        ApiController.getInstance().getService()
                .updateMouseLocation(locationVector)
                .enqueue(new Callback<ApiVoidResponse>() {
                    @Override
                    public void onResponse(Call<ApiVoidResponse> call, Response<ApiVoidResponse> response) {
//                                System.out.println(response.body().getStatus());
                    }

                    @Override
                    public void onFailure(Call<ApiVoidResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    @Override
    public void onMouseClicked(int buttonType) {
        ApiController.getInstance().getService()
                .clickMouseButton(buttonType)
                .enqueue(new Callback<ApiVoidResponse>() {
                    @Override
                    public void onResponse(Call<ApiVoidResponse> call, Response<ApiVoidResponse> response) {
//                        System.out.println(response.body().getStatus());
                    }

                    @Override
                    public void onFailure(Call<ApiVoidResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    @Override
    public void onSendKey(int keyCode) {
        view.getActivity().sendBroadcast(new Intent(view.getContext(), ControlListener.class));
        ApiController.getInstance().getService()
                .pressKey(keyCode)
                .enqueue(new Callback<ApiVoidResponse>() {
                    @Override
                    public void onResponse(Call<ApiVoidResponse> call, Response<ApiVoidResponse> response) {
//                        System.out.println(response.body().getStatus());
                    }

                    @Override
                    public void onFailure(Call<ApiVoidResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    @Override
    public void onWriteText(String text) {
        TextData textData = new TextData();
        textData.setData(text);

        ApiController.getInstance().getService()
                .writeText(textData)
                .enqueue(new Callback<ApiVoidResponse>() {
                    @Override
                    public void onResponse(Call<ApiVoidResponse> call, Response<ApiVoidResponse> response) {
//                        System.out.println(response.body().getStatus());
                    }

                    @Override
                    public void onFailure(Call<ApiVoidResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    @Override
    public void onDestroy() {
        notificationUtils.stopNotification();
    }
}
