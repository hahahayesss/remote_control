package com.r00t.remotecontrol.domain.interactor.single;

import com.r00t.remotecontrol.domain.KeyboardButton;
import com.r00t.remotecontrol.domain.executor.PostExecutionThread;
import com.r00t.remotecontrol.domain.executor.ThreadExecutor;
import com.r00t.remotecontrol.domain.interactor.UseCase;
import com.r00t.remotecontrol.domain.model.DoubleValueBundle;
import com.r00t.remotecontrol.domain.model.StatusResponse;
import com.r00t.remotecontrol.domain.repository.DataStore;

import javax.inject.Inject;

import io.reactivex.Observable;

public class PressKeyboardButtonUseCase extends UseCase<StatusResponse, DoubleValueBundle<String, KeyboardButton>> {
    private final DataStore dataStore;

    @Inject
    public PressKeyboardButtonUseCase(DataStore dataStore, ThreadExecutor te, PostExecutionThread pet) {
        super(te, pet);
        this.dataStore = dataStore;
    }

    @Override
    public Observable<StatusResponse> buildUseCaseObservable(DoubleValueBundle<String, KeyboardButton> bundle) {
        return dataStore.pressKeyboardButton(bundle.getFirst(), bundle.getSecond());
    }
}
