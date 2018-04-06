package ru.naumen.naumencd.models.dbdto;


import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import ru.naumen.naumencd.models.dbdto.interfaces.ItemEntity;
import ru.naumen.naumencd.models.dbdto.interfaces.PageItemEntity;

@Entity
public class ItemDbDto implements ItemEntity, PageItemEntity {

    @SerializedName("id")
    @PrimaryKey
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("pageId")
    private Integer pageId;

    @SerializedName("introduced")
    private String introduced;

    @SerializedName("discounted")
    private String discounted;

    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("company")
    @Embedded(prefix = "Company")
    private CompanyDbDto company;

    @SerializedName("description")
    private String description;


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

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getIntroduced() {
        return introduced;
    }

    public void setIntroduced(String introduced) {
        this.introduced = introduced;
    }

    @Override
    public String getDiscounted() {
        return discounted;
    }

    public void setDiscounted(String discounted) {
        this.discounted = discounted;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public CompanyDbDto getCompany() {
        return company;
    }

    public void setCompany(CompanyDbDto company) {
        this.company = company;
    }

    @Override
    public String getCompanyName() {
        if (company != null){
            return company.getName();
        }
        return "";
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
