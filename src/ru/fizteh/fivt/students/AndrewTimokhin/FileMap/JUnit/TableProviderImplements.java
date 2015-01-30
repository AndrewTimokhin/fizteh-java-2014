package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit;

import java.io.IOException;
import java.util.Collection;

/**
 * Класс @class TableProviderImplements содержит логику по работе провайдера
 * базы данных. В нем переопределены все методы, заявленные в интерфейсе
 * TableProvider
 * 
 * @author Timokhin Andrew
 */

public class TableProviderImplements implements TableProvider {

    @SuppressWarnings("rawtypes")
    public TableImplement[] collection;
    public final String dir;

    TableProviderImplements(String dir) throws IOException {
        this.dir = dir;
        collection = null;
        Reader rd = new Reader();
        rd.read(this);
    }

    public void writer() throws IOException {
        Writer writer = new Writer();
        writer.write(this);
    }

    @Override
    public Table getTable(String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("Error in getTable-meth");
        }
        if (collection != null) {
            for (int i = 0; i < collection.length; i++) {
                if (collection[i].getName().equals(name)) {
                    return collection[i];
                }

            }
        }
        return null;
    }

    @Override
    public Table createTable(String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("Error in createTable-meth");
        }
        if (collection != null) {
            for (int i = 0; i < collection.length; i++) {
                if (name.equals(collection[i].getName())) {
                    return null;
                }
            }
            {
                TableImplement[] temp = new TableImplement[collection.length + 1];
                System.arraycopy(collection, 0, temp, 0, collection.length);
                temp[collection.length] = new TableImplement(name, dir);
                collection = temp;
                return collection[collection.length - 1];
            }
        }

        if (collection == null) {
            collection = new TableImplement[1];
            collection[0] = new TableImplement(name, dir);
            return collection[0];
        }
        return null;
    }

    @Override
    public void removeTable(String name) throws IllegalArgumentException,
            IllegalStateException {
        if (name == null) {
            throw new IllegalArgumentException("Error in removeTable-meth");
        }
        if (collection != null) {
            for (int i = 0; i < collection.length; i++) {
                if (collection[i].getName().equals(name)) {
                    collection[i].setBackup(null);
                    collection[i].setMap(null);
                    collection[i].setName(null);
                    TableImplement[] temp = new TableImplement[collection.length - 1];
                    System.arraycopy(collection, 0, temp, 0, i);
                    if (i < collection.length) {
                        System.arraycopy(collection, i + 1, temp, i,
                                collection.length - i - 1);
                    }
                    collection = temp;
                    return;
                }
            }
            throw new IllegalStateException("Error in removeTable-meth");
        }

    }

}
