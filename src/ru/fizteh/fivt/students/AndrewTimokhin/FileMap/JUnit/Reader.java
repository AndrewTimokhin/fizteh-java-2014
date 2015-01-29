package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

/**
 * Класс @class Reader отвечает за физическое чтение данных с жесткого диска или
 * другого физического носителя информации.
 * 
 *
 * @author Timokhin Andrew
 */

public class Reader {
    public void read(TableProviderImplements tp) throws IOException {
        int i;
        StringBuilder keyBuilder = new StringBuilder(); // буфер для чтения
                                                        // ключей
        StringBuilder valueBuilder = new StringBuilder(); // буфер для чтения
                                                          // значений
        int length; // длина ключа/значения
        String path = tp.dir; // корень хранилища
        Vector<TableImplement> agregat = new Vector<TableImplement>();
        int validator = 0;
        File testDir = new File(path);
        if (testDir.list() != null) {
            for (String time : testDir.list()) {
                File checkDir = new File(path + "\\" + time);

                if (checkDir.isDirectory()) {
                    validator++;
                    TableImplement database = new TableImplement(time, tp.dir);
                    agregat.add(database);
                    for (i = 0; i < 16; i++) { // сканируеться максимально
                                               // возможное число директорий =
                                               // 16
                        Integer numberDir = new Integer(i);
                        File locDB = new File(path + "\\" + time + "\\"
                                + numberDir.toString() + ".dir");
                        if (locDB.exists()) {
                            for (String file : locDB.list()) {

                                try (DataInputStream rd = new DataInputStream(
                                        new FileInputStream(
                                                locDB.getAbsolutePath() + "\\"
                                                        + file))) {

                                    while (true) {

                                        try {
                                            length = rd.readInt(); // на 1 шаге
                                                                   // алгоритм
                                                                   // считывает
                                                                   // размер
                                                                   // ключа
                                            for (int k = 0; k < length; k++) { // читается
                                                                               // ключ
                                                keyBuilder
                                                        .append(rd.readChar());

                                            }
                                            length = rd.readInt(); // на 2 шаге
                                                                   // алгоритм
                                                                   // считывает
                                                                   // размер
                                                                   // значения
                                            for (int k = 0; k < length; k++) { // считывается
                                                                               // значение
                                                valueBuilder.append(rd
                                                        .readChar());

                                            }

                                            database.map.put(
                                                    keyBuilder.toString(), // добавление
                                                                           // ключа/значения
                                                                           // в
                                                                           // карту
                                                    valueBuilder.toString());
                                            database.backup.put(
                                                    keyBuilder.toString(),
                                                    valueBuilder.toString());
                                            keyBuilder.replace(0,
                                                    keyBuilder.length(), // сохранение
                                                                         // начальных
                                                                         // данных
                                                                         // в
                                                                         // бэкап
                                                    "");
                                            valueBuilder.replace(0,
                                                    valueBuilder.length(), ""); // сброс
                                                                                // буфера
                                        } catch (EOFException e) {

                                            break;
                                        }

                                    }

                                } catch (FileNotFoundException e) {
                                    // do nothing

                                }

                            }
                        }
                    }

                }

            }
        }
        if (validator == 0) {
            return;
        }// загружаем ранее сохраненные базы данных в нашу базу
         // данных

        TableImplement[] copy = new TableImplement[agregat.size()];
        i = 0;
        Iterator<TableImplement> it = agregat.iterator();

        while (it.hasNext()) {

            copy[i] = it.next();
            i++;
        }
        tp.t = copy;
        return;

    }

}
