package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit;

import java.io.IOException;

/**
 *  ласс @class FactoryImplements отвечает за фабрику по созданию таблиц.
 * »мплиментиру€ интерфейс TableProviderFactory, переопредел€ет метод по
 * созданию провайдера базы данных.
 *
 * @author Timokhin Andrew
 */

public class FactoryImplements implements TableProviderFactory {

    @Override
    public TableProvider create(String dir) {
        if (dir == null) {
            throw new IllegalArgumentException("Error in create-meth.");
        }
        try {
            return new TableProviderImplements(dir);
        } catch (IOException xcpt) {
            System.out.println(xcpt.toString());
        }
        return null;
    }
}
