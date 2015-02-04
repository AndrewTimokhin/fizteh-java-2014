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
public class Reader implements ConstantValue {

    private StringBuilder readKeyValue(DataInputStream rd) throws IOException {
        StringBuilder string = new StringBuilder();
        int length = rd.readInt();
        for (int k = 0; k < length; k++) {
            string.append(rd.readChar());
        }
        return string;
    }

    public void read(TableProviderImplements tp) throws IOException {
        StringBuilder keyBuilder;
        StringBuilder valueBuilder;
        String path = tp.getDir();
        File testDir = new File(path);
        if (testDir.list() != null) {
            for (String time : testDir.list()) {
                Path pathToFile = Paths.get(path, time);
                File checkDir = new File(pathToFile.toString());
                if (checkDir.isDirectory()) {
                    tp.createTable(time);
                    TableImplement dataBase = (TableImplement) tp.getTable(time);
                    dataBase.setPath(tp.getDir());
                    for (int i = 0; i < TOTAL_SUB_STRING; i++) {
                        Path pathToDb = Paths.get(path, time,
                                i + DIRECTORY_SUFFIX);
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
                                                    keyBuilder = readKeyValue(rd);
                                                    valueBuilder = readKeyValue(rd);

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
    }
}
