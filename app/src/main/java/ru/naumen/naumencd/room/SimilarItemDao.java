package ru.naumen.naumencd.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ru.naumen.naumencd.models.SimilarItem;

@Dao
public interface SimilarItemDao {

    @Query("SELECT * FROM SimilarItem WHERE itemId = :itemId")
    List<SimilarItem> getSimilarListById(int itemId);

    @Insert
    void insert(SimilarItem similarItem);

    @Update
    void update(SimilarItem similarItem);

    @Delete
    void delete(SimilarItem similarItem);
}
