package ru.naumen.naumencd.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ru.naumen.naumencd.models.dbdto.PageDbDto;
import ru.naumen.naumencd.models.dbdto.PageItemDbDto;

@Dao
public interface PageItemDao {

    @Query("SELECT * FROM PageItemDbDto WHERE pageId = :pageId")
    List<PageItemDbDto> getPageItem(Integer pageId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PageItemDbDto pageItem);

    @Update
    void update(PageItemDbDto pageItem);

    @Delete
    void delete(PageItemDbDto pageItem);

}
