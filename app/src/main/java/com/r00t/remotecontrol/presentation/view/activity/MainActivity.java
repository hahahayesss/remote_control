package com.r00t.remotecontrol.presentation.view.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.r00t.remotecontrol.R;
import com.r00t.remotecontrol.domain.KeyboardButton;
import com.r00t.remotecontrol.domain.MouseButton;
import com.r00t.remotecontrol.domain.model.LocationVector;
import com.r00t.remotecontrol.presentation.presenter.main.MainViewPresenter;
import com.r00t.remotecontrol.presentation.view.MainView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends BaseActivity implements MainView {
    @Inject
    protected MainViewPresenter presenter;

    @BindView(R.id.mainView)
    protected ConstraintLayout mainView;
    @BindView(R.id.menuView)
    protected LinearLayout menuView;
    @BindView(R.id.textEt)
    protected EditText textEditText;

    private int movementSize;
    private LocationVector lastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter.setView(this);

        initListeners();
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    public AppCompatActivity activity() {
        return this;
    }

    @SuppressLint("ClickableViewAccessibility")
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
                if (movementSize < 7)
                    presenter.onMouseClicked(MouseButton.LEFT);
            }
            return true;
        });
    }

    @OnClick({R.id.leftButton, R.id.rightButton, R.id.menuButton})
    protected void onControlButtonClicked(View v) {
        switch (v.getId()) {
            case R.id.leftButton:
                presenter.onMouseClicked(MouseButton.LEFT);
                break;
            case R.id.rightButton:
                presenter.onMouseClicked(MouseButton.RIGHT);
                break;
            case R.id.menuButton:
                if (menuView.getVisibility() == View.VISIBLE) {
                    menuView.setVisibility(View.GONE);
                } else {
                    menuView.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @OnClick({R.id.leftArrow, R.id.upArrow, R.id.rightArrow, R.id.downArrow})
    protected void onArrowButtonClicked(View v) {
        switch (v.getId()) {
            case R.id.leftArrow:
                presenter.onKeyPressed(KeyboardButton.LEFT_ARROW);
                break;
            case R.id.upArrow:
                presenter.onKeyPressed(KeyboardButton.UP_ARROW);
                break;
            case R.id.rightArrow:
                presenter.onKeyPressed(KeyboardButton.RIGHT_ARROW);
                break;
            case R.id.downArrow:
                presenter.onKeyPressed(KeyboardButton.DOWN_ARROW);
                break;
        }
    }

    @OnClick({R.id.backspaceButton, R.id.enterButton, R.id.sendButton})
    protected void onKeyboardButtonClicked(View v) {
        switch (v.getId()) {
            case R.id.backspaceButton:
                presenter.onKeyPressed(KeyboardButton.BACKSPACE);
                break;
            case R.id.enterButton:
                presenter.onKeyPressed(KeyboardButton.ENTER);
                break;
            case R.id.sendButton:
                presenter.onWriteText(textEditText.getText().toString());
                textEditText.setText("");
                break;
        }
    }

    private LocationVector calculateMovement(LocationVector currentLocation) {
        return new LocationVector(
                (lastLocation.getX() - currentLocation.getX()) * -1,
                (lastLocation.getY() - currentLocation.getY()) * -1
        );
    }
}