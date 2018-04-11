package ru.naumen.naumencd.util;

import java.util.Random;

import ru.naumen.naumencd.models.dbdto.SimilarItemDbDto;
import ru.naumen.naumencd.models.dto.SimilarItem;

public class VariableGenerator {

    private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static Random RANDOM = new Random();

    public String generateString() {
        StringBuilder stringBuilder = new StringBuilder(10);

        for (int i = 0; i < 10; i++) {
            stringBuilder.append(CHARS.charAt(RANDOM.nextInt(CHARS.length())));
        }
        return stringBuilder.toString();
    }

    public Integer generateInteger() {
        return RANDOM.nextInt() + 1;
    }
}
