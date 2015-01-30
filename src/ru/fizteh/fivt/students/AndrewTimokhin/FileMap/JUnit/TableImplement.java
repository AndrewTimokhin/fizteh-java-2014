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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TableImplement<V> implements Table {

    private String name;
    private String path;
    private Map<String, String> map;
    private Map<String, String> backup;

    public TableImplement(String name, String path) {
        this.path = path;
        this.name = name;
        map = new HashMap<String, String>();
        backup = new HashMap<String, String>();
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
        int summ = 0;
        if (map != null) {
            summ += map.size();
        }
        return summ;
    }

    @Override
    public String get(String key) throws IllegalArgumentException,
            KeyNullAndNotFound {
        if (key == null) {
            IllegalArgumentException exception = new IllegalArgumentException(
                    "Error in get meth!");
            exception.initCause(new KeyNullAndNotFound("Error!"));
            throw exception;
        }
        if (map != null) {
            if (map.containsKey(key)) {
                return (String) map.get(key);
            }
        }
        return null;

    }

    @Override
    public String put(String key, String value) throws IllegalArgumentException {
        String time = null;
        if (key == null || value == null) {
            throw new IllegalArgumentException(
                    "Error in put-meth. Key or (and) value is wrong.");
        }
        if (map != null) {
            if (map.containsKey(key)) {
                time = (String) map.get(key);
            }
        }
        map.put(key, value);
        return time;
    }

    @Override
    public String remove(String key) throws IllegalArgumentException {
        String time = null;
        if (key == null) {
            throw new IllegalArgumentException(
                    "Error in remove-meth. Key is wrong.");
        }
        if (map != null) {
            if (map.containsKey(key)) {
                time = (String) map.get(key);
                map.remove(key);
            }
        }
        return time;

    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List<String> list() {
        Set<String> time;
        if (map != null) {
            time = map.keySet();
            List<String> list = new ArrayList<String>(time);
            return list;
        }
        return (new ArrayList<String>());
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
            Set<String> time = backup.keySet();
            for (String timeKey : time) {

                if (!(backup.get(timeKey).equals(map.get(timeKey)))) {
                    counter++;
                } else if (!map.containsKey(timeKey)) {
                    counter++;
                }
            }
            time = map.keySet();
            for (String timeKey : time) {

                if (!backup.containsKey(timeKey)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    @Override
    public int commit() {
        int counter = this.totalChanges();
        if (counter != 0) {
            try {

                this.writeToDisk();
            } catch (IOException ioexcptn) {
                System.out.println(ioexcptn.toString());
            }
        }
        return counter;
    }

    public void writeToDisk() throws IOException {
        int flagIfBaseExist = 0;
        Reader rd = new Reader();

        FactoryImplements tb = new FactoryImplements();
        TableProviderImplements tpi = (TableProviderImplements) tb.create(path);

        rd.read(tpi);
        Writer writer = new Writer();
        if (tpi.collection != null) {
            for (int i = 0; i < tpi.collection.length; i++) {
                if (tpi.collection[i].getName().equals(this.getName())) {

                    {
                        Map<String, String> tmp = new HashMap<String, String>();
                        @SuppressWarnings("unchecked")
                        Set<String> copy = tpi.collection[i].map.keySet();
                        for (String time : copy) {

                            tmp.put(new String(time),
                                    new String((String) tpi.collection[i]
                                            .getMap().get(time)));
                        }
                        this.backup = new HashMap<String, String>(tmp);
                        ;

                    }

                    tpi.collection[i] = this;
                    flagIfBaseExist = 1;
                }

            }

        }
        if (flagIfBaseExist == 0) {
            tpi.createTable(this.name);
            this.backup = null;
        }
        for (int i = 0; i < tpi.collection.length; i++) {
            if (tpi.collection[i].getName().equals(this.getName())) {
                tpi.collection[i] = this;
            }

        }

        writer.write(tpi);
        if (this.map == null) {
            // this.commit();

        }

    }

    @Override
    public int rollback() {
        Map<String, String> time = new HashMap<String, String>(this.map);
        this.map = new HashMap<String, String>(this.backup);
        this.backup = time;
        return this.commit();
    }
}
