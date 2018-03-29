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

    @Query("SELECT * FROM SimilarItem WHERE id = :id")
    List<SimilarItem> getSimilarListById(int id);

    @Insert
    void insert(List<SimilarItem> similarItem);

    @Update
    void update(List<SimilarItem> similarItem);

    @Delete
    void delete(List<SimilarItem> similarItem);
}
