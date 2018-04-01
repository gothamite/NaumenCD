package ru.naumen.naumencd.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class PageItem implements PageItemEntity {

    @SerializedName("pageId")
    private Integer pageId;

    @PrimaryKey
    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

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

    @Override
    public Integer getPageId() {
        return pageId;
    }

    @Override
    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }
}
