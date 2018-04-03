package ru.naumen.naumencd.models.dbdto.interfaces;


import ru.naumen.naumencd.models.dbdto.CompanyDbDto;

public interface ItemEntity {

    Integer getId();

    String getName();

    String getIntroduced();

    String getDiscounted();

    String getImageUrl();

    CompanyDbDto getCompany();

    String getDescription();

}
