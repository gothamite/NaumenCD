package ru.naumen.naumencd.presentation.presenters;

import android.support.annotation.NonNull;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter  {
    private CompositeDisposable compositeDisposable =  new CompositeDisposable();

    protected void unsubscribeOnDestroy(@NonNull Disposable subscription) {
        compositeDisposable.add(subscription);
    }

    public void onDestroy() {
        compositeDisposable.clear();
    }
}
