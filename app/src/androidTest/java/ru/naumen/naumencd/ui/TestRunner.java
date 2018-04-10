package ru.naumen.naumencd.ui;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

import ru.naumen.naumencd.app.ComputerDatabaseTestApp;

public class TestRunner extends AndroidJUnitRunner {
    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, ComputerDatabaseTestApp.class.getName(), context);
    }

}
