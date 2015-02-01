package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Timokhin Andrew
 */

public class TableProviderImplements implements TableProvider {

    public static void deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                File f = new File(dir, children[i]);
                deleteDir(f);
            }
            dir.delete();
        } else
            dir.delete();
    }

    protected Map<String, TableImplement> collection;
    public final String dir;

    TableProviderImplements(String dir) throws IOException {
        this.dir = dir;
        collection = new HashMap<>();
        Reader rd = new Reader();
        rd.read(this);
    }

    public void write() throws IOException {
        Writer writeToFileSystem = new Writer();
        writeToFileSystem.write(this);
    }

    @Override
    public Table getTable(String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException(
                    "Info: Name of DataBase is null. Please, enter correct name");
        }
        if (collection != null) { // if database is created now
            if (collection.containsKey(name))
                return collection.get(name);
        }
        return null;
    }

    @Override
    public Table createTable(String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException(
                    "Info: Name of DataBase is null. Please, enter correct name");
        }
        if (collection != null) {
            if (collection.containsKey(name))
                return null;
            {
                collection.put(name, new TableImplement(name, dir));
                return collection.get(name);
            }
        }
        if (collection == null) {
            collection = new HashMap<>();
            collection.put(name, new TableImplement(name, dir));
            return collection.get(name);
        }
        return null;
    }

    @Override
    public void removeTable(String name) throws IllegalArgumentException,
            IllegalStateException {
        if (name == null) {
            throw new IllegalArgumentException(
                    "Info: Name of DataBase is null. Please, enter correct name");
        }
        if (collection != null) {
            if (collection.containsKey(name)) {
                Path path = Paths.get(
                        collection.get(name).getPath().toString(), collection
                                .get(name).getName());
                File file = new File(path.toString());
                this.deleteDir(file);
                collection.remove(name);
                return;
            }
        }
        throw new IllegalStateException("Requested database don't exist");
    }
}
