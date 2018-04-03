package ru.naumen.naumencd.models.dbdto;

import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import ru.naumen.naumencd.models.dbdto.interfaces.SimilarItemEntity;


@Entity(primaryKeys = {"id", "itemId"})
public class SimilarItemDbDto implements SimilarItemEntity {

    @SerializedName("id")
    @NonNull
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("itemId")
    @NonNull
    private Integer itemId;

    @Override
    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
