package ru.naumen.naumencd.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import ru.naumen.naumencd.models.Page;

@Dao
public interface PageDao {

    @Query("SELECT * FROM Page WHERE page = :page")
    Page getPage(int page);

    @Insert
    void insert(Page page);

    @Update
    void update(Page page);

    @Delete
    void delete(Page page);

}
