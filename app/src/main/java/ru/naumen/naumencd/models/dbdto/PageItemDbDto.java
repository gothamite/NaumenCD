package ru.naumen.naumencd.models.dbdto;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import ru.naumen.naumencd.models.dbdto.interfaces.PageItemEntity;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = PageDbDto.class,
        parentColumns = "page",
        childColumns = "pageId",
        onDelete = CASCADE))
public class PageItemDbDto implements PageItemEntity {

    @PrimaryKey
    @SerializedName("id")
    private Integer id;

    @SerializedName("pageId")
    private Integer pageId;

    @SerializedName("name")
    private String name;

    @SerializedName("company_name")
    private String companyName;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }
}
