package com.r00t.remotecontrol.domain.interactor.single;

import com.r00t.remotecontrol.domain.MouseButton;
import com.r00t.remotecontrol.domain.executor.PostExecutionThread;
import com.r00t.remotecontrol.domain.executor.ThreadExecutor;
import com.r00t.remotecontrol.domain.interactor.UseCase;
import com.r00t.remotecontrol.domain.model.DoubleValueBundle;
import com.r00t.remotecontrol.domain.model.StatusResponse;
import com.r00t.remotecontrol.domain.repository.DataStore;

import javax.inject.Inject;

import io.reactivex.Observable;

public class PressMouseButtonUseCase extends UseCase<StatusResponse, DoubleValueBundle<String, MouseButton>> {
    private final DataStore dataStore;

    @Inject
    public PressMouseButtonUseCase(DataStore dataStore, ThreadExecutor te, PostExecutionThread pet) {
        super(te, pet);
        this.dataStore = dataStore;
    }

    @Override
    public Observable<StatusResponse> buildUseCaseObservable(DoubleValueBundle<String, MouseButton> bundle) {
        return dataStore.pressMouseButton(bundle.getFirst(), bundle.getSecond());
    }
}
