package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.DataBase;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;
import static java.lang.Math.abs;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Класс @class Writer отвечает за физическую запись информации с на жесткий
 * диск или другой носитель информации. Логика работы устойчива к новым папкам и
 * т.д.
 *
 * @author Timokhin Andrew
 */
public class Writer implements ConstantValue, DeleteAlgorithm {

    boolean checkDir(String name) {
        File tmpFile = new File(name);
        return tmpFile.exists();

    }

    void createDir(String path) {
        File tmpDir = new File(path);
        tmpDir.mkdir();
    }

    void createFile(String path) throws IOException {

        File tmpFile = new File(path);
        File prepareToMakeDir = new File(tmpFile.getParent());
        prepareToMakeDir.mkdirs();
        tmpFile.createNewFile();
    }

    public void write(TableProviderImplements tp) throws IOException, KeyNullAndNotFound {
        this.deleteDirectory(new File(tp.getDir()));
        File baseDir = new File(tp.getDir());
        baseDir.mkdir();
            Set<String> dataBase = tp.getAvailableTables();
            for (String name : dataBase) {
                TableImplement ti = (TableImplement) tp.getTable(name);
                Path pathToDb = Paths.get(tp.getDir(), ti.getName());
                File dataBaseDir = new File(pathToDb.toString());
                dataBaseDir.mkdir();
                {
                    ArrayList<String> keyList;
                    keyList = (ArrayList<String>) ti.list();
                    for (String keyFind : keyList) {
                        Integer dirToWrite = new Integer(abs(keyFind.hashCode()
                                % TOTAL_SUB_STRING));
                        Path localPath = Paths.get(tp.getDir(), ti.getName(),
                                dirToWrite.toString() + DIRECTORY_SUFFIX);
                        if (!this.checkDir(localPath.toString())) {
                            this.createDir(localPath.toString());
                        }
                        dirToWrite = new Integer((ti.get(keyFind)
                                .hashCode()
                                % TOTAL_SUB_STRING % TOTAL_SUB_STRING));
                        Path localFile = Paths.get(localPath.toString(),
                                dirToWrite.toString());
                        File checkIfExist = new File(localFile.toString());
                        if (!this.checkDir(localFile.toString())
                                && !checkIfExist.exists()) {

                            this.createFile(localFile.toString());
                        }
                        try (DataOutputStream outStream = new DataOutputStream(
                                new FileOutputStream(localFile.toString(), true))) {

                            outStream.writeInt(keyFind.length());
                            outStream.writeChars(keyFind);

                            outStream.writeInt(ti.get(keyFind).toString()
                                    .length());
                            outStream.writeChars(ti.get(keyFind)
                                    .toString());
                        }
                    }

                }
            }
    }
}
