package ru.naumen.naumencd.di.module;


import android.content.Context;

import javax.inject.Scope;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import ru.naumen.naumencd.ComputerDatabaseService;
import ru.naumen.naumencd.app.ComputerDatabaseApi;
import ru.naumen.naumencd.utils.SharedPrefs;


@Module
public class NetworkModule {
    @Provides
    @Singleton
    public ComputerDatabaseService provideDatabaseService(ComputerDatabaseApi computerDatabaseApi) {
        return new ComputerDatabaseService(computerDatabaseApi);
    }

    @Provides
    @Singleton
    public ComputerDatabaseApi provideApi(Retrofit retrofit) {
        return retrofit.create(ComputerDatabaseApi.class);
    }
}