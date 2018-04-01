package ru.naumen.naumencd.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


import com.google.gson.annotations.SerializedName;


@Entity
public class SimilarItem implements SimilarItemEntity {

    @SerializedName("id")
    @PrimaryKey
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("itemId")
    private Integer itemId;

    @Override
    public Integer getItemId() {
        return itemId;
    }

    @Override
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
