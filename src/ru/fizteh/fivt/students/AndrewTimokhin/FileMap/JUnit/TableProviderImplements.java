package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit;

import java.io.IOException;

/**
 * Класс @class TableProviderImplements содержит логику по работе провайдера
 * базы данных. В нем переопределены все методы, заявленные в интерфейсе
 * TableProvider
 * 
 * @author Timokhin Andrew
 */

public class TableProviderImplements implements TableProvider {

    public TableImplement[] t;
    public final String dir;

    TableProviderImplements(String dir) throws IOException {
        this.dir = dir;
        t = null;
        Reader rd = new Reader();
        rd.read(this);
    }

    public void writer() throws IOException {
        Writer writer = new Writer();
        writer.write(this);
    }

    @Override
    public Table getTable(String name) throws IllegalArgumentException { // получение
                                                                         // таблицы
                                                                         // по
                                                                         // ее
                                                                         // имени
        if (name == null)
            throw new IllegalArgumentException("Error in getTable-meth"); // вырабатывает
                                                                          // исключение,
                                                                          // если
                                                                          // имя
                                                                          // таблицы
                                                                          // задано
                                                                          // некорректно
        if (t != null) {
            for (int i = 0; i < t.length; i++) {
                if (t[i].getName().equals(name))
                    return t[i]; // возврат таблицы, если такая существует

            }
        }
        return null; // возврат null в случае, если таблицу с указанным
                     // названием невозможно найти
    }

    @Override
    public Table createTable(String name) throws IllegalArgumentException { // создает
                                                                            // таблицу
                                                                            // с
                                                                            // указанным
                                                                            // именем
        if (name == null)
            throw new IllegalArgumentException("Error in createTable-meth"); // вырабатывает
                                                                             // исключение,
                                                                             // если
                                                                             // имя
                                                                             // таблицы
                                                                             // задано
                                                                             // некорректно
        if (t != null) {
            for (int i = 0; i < t.length; i++) {
                if (name.equals(t[i].getName())) {
                    return null; // если таблица с указанным именем содержиться
                                 // в агрегате, тогда возврат null
                }
            }
            {
                TableImplement[] temp = new TableImplement[t.length + 1];
                for (int k = 0; k < t.length; k++) {
                    temp[k] = t[k];
                }

                temp[t.length] = new TableImplement(name, dir);
                t = temp;
                return t[t.length - 1];
            }
        }

        if (t == null) { // на случай пустого агрегата, сразу создаем таблицу
                         // (без копирующего с пропуском алгоритма)
            t = new TableImplement[1];
            t[0] = new TableImplement(name, dir);
            return t[0];
        }
        return null;
    }

    @Override
    public void removeTable(String name) throws IllegalArgumentException,
            IllegalStateException { // удаление таблицы с указанным именем,
                                    // возможна
        // выроботка двух видов исключений
        if (name == null)
            throw new IllegalArgumentException("Error in removeTable-meth"); // если
                                                                             // название
                                                                             // таблицы
                                                                             // неверно,
                                                                             // тогда
                                                                             // возбуждает
                                                                             // исключение
        if (t != null) {
            for (int i = 0; i < t.length; i++) { // использует алгоритм
                                                 // копирования с пропусками
                if (t[i].getName().equals(name)) {
                    t[i].backup = null;
                    t[i].map = null;
                    t[i].name = null;
                    TableImplement[] temp = new TableImplement[t.length - 1];
                    for (int j = 0; j < i; j++) {
                        temp[j] = t[j];
                    }

                    for (int j = i + 1; j < t.length; j++) {
                        temp[j - 1] = t[j];
                    }
                    t = temp;
                    return;
                }
            }
            throw new IllegalStateException("Error in removeTable-meth"); // если
                                                                          // таблицы,
                                                                          // соответствующей
                                                                          // параметру
                                                                          // name
                                                                          // не
                                                                          // существовало
            // то генерация исключения неверного состояния
        }

    }

}
