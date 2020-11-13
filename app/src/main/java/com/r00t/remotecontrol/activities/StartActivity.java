package com.r00t.remotecontrol.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.r00t.remotecontrol.R;
import com.r00t.remotecontrol.services.ApiController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartActivity extends AppCompatActivity {
    private List<EditText> serverIpETs;
    private EditText serverPortET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        serverIpETs = Arrays.asList(
                findViewById(R.id.serverIpFirstET), findViewById(R.id.serverIpSecondET),
                findViewById(R.id.serverIpThirdET), findViewById(R.id.serverIpFourthET));
        serverPortET = findViewById(R.id.serverPortET);

        findViewById(R.id.nextButton).setOnClickListener(v -> {
            if (checkFields()) {
                System.out.println("error");
                return;
            }

            ApiController.createInstance(getServerURL());
            ApiController.getInstance()
                    .getService()
                    .temp().enqueue(new Callback<Map<String, Object>>() {
                @Override
                public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                    Map<String, Object> map = response.body();

                    try {
                        Class c = Class.forName((String) map.get("test"));
                        System.out.println(c);

                        System.out.println(LinearLayout.class);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<Map<String, Object>> call, Throwable t) {

                }
            });

//            startActivity(new Intent(StartActivity.this, MainActivity.class));
        });
    }

    private boolean checkFields() {
        for (EditText serverIpET : serverIpETs)
            if (TextUtils.isEmpty(serverIpET.getText()))
                return true;
            else if (Integer.parseInt(serverIpET.getText().toString()) < 0)
                return true;
            else if (Integer.parseInt(serverIpET.getText().toString()) > 254)
                return true;

        if (TextUtils.isEmpty(serverPortET.getText()))
            return true;
        else if (Integer.parseInt(serverPortET.getText().toString()) < 0)
            return true;

        return false;
    }

    private String getServerURL() {
        StringBuilder url = new StringBuilder("http://");
        url.append(serverIpETs.get(0).getText().toString());
        url.append(".");
        url.append(serverIpETs.get(1).getText().toString());
        url.append(".");
        url.append(serverIpETs.get(2).getText().toString());
        url.append(".");
        url.append(serverIpETs.get(3).getText().toString());
        url.append(":");
        url.append(serverPortET.getText().toString());
        return url.toString();
    }
}