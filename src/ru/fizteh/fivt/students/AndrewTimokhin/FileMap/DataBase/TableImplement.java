package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.DataBase;

/**
 * Класс TableImplement содержит логику работы таблицы базы данных. В нем
 * переопределены все методы, которые заявлены в интерфейсе Table
 *
 * @author Timokhin Andrew
 */
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TableImplement implements Table {

    private String name;
    private String path;
    private Map<String, String> map;
    private Map<String, String> backup;
    private Map<String, String> current;

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

    public void setPath(String newPath) {
        this.path = newPath;
        return;
    }

    public Map<String, String> getBackup() {
        return backup;
    }

    public void setBackup(Map<String, String> backup) {
        this.backup = backup;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int size() {
        return map.size();
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
        String previousValue = null;
        if (key == null || value == null) {
            throw new IllegalArgumentException(
                    "Key or (and) value is wrong.");
        }
        if (map != null) {
            if (map.containsKey(key)) {
                previousValue = map.get(key);
            }
        }
        map.put(key, value);
        return previousValue;
    }

    @Override
    public String remove(String key) throws IllegalArgumentException {
        String time = null;
        if (key == null) {
            throw new IllegalArgumentException("Key shouldn't be null");
        }
        if (map.containsKey(key)) {
            time = map.get(key);
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
        return new ArrayList<String>();
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
            throw new RuntimeException(ioexcptn);
        }
    }

    public int writeToDisk() throws IOException, IllegalArgumentException {
        boolean flagIfBaseExist = false;
        Reader reader = new Reader(this.path);
        FactoryImplements factory = new FactoryImplements();
        TableProviderImplements tableProvider = (TableProviderImplements) factory
                .create(path);
        reader.read(tableProvider);
        copyOfMap(this.map, this.backup);
        Writer writer = new Writer();
        if (tableProvider.getAvailableTables() != null) {

            {
                if (tableProvider.getAvailableTables().contains(this.getName())) {
                    {
                        Map<String, String> tmp = new HashMap<>();
                        List<String> copy = tableProvider.getTable(this.getName()).list();
                        for (String copyString : copy) {
                            try {
                                tmp.put(new String(copyString),
                                        new String(
                                                (String) tableProvider.getTable(this.getName()).get(copyString)));
                            } catch (KeyNullAndNotFound exception) {
                                continue;
                            }
                        }
                        this.backup = new HashMap<String, String>(tmp);
                    }
                } else {
                    tableProvider.createTable(this.name);
                    this.backup = new HashMap<String, String>();
                }

                tableProvider.removeTable(this.getName());
                tableProvider.createTable(this.getName());
                for (String timeString : this.list()) {
                    try {
                 tableProvider.getTable(this.getName()).put(new String(timeString), new String(this.get(timeString)));
                    } catch (KeyNullAndNotFound ex) {
                        continue;
                    }
                }
                flagIfBaseExist = true;
            }
        }

        if (this.totalChanges() > 0) {
            try {
                writer.write(tableProvider);
            } catch (KeyNullAndNotFound nullKey) {
                        System.out.println(nullKey.toString());
            }
        }
        return this.totalChanges();
    }

    @Override
    public int rollback() throws NullPointerException {
        Map<String, String> copyMap = new HashMap<String, String>(this.map);
        if (this.backup != null) {
            this.map = new HashMap<String, String>(this.backup);
        } else {
            this.map = new HashMap<String, String>();
        }
        this.backup = copyMap;
        return this.commit();
    }

    private void copyOfMap(Map<String, String> from, Map<String, String> to) {
        Map<String, String> timeMap = new HashMap<>();
        for (String timeKey : from.keySet()) {
            timeMap.put(new String(timeKey), new String(from.get(timeKey)));
        }
        to = new HashMap(timeMap);
        return;
    }

}
