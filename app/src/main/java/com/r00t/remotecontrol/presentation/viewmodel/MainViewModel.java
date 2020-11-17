package com.r00t.remotecontrol.presentation.viewmodel;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.r00t.remotecontrol.domain.interactor.empty.SaveServerUriUseCase;
import com.r00t.remotecontrol.domain.interactor.single.GetServerUriUserCase;
import com.r00t.remotecontrol.presentation.DefaultObserver;

import io.reactivex.annotations.NonNull;

public class MainViewModel extends ViewModel {
    private final GetServerUriUserCase getServerUri;
    private final SaveServerUriUseCase saveServerUri;
    private final SavedStateHandle savedStateHandle;

    private final MutableLiveData<String> serverUri;

    @ViewModelInject
    public MainViewModel(GetServerUriUserCase getServerUri, SaveServerUriUseCase saveServerUri, @Assisted SavedStateHandle savedStateHandle) {
        this.getServerUri = getServerUri;
        this.saveServerUri = saveServerUri;
        this.savedStateHandle = savedStateHandle;

        this.serverUri = new MutableLiveData<>();

        init();
    }

    private void init() {
        getServerUri.execute(new DefaultObserver<String>() {
            @Override
            public void onNext(@NonNull String s) {
                serverUri.setValue(s);
            }
        }, null);
    }

    public LiveData<String> getServerUri() {
        return serverUri;
    }

    public void setServerUri(String serverUri) {
        saveServerUri.execute(new DefaultObserver<Void>() {
            @Override
            public void onNext(@NonNull Void aVoid) {
                MainViewModel.this.serverUri.setValue(serverUri);
            }
        }, serverUri);
    }
}
