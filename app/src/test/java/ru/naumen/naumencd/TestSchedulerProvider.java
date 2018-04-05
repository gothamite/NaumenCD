package ru.naumen.naumencd;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import ru.naumen.naumencd.utils.SchedulerProvider;

public class TestSchedulerProvider extends SchedulerProvider {
    @Override
    public Scheduler schedulersIo() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler androidMainThread() {
        return Schedulers.trampoline();
    }
}
