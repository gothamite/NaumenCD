package ru.naumen.naumencd.util;

import java.util.ArrayList;
import java.util.List;

import ru.naumen.naumencd.models.dbdto.interfaces.PageItemEntity;

public class PageItemEntityFactory {
    private static VariableGenerator variableGenerator = new VariableGenerator();

    private static PageItemEntity create() {
        return new PageItemEntity() {
            int id = variableGenerator.generateInteger();
            int pageId = variableGenerator.generateInteger();
            String name = variableGenerator.generateString();
            String companyName = variableGenerator.generateString();

            @Override
            public Integer getId() {
                return id;
            }

            @Override
            public Integer getPageId() {
                return pageId;
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getCompanyName() {
                return companyName;
            }
        };
    }

    public static List<PageItemEntity> createList(int size) {
        List<PageItemEntity> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(create());
        }
        return list;
    }
}
