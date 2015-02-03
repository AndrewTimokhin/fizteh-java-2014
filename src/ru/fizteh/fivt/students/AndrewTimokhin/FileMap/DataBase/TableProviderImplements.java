package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.DataBase;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Timokhin Andrew
 */
public class TableProviderImplements implements TableProvider {

    private Map<String, TableImplement> collection;
    private final String dir;

    TableProviderImplements(String dir) throws IOException {
        this.dir = dir;
        collection = new HashMap<String, TableImplement>();
        Reader rd = new Reader(new String(this.dir));
        rd.read(this);
    }

    public Set<String> getAvailableTables() {
        return collection.keySet();
    }

    public static void deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (String childName : children) {
                deleteDir(new File(dir, childName));
            }
            dir.delete();
        } else {
            dir.delete();
        }
    }

    public void write() throws IOException, KeyNullAndNotFound {
        Writer writeToFileSystem = new Writer();
        writeToFileSystem.write(this);
    }

    @Override
    public Table getTable(String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException(
                    "Name of DataBase is null");
        }
        if (collection.containsKey(name)) {
            return collection.get(name);
        }
        return null;
    }

    @Override
    public Table createTable(String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException(
                    "Name of DataBase is null. Please, enter correct name");
        }
        if (collection != null) {
            if (collection.containsKey(name)) {
                return null;
            }
            collection.put(name, new TableImplement(name, dir));
            return collection.get(name);
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
                    "Name of DataBase is null. Please, enter correct name");
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

    public int size() {
        int size = 0;
        for (String nameTable : collection.keySet()) {
            size += collection.get(nameTable).size();
        }
        return size;
    }

    public String getDir() {
        return dir;
    }
}
