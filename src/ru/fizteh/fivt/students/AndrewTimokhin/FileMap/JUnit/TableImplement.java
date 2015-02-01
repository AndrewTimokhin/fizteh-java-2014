package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit;

/**
 * Класс TableImplement содержит логику работы таблицы базы данных. 
 * В нем переопределены все методы, которые заявлены в интерфейсе Table
 * 
 * @author Timokhin Andrew
 */

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TableImplement<V> implements Table {

    private String name;
    private String path;
    private Map<String, String> map;
    private Map<String, String> backup;
    public Map<String, String> current;

    public TableImplement(String name, String path) {
        this.path = path;
        this.name = name;
        map = new HashMap<String, String>();
        backup = new HashMap<String, String>();
        current = new HashMap<String, String>();
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public Map<String, String> getBackup() {
        return backup;
    }

    public void setBackup(Map<String, String> backup) {
        this.backup = backup;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int size() {
        int size = 0;
            size += map.size();
         return size;
    }

    @Override
    public String get(String key) throws KeyNullAndNotFound {
        if (key == null) {
            throw new KeyNullAndNotFound("Key is null");
        }
        if (map != null) {
            if (map.containsKey(key)) {
                return map.get(key);
            }
        }
        return null;
    }

    @Override
    public String put(String key, String value) throws IllegalArgumentException {
        String time = null;
        if (key == null || value == null) {
            throw new IllegalArgumentException(
                    "Key or (and) value is wrong.");
        }
        if (map != null) {
            if (map.containsKey(key)) {
                time = map.get(key);
            }
        }
        map.put(key, value);
        return time;
    }

    @Override
    public String remove(String key) throws IllegalArgumentException {
        String time = null;
        if (key == null) {
            throw new IllegalArgumentException("Key is wrong.");
        }
            if (map.containsKey(key)) {
                time = (String) map.get(key);
                map.remove(key);
        }
        return time;
    }

    @Override
    public List<String> list() {
        Set<String> time;
        if (map != null) {
            return new ArrayList<String>(map.keySet());
        }
        return new ArrayList<String>(); // if map is empty
    }

    public int totalChanges() {
        int counter = 0;
        if (backup == null && map == null) {
            return 0;
        }
        if (backup != null && map == null) {
            return backup.size();
        }
        if (backup == null && map != null) {
            return map.size();
        }
        if (backup != null && map != null) {
            Set<String> backupKey = backup.keySet();
            for (String timeKey : backupKey) {
                if ((map.containsKey(timeKey)
                        && !(backup.get(timeKey).equals(map.get(timeKey)))) || !map.containsKey(timeKey)) {
                    counter++;
                }
            }
            backupKey = map.keySet();
            for (String timeKey : backupKey) {
                if (!backup.containsKey(timeKey)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    @Override
    public int commit() {
        try {
            return this.writeToDisk();
        } catch (IOException ioexcptn) {
            throw new RuntimeException(new IOException("Cannot write to the File System"));
        }
    }

    public int writeToDisk() throws IOException {
        boolean flagIfBaseExist = false;
        Reader reader = new Reader();
        FactoryImplements factory = new FactoryImplements();
        TableProviderImplements tableProvider = (TableProviderImplements) factory
                .create(path);
        reader.read(tableProvider);
        Writer writer = new Writer();
        if (tableProvider.collection != null) {
            {
                if (tableProvider.collection.containsKey(this.getName())) {
                    {
                        Map<String, String> tmp = new HashMap<>();
                        Set<String> copy = tableProvider.collection.get(name).map
                                .keySet();
                        for (String copyString : copy) {
                            tmp.put(new String(copyString),
                                    new String(
                                            (String) tableProvider.collection
                                                    .get(name).map.get(copyString)));
                        }
                        this.backup = new HashMap<String, String>(tmp);
                    }
                    tableProvider.collection.put(this.getName(), this);
                    flagIfBaseExist = true;
                }
            }
        }
        if (!flagIfBaseExist) {
            tableProvider.createTable(this.name);
            this.backup = null;
        }
        if (tableProvider.collection.containsKey(this.getName()))
            tableProvider.collection.put(this.getName(), this);
        if (this.totalChanges() > 0)
            writer.write(tableProvider);
        return this.totalChanges();
    }

    @Override
    public int rollback() throws NullPointerException{
        Map<String, String> copyMap = new HashMap<String, String>(this.map);
        if (this.backup!= null)
        this.map = new HashMap<String, String>(this.backup);
        else
            this.map = new HashMap<String, String>();
        this.backup = copyMap;
        return this.commit();
    }
}
