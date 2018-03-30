package ru.naumen.naumencd.models;



public interface ItemEntity {

    Integer getId();

    void setId(Integer id);

    String getName();

    void setName(String name);

    String getIntroduced();

    void setIntroduced(String introduced);

    String getDiscounted();

    void setDiscounted(String discounted);

    String getImageUrl();

    void setImageUrl(String imageUrl);

    Company getCompany();

    void setCompany(Company company);

    String getDescription();

    void setDescription(String description);
}
