package com.r00t.remotecontrol.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.r00t.remotecontrol.R;
import com.r00t.remotecontrol.models.LocationVector;
import com.r00t.remotecontrol.presenters.main.MainPresenter;
import com.r00t.remotecontrol.presenters.main.MainView;

public class MainActivity extends AppCompatActivity implements MainView {
    private MainView.Presenter presenter;

    private ConstraintLayout mainView;
    private LinearLayout menuView;
    private EditText textEt;

    private int movementSize;
    private LocationVector lastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void initViews() {
        mainView = findViewById(R.id.mainView);
        menuView = findViewById(R.id.menuView);
        textEt = findViewById(R.id.textEt);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void initListeners() {
        mainView.setOnTouchListener((v, event) -> {
            int x = (int) event.getX();
            int y = (int) event.getY();

            if (event.getAction() == 0) {
                movementSize = 0;
                lastLocation = new LocationVector(x, y);
            } else if (event.getAction() == 2) {
                LocationVector currentLocation = new LocationVector(x, y);
                LocationVector vector = calculateMovement(currentLocation);
                presenter.onMouseMove(vector);

                movementSize++;
                lastLocation = currentLocation;
            } else if (event.getAction() == 1) {
                if (movementSize < 5)
                    presenter.onMouseClicked(0);
            }
            return true;
        });

        findViewById(R.id.leftButton).setOnClickListener(v ->
                presenter.onMouseClicked(0));
        findViewById(R.id.menuButton).setOnClickListener(v -> {
            if (menuView.getVisibility() == View.VISIBLE)
                menuView.setVisibility(View.GONE);
            else
                menuView.setVisibility(View.VISIBLE);
        });
        findViewById(R.id.rightButton).setOnClickListener(v ->
                presenter.onMouseClicked(1));

        findViewById(R.id.leftArrow).setOnClickListener(v ->
                presenter.onSendKey(37));
        findViewById(R.id.upArrow).setOnClickListener(v ->
                presenter.onSendKey(38));
        findViewById(R.id.rightArrow).setOnClickListener(v ->
                presenter.onSendKey(39));
        findViewById(R.id.downArrow).setOnClickListener(v ->
                presenter.onSendKey(40));

        findViewById(R.id.backspaceButton).setOnClickListener(v ->
                presenter.onSendKey('\b'));
        findViewById(R.id.enterButton).setOnClickListener(v ->
                presenter.onSendKey('\n'));
        findViewById(R.id.sendButton).setOnClickListener(v -> {
            presenter.onWriteText(textEt.getText().toString());
            textEt.setText("");
        });
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    private LocationVector calculateMovement(LocationVector currentLocation) {
        return new LocationVector(
                (lastLocation.getX() - currentLocation.getX()) * -1,
                (lastLocation.getY() - currentLocation.getY()) * -1
        );
    }
}