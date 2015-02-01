package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit;

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
                    "Info: String representing directory is null");
        }
        try {
            return new TableProviderImplements(dir);
        } catch (IOException xcpt) {
            throw new RuntimeException(
                    "Info: Target directory cannot be created or you don't have access to creating files! Try to run as administrator");
        }
    }
}
