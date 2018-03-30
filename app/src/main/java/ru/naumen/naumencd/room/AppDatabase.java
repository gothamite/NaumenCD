package ru.naumen.naumencd.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ru.naumen.naumencd.models.Item;
import ru.naumen.naumencd.models.SimilarItem;

@Database(entities = {Item.class, SimilarItem.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ItemDao itemDao();

    public abstract SimilarItemDao similarItemDao();
}
