package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.DataBase;

import java.io.IOException;

/**
 * Класс @class FactoryImplements отвечает за фабрику по созданию таблиц.
 * Имплиментируя интерфейс TableProviderFactory, переопределяет метод по
 * созданию провайдера базы данных.
 *
 * @author Timokhin Andrew
 */
public class FactoryImplements implements TableProviderFactory {

    @Override
    public TableProvider create(String dir) {
        if (dir == null) {
            throw new IllegalArgumentException(
                    "String representing directory is null");
        }
        try {
            return new TableProviderImplements(dir);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
