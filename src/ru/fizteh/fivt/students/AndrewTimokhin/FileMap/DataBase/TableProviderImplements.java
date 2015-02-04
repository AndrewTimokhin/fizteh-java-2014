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
public class TableProviderImplements implements TableProvider, DeleteAlgorithm {

    private final Map<String, TableImplement> collection;
    private final String dir;

    public TableProviderImplements(String dir) throws IOException {
        this.dir = dir;
        collection = new HashMap<>();
        Reader rd = new Reader();
        rd.read(this);
    }

    public Set<String> getAvailableTables() {
        return collection.keySet();
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

        if (collection.containsKey(name)) {
            return null;
        }
        collection.put(name, new TableImplement(name, dir));
        return collection.get(name);

    }

    @Override
    public void removeTable(String name) throws IllegalArgumentException,
            IllegalStateException {
        if (name == null) {
            throw new IllegalArgumentException(
                    "Name of DataBase is null. Please, enter correct name");
        }
            if (collection.containsKey(name)) {
                Path path = Paths.get(collection.get(name).getPath(), collection
                        .get(name).getName());
                File file = new File(path.toString());
                this.deleteDirectory(file);
                collection.remove(name);
                return;
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
