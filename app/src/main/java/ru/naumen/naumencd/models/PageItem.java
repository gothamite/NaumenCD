package ru.naumen.naumencd.models;

import android.arch.persistence.room.Entity;

import com.google.gson.annotations.SerializedName;

@Entity
public class PageItem {

    @SerializedName("pageId")
    private Integer pageId;


}
