package com.r00t.remotecontrol.presentation.presenter.start;

import com.r00t.remotecontrol.presentation.view.StartView;

public interface StartViewPresenter {

    void setView(StartView view);

    void onNext(String serverUri);
}
