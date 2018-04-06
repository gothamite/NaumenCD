package ru.naumen.naumencd.utils;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TimerImpl implements Timer {

    private static final int TIME = 30000;
    private Map<String, Long> hashMap = new HashMap<>();


    @Override
    public void updateTime(String key) {
        hashMap.put(key, new Date().getTime());
    }

    @Override
    public boolean isTimeValid(String key) {
        if (!hashMap.containsKey(key)) {
            return false;
        }
        Long date = hashMap.get(key);
        Long curDate = new Date().getTime();

        return (curDate - date < TIME);
    }
}
