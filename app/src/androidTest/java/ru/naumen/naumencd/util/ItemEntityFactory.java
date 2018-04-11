package ru.naumen.naumencd.util;

import java.util.ArrayList;
import java.util.List;

import ru.naumen.naumencd.models.dbdto.CompanyDbDto;
import ru.naumen.naumencd.models.dbdto.interfaces.ItemEntity;
import ru.naumen.naumencd.models.dbdto.interfaces.SimilarItemEntity;

public class ItemEntityFactory {
    private static VariableGenerator variableGenerator = new VariableGenerator();

    public static ItemEntity createItemEntity() {
        return new ItemEntity() {
            int id = variableGenerator.generateInteger();
            int companyId = variableGenerator.generateInteger();
            String name = variableGenerator.generateString();
            String companyName = variableGenerator.generateString();
            String introduced = "1986-01-15T19:00:00Z";
            String discounted = "1986-01-15T19:00:00Z";
            String description = variableGenerator.generateString();

            @Override
            public Integer getId() {
                return id;
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getIntroduced() {
                return introduced;
            }

            @Override
            public String getDiscounted() {
                return discounted;
            }

            @Override
            public String getImageUrl() {
                return null;
            }

            @Override
            public CompanyDbDto getCompany() {
                CompanyDbDto companyDbDto = new CompanyDbDto();
                companyDbDto.setId(companyId);
                companyDbDto.setName(companyName);
                return companyDbDto;
            }

            @Override
            public String getDescription() {
                return description;
            }

        };
    }

     public static List<SimilarItemEntity> createList(int size) {
        List<SimilarItemEntity> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(createSimilarItem());
        }
        return list;
    }

    private static SimilarItemEntity createSimilarItem() {
        return  new SimilarItemEntity() {
            int id = variableGenerator.generateInteger();
            int itemId = variableGenerator.generateInteger();
            String name = variableGenerator.generateString();

            @Override
            public Integer getItemId() {
                return id;
            }

            @Override
            public Integer getId() {
                return itemId;
            }

            @Override
            public String getName() {
                return name;
            }
        };
    }
}
