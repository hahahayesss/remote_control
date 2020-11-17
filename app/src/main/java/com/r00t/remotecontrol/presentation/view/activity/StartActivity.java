package com.r00t.remotecontrol.presentation.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.r00t.remotecontrol.R;
import com.r00t.remotecontrol.data.log.LogEntity;
import com.r00t.remotecontrol.presentation.adapter.LogsAdapter;
import com.r00t.remotecontrol.presentation.presenter.start.StartViewPresenter;
import com.r00t.remotecontrol.presentation.view.StartView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class StartActivity extends BaseActivity implements StartView {
    @Inject
    protected StartViewPresenter presenter;
    @Inject
    protected LogsAdapter logsAdapter;

    @BindViews({R.id.server_ip_first_edit_text, R.id.server_ip_second_edit_text, R.id.server_ip_third_edit_text, R.id.server_ip_fourth_edit_text})
    protected List<EditText> serverAddressEditTexts;
    @BindView(R.id.server_port_edit_text)
    protected EditText serverPortEditText;
    @BindView(R.id.logs_recycler_view)
    protected RecyclerView logsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);

        presenter.setView(this);

        logsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        logsRecyclerView.setAdapter(logsAdapter);
    }

    @Override
    public void setServerAddress(String address) {
        String[] arr = address.split("\\.");
        for (int x = 0; x < serverAddressEditTexts.size(); x++)
            serverAddressEditTexts.get(x).setText(arr[x]);
    }

    @Override
    public void setServerPort(String port) {
        serverPortEditText.setText(port);
    }

    @Override
    public void renderLogsEntities(List<LogEntity> logEntities) {
        logsAdapter.setLogEntityList(logEntities);
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    public AppCompatActivity activity() {
        return this;
    }

    @OnClick(R.id.next_button)
    protected void onNextButtonClicked(View v) {
        if (!checkFields()) return;

        String serverUri = serverAddressEditTexts.get(0).getText() + "." +
                serverAddressEditTexts.get(1).getText() + "." +
                serverAddressEditTexts.get(2).getText() + "." +
                serverAddressEditTexts.get(3).getText() + ":" +
                serverPortEditText.getText();

        presenter.onNext(serverUri);
    }

    private boolean checkFields() {
        for (EditText e : serverAddressEditTexts)
            if (TextUtils.isEmpty(e.getText()))
                return false;
            else if (Integer.parseInt(e.getText().toString()) < 0)
                return false;
            else if (Integer.parseInt(e.getText().toString()) > 254)
                return false;

        if (TextUtils.isEmpty(serverPortEditText.getText()))
            return false;
        else return Integer.parseInt(serverPortEditText.getText().toString()) >= 0;
    }
}