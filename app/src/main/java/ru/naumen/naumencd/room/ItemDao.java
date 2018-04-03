package ru.naumen.naumencd.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import ru.naumen.naumencd.models.dbdto.ItemDbDto;

@Dao
public interface ItemDao {

    @Query("SELECT * FROM ItemDbDto WHERE id = :id")
    ItemDbDto getId(Integer id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ItemDbDto item);

    @Update
    void update(ItemDbDto item);

    @Delete
    void delete(ItemDbDto item);
}
