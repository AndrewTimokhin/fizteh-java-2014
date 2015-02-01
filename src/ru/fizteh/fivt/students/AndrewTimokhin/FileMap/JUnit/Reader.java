package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * Класс @class Reader отвечает за физическое чтение данных из файловой системы.
 * 
 *
 * @author Timokhin Andrew
 */

public class Reader {
    static final int TOTAL_SUB_STRING = 16;
    static final String DIRECT = ".dir";
 private void readKeyValue(DataInputStream rd, StringBuilder string) throws IOException{
                                       int length = rd.readInt();
                                            for (int k = 0; k < length; k++) {
                                                string
                                                        .append(rd.readChar());
                                            } 
 };
    public void read(TableProviderImplements tp) throws IOException {
        StringBuilder keyBuilder = new StringBuilder();
        StringBuilder valueBuilder = new StringBuilder();
        int length;
        String path = tp.getDir();
        List<TableImplement> tables = new ArrayList<TableImplement>();
        boolean dbExist = false;
        File testDir = new File(path);
        if (testDir.list() != null) {
            for (String time : testDir.list()) {
                Path pathToFile = Paths.get(path, time);
                File checkDir = new File(pathToFile.toString());
                if (checkDir.isDirectory()) {
                    dbExist = true;
                    TableImplement dataBase = new TableImplement(time, tp.getDir());
                    tables.add(dataBase);
                    for (int i = 0; i < TOTAL_SUB_STRING; i++) {
                        Integer numberDir = new Integer(i);
                        Path pathToDb = Paths.get(path, time,
                                numberDir.toString() + DIRECT);
                        File locDB = new File(pathToDb.toString());
                        if (locDB.exists()) {
                            for (String file : locDB.list()) {
                                Path pathToLocalDb = Paths.get(
                                        locDB.getAbsolutePath(), file);
                                try (DataInputStream rd = new DataInputStream(
                                        new FileInputStream(
                                                pathToLocalDb.toString()))) {
                                    while (true) {
                                        try {
                                            readKeyValue(rd, keyBuilder);
                                            readKeyValue(rd, valueBuilder); 
                                             
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
                                    throw new RuntimeException(
                                            "Target directory cannot be read! Try to run as administrator");
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!dbExist) {
            return;
        }
        Map<String, TableImplement> copy = new HashMap<>();
        for (TableImplement table : tables) {
            copy.put(table.getName(), table);
        }
        tp.collection = copy;
        return;
    }
}
