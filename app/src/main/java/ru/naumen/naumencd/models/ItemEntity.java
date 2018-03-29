package ru.naumen.naumencd.models;



public interface ItemEntity {

    public Integer getId();

    public void setId(Integer id);

    public String getName();

    public void setName(String name);

    public String getIntroduced();

    public void setIntroduced(String introduced);

    public String getDiscounted();

    public void setDiscounted(String discounted);

    public String getImageUrl();

    public void setImageUrl(String imageUrl);

    public Company getCompany();

    public void setCompany(Company company);

    public String getDescription();

    public void setDescription(String description);
}
