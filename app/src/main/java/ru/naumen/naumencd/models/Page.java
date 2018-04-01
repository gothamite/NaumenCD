package ru.naumen.naumencd.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Page implements PageEntity {

    @PrimaryKey
    @SerializedName("page")
    private Integer page;

    @SerializedName("total")
    private Integer total;

    @Override
    public Integer getPage() {
        return page;
    }

    @Override
    public void setPage(Integer page) {
        this.page = page;
    }

    @Override
    public Integer getTotal() {
        return total;
    }

    @Override
    public void setTotal(Integer total) {
        this.total = total;
    }
}
