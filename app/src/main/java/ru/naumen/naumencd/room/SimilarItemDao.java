package ru.naumen.naumencd.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ru.naumen.naumencd.models.dbdto.SimilarItemDbDto;

@Dao
public interface SimilarItemDao {

    @Query("SELECT * FROM SimilarItemDbDto WHERE itemId = :itemId")
    List<SimilarItemDbDto> getSimilarListById(Integer itemId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SimilarItemDbDto similarItemDbDto);

    @Update
    void update(SimilarItemDbDto similarItemDbDto);

    @Delete
    void delete(SimilarItemDbDto similarItemDbDto);
}
