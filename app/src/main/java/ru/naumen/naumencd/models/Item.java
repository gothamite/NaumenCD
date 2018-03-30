package ru.naumen.naumencd.models;


import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import ru.naumen.naumencd.models.dto.Company;

@Entity
public class Item implements ItemEntity{

    @SerializedName("id")
    @Expose
    @PrimaryKey
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("introduced")
    @Expose
    private String introduced;

    @SerializedName("discounted")
    @Expose
    private String discounted;

    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;

    @SerializedName("company")
    @Expose
    @Embedded(prefix = "Company")
    private Company company;

    @SerializedName("description")
    @Expose
    private String description;

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
    public String getIntroduced() {
        return introduced;
    }

    @Override
    public void setIntroduced(String introduced) {
        this.introduced = introduced;
    }

    @Override
    public String getDiscounted() {
        return discounted;
    }

    @Override
    public void setDiscounted(String discounted) {
        this.discounted = discounted;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public Company getCompany() {
        return company;
    }

    @Override
    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

}
