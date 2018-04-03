package ru.naumen.naumencd.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import ru.naumen.naumencd.models.dbdto.PageDbDto;

@Dao
public interface PageDao {

    @Query("SELECT * FROM PageDbDto WHERE page = :page")
    PageDbDto getPage(int page);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PageDbDto page);

    @Update
    void update(PageDbDto page);

    @Delete
    void delete(PageDbDto page);

}
