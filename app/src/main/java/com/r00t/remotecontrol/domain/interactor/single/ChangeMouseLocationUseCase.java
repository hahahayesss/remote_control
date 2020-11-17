package com.r00t.remotecontrol.domain.interactor.single;

import com.r00t.remotecontrol.domain.executor.PostExecutionThread;
import com.r00t.remotecontrol.domain.executor.ThreadExecutor;
import com.r00t.remotecontrol.domain.interactor.UseCase;
import com.r00t.remotecontrol.domain.model.DoubleValueBundle;
import com.r00t.remotecontrol.domain.model.LocationVector;
import com.r00t.remotecontrol.domain.model.StatusResponse;
import com.r00t.remotecontrol.domain.repository.DataStore;

import javax.inject.Inject;

import io.reactivex.Observable;

public class ChangeMouseLocationUseCase extends UseCase<StatusResponse, DoubleValueBundle<String, LocationVector>> {
    private final DataStore dataStore;

    @Inject
    public ChangeMouseLocationUseCase(DataStore dataStore, ThreadExecutor te, PostExecutionThread pet) {
        super(te, pet);
        this.dataStore = dataStore;
    }

    @Override
    public Observable<StatusResponse> buildUseCaseObservable(DoubleValueBundle<String, LocationVector> bundle) {
        return dataStore.changeMouseLocation(bundle.getFirst(), bundle.getSecond());
    }
}
