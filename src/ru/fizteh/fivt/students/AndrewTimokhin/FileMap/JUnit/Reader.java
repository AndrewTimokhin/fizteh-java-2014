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
        StringBuilder keyBuilder = new StringBuilder();
        StringBuilder valueBuilder = new StringBuilder();
        int length;
        String path = tp.dir;
        Vector<TableImplement> agregat = new Vector<TableImplement>();
        int validator = 0;
        File testDir = new File(path);
        if (testDir.list() != null) {
            for (String time : testDir.list()) {
                File checkDir = new File(path + "\\" + time);

                if (checkDir.isDirectory()) {
                    validator++;
                    TableImplement dataBase = new TableImplement(time, tp.dir);
                    agregat.add(dataBase);
                    for (i = 0; i < 16; i++) {
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
                                            length = rd.readInt();
                                            for (int k = 0; k < length; k++) {
                                                keyBuilder
                                                        .append(rd.readChar());

                                            }
                                            length = rd.readInt();
                                            for (int k = 0; k < length; k++) {
                                                valueBuilder.append(rd
                                                        .readChar());

                                            }

                                            dataBase.getMap().put(
                                                    keyBuilder.toString(),
                                                    valueBuilder.toString());
                                            dataBase.getBackup().put(
                                                    keyBuilder.toString(),
                                                    valueBuilder.toString());
                                            keyBuilder.replace(0,
                                                    keyBuilder.length(), "");
                                            valueBuilder.replace(0,
                                                    valueBuilder.length(), "");
                                        } catch (EOFException e) {

                                            break;
                                        }

                                    }

                                } catch (FileNotFoundException fnfe) {
                                    System.out.println(fnfe.toString());

                                }

                            }
                        }
                    }

                }

            }
        }
        if (validator == 0) {
            return;
        }
        TableImplement[] copy = new TableImplement[agregat.size()];
        i = 0;
        Iterator<TableImplement> it = agregat.iterator();

        while (it.hasNext()) {

            copy[i] = it.next();
            i++;
        }
        tp.collection = copy;
        return;

    }

}
