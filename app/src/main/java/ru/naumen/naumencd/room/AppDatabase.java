package ru.naumen.naumencd.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ru.naumen.naumencd.models.dbdto.CompanyDbDto;
import ru.naumen.naumencd.models.dbdto.ItemDbDto;
import ru.naumen.naumencd.models.dbdto.PageDbDto;
import ru.naumen.naumencd.models.dbdto.PageItemDbDto;
import ru.naumen.naumencd.models.dbdto.SimilarItemDbDto;

@Database(entities = {ItemDbDto.class, SimilarItemDbDto.class, PageDbDto.class, PageItemDbDto.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ItemDao itemDao();

    public abstract SimilarItemDao similarItemDao();

    public abstract PageDao pageDao();

    public abstract PageItemDao pageItemDao();
}
