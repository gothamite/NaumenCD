package ru.naumen.naumencd.utils;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SchedulerProvider {

    public Scheduler schedulersIo() {
        return Schedulers.io();
    }

    public Scheduler androidMainThread() {
        return AndroidSchedulers.mainThread();
    }
}
