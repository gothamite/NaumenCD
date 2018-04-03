package ru.naumen.naumencd.di.module;


import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.naumen.naumencd.room.AppDatabase;

@Module
public class RoomModule {

    @Provides
    @Singleton
    public AppDatabase provideAppDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "Database").build();
    }
}
