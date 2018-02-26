package ru.naumen.naumencd.di.module;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import ru.naumen.naumencd.ComputerDatabaseService;
import ru.naumen.naumencd.app.ComputerDatabaseApi;


@Module
public class NetworkModule {
    @Provides
    @Singleton
    public ComputerDatabaseService provideGithubService(ComputerDatabaseApi authApi) {
        return new ComputerDatabaseService(authApi);
    }

    @Provides
    @Singleton
    public ComputerDatabaseApi provideAuthApi(Retrofit retrofit) {
        return retrofit.create(ComputerDatabaseApi.class);
    }
}