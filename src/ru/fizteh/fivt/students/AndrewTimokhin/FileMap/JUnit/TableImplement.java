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

public class TableImplement implements Table {

    public String name; // содержит имя таблицы
    public String path; // путь к таблице
    public Map<String, String> map; // real-time версию карты
    public Map<String, String> backup; // бэкапнутую версию базы данных

   public TableImplement(String name, String path) {
        this.path = path; // устанавливает путь к базе данных
        this.name = name; // устанавливает имя базы данных
        map = new HashMap<String, String>(); // выделение место под real-time
                                             // карту
        backup = new HashMap<String, String>(); // выделение места под
                                                // backup-карту

    }

    @Override
    public String getName() { // возвращает имя базы данных
        return name;
    }

    @Override
    public int size() { // Возврат числа ключей, хранимых в базе данных.
                        // Исключения не вырабатываються.
        int summ = 0; // изначальный размер базы данных полагает равным 0.
        if (map != null) {
            summ += map.size();// сохраняем реальное число записей, хранимых в
                               // базе данных.
        }
        return summ;
    }

    @Override
    public String get(String key) throws IllegalArgumentException { // возврашает
                                                                    // значение
                                                                    // по
                                                                    // указанному
                                                                    // ключу
        if (key == null)
            throw new IllegalArgumentException(
                    "Error in get-meth. Key is wrong!"); // если ключ null,
                                                         // тогда возбуждается
                                                         // исключение
        if (map != null)
            if (map.containsKey(key))
                return (String) map.get(key);
        return null;

    }

    @Override
    public String put(String key, String value) throws IllegalArgumentException { // метод
                                                                                  // создан
                                                                                  // для
                                                                                  // записи
                                                                                  // ключа
                                                                                  // и
                                                                                  // значения
                                                                                  // в
                                                                                  // базу
                                                                                  // данных
        String time = null;
        if (key == null || value == null)
            throw new IllegalArgumentException(
                    "Error in put-meth. Key or (and) value is wrong."); // если
                                                                        // неверно
                                                                        // заданы
                                                                        // аргументы
        // возбуждает исключение
        if (map != null) // случай, если база данных была непуста
            if (map.containsKey(key))
                time = (String) map.get(key);
        map.put(key, value);
        return time; // возращает значение ранее ассоциированное с данным ключом
        // null- если не было ранее никаких ассоциаций
    }

    @Override
    public String remove(String key) throws IllegalArgumentException // удаляем
                                                                     // значение
                                                                     // ассоциированное
                                                                     // с
                                                                     // ключом,
                                                                     // переданным
                                                                     // в
                                                                     // параметрах
    {
        String time = null;
        if (key == null)
            throw new IllegalArgumentException(
                    "Error in remove-meth. Key is wrong.");
        if (map != null)
            if (map.containsKey(key)) {
                time = (String) map.get(key);
                map.remove(key);
            }
        return time;

    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List<String> list() { // возврат списка, содержащего ключи базы
                                 // данных
        Set<String> time;
        if (map != null) {
            time = map.keySet(); // получение ключей, имеющхся в базе данных в
                                 // данный момент
            List<?> list = new ArrayList(time);
            return (List<String>) list;
        }
        return (List<String>) (new ArrayList());
    }

    @Override
    public int commit() {
        int counter = 0;
        if (backup == null && map == null)
            return 0; // изменений нет, не нужно коммитеть
        if (backup != null && map == null)
            counter= backup.size(); // текущее состояние базы данных пусто, в
                                  // бекапе есть записи, выполняеться перезапись
        if (backup == null && map != null)
            counter = map.size(); // аналогично
         if (backup != null && map != null) {
        Set<String> time = backup.keySet();
        for (String time1 : time) {

            if (backup.get(time1).equals(map.get(time1)) == false)
                counter++; // если одному и тому же ключу соответствуют разные
                           // значения
            // то метод считает, что нужно изменить
            else if (backup.containsKey(time1)
                    && map.containsKey(time1) == false)
                counter++;
        }
        time = map.keySet();
        for (String time1 : time) {

            if ((backup.containsKey(time1) && map.containsKey(time1)) == false) {
                // аналогично, однако, если один ключ был удален, а другой был
                // добавлен, то считаеться что было произведено 2 изменения
                counter++;
            }
        }}
        System.out.println("in this method counter is ==> " + counter);
        if (counter != 0) // если есть изменения вызывается физичекая запись на
                          // жесткий диск
            try {

                this.writeToDisk();
            } catch (IOException e) {
                // do nothing
            }

        return counter; // возвращаеться общий счетчик числа изменений
    }

    public void writeToDisk() throws IOException { // метод, реально
                                                   // записывающий сделанные
                                                   // изменения на диск
        int flag = 0;
        Reader rd = new Reader();
        TableProviderImplements tpi = new TableProviderImplements(path);
        rd.read(tpi); // считываем, имеющееся на диске
        Writer writer = new Writer();
        if (tpi.t != null) {
            for (int i = 0; i < tpi.t.length; i++) {
                if (tpi.t[i].getName().equals(this.getName())) {

                    {
                        Map<String, String> tmp = new HashMap<String, String>();
                        @SuppressWarnings("unchecked")
                        Set<String> copy = tpi.t[i].map.keySet();
                        for (String time : copy) {

                            tmp.put(new String(time),
                                    new String(tpi.t[i].map.get(time)));
                        }
                        this.backup = new HashMap<String, String>(tmp);
                        ;

                    }

                    tpi.t[i] = this;
                    flag = 1;
                }

            }

        }
        if (flag == 0) {
            tpi.createTable(this.name);
            this.backup = null;
        }
        for (int i = 0; i < tpi.t.length; i++) {
            if (tpi.t[i].getName().equals(this.getName()))
                tpi.t[i] = this;

        }

        writer.write(tpi);
        if (this.map == null) {
            this.commit();

        }

    }

    @Override
    public int rollback() { // отвечает за откат к предыдущему состоянию (к
                            // самому последнему коммиту)
        Map<String, String> time = new HashMap<String, String>(this.map);
        this.map = new HashMap<String, String>(this.backup);
        this.backup = time;
        this.commit();

        return 0;
    }
}
