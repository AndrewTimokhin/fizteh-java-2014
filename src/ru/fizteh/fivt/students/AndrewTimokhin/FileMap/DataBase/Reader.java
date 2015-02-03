package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.DataBase;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Класс @class Reader отвечает за физическое чтение данных из файловой системы.
 *
 *
 * @author Timokhin Andrew
 */
public class Reader {

    static final int TOTAL_SUB_STRING = 16;
    static final String DIRECT = ".dir";
    private final String directory;

    public Reader(String directory) {
        this.directory = directory;
    }

    private void readKeyValue(DataInputStream rd, StringBuilder string) throws IOException {
        int length = rd.readInt();
        for (int k = 0; k < length; k++) {
            string
                    .append(rd.readChar());
        }
    }

    public void read(TableProviderImplements tp) throws IOException {
        StringBuilder keyBuilder = new StringBuilder();
        StringBuilder valueBuilder = new StringBuilder();
        int length;
        String path = tp.getDir();
        boolean dbExist = false;
        File testDir = new File(path);
        if (testDir.list() != null) {
            for (String time : testDir.list()) {
                Path pathToFile = Paths.get(path, time);
                File checkDir = new File(pathToFile.toString());
                if (checkDir.isDirectory()) {
                    dbExist = true;
                    tp.createTable(time);
                    TableImplement dataBase = (TableImplement) tp.getTable(time);
                    dataBase.setPath(tp.getDir());
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

                                                    dataBase.put(
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
                                                    rd.close();
                                                    break;
                                                }

                                            }
                                 } catch (FileNotFoundException exceptionFileNotFound) {
                                            throw new RuntimeException(
                                                    exceptionFileNotFound);
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

        return;
    }
}
