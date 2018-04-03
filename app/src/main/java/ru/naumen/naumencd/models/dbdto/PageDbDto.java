package ru.naumen.naumencd.models.dbdto;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class PageDbDto {

    @SerializedName("page")
    @PrimaryKey
    private Integer page;

    @SerializedName("total")
    private Integer total;


    public Integer getPage() {
        return page;
    }


    public void setPage(Integer page) {
        this.page = page;
    }


    public Integer getTotal() {
        return total;
    }


    public void setTotal(Integer total) {
        this.total = total;
    }
}
