package ru.naumen.naumencd.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import ru.naumen.naumencd.models.PageItem;

@Dao
public interface PageItemDao {
    @Query("SELECT * FROM PageItem WHERE pageId = :pageId")
    PageItem getPageItem(Integer pageId);
}
