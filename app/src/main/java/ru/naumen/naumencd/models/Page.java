package ru.naumen.naumencd.models;

import android.arch.persistence.room.Entity;

import com.google.gson.annotations.SerializedName;

@Entity
public class Page {

    @SerializedName("page")
    private Integer page;

    @SerializedName("total")
    private Integer total;
}
