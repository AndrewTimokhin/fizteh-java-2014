package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;
import static java.lang.Math.abs;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Класс @class Writer отвечает за физическую запись информации с на жесткий
 * диск или другой носитель информации. Логика работы устойчива к новым папкам и
 * т.д.
 *
 * @author Timokhin Andrew
 */

public class Writer {
    final int totalSubDir = 16;
    final String dirct = ".dir";

    boolean checkDir(String name) {
        File tmpFile = new File(name);
        return tmpFile.exists();

    }

    void createDir(String path) {
        File tmpDir = new File(path);
        tmpDir.mkdir();
        return;
    }

    void createFile(String path) throws IOException {

        File tmpFile = new File(path);
        File prepareToMakeDir = new File(tmpFile.getParent().toString());
        prepareToMakeDir.mkdirs();
        tmpFile.createNewFile();
        return;
    }

    public void deleteDirectory(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                File tmpFile = new File(dir, children[i]);
                deleteDirectory(tmpFile);
            }
            dir.delete();
        } else {
            dir.delete();
        }
    }

    public void write(TableProviderImplements tp) throws IOException {
        this.deleteDirectory(new File(tp.dir));
        File baseDir = new File(tp.dir);
        baseDir.mkdir();
        if (tp.collection != null) {
            Set<String> dataBase = tp.collection.keySet();
            for (String name : dataBase) {
                TableImplement ti = tp.collection.get(name);
                Path pathToDb = Paths.get(tp.dir, ti.getName());
                File dataBaseDir = new File(pathToDb.toString());
                dataBaseDir.mkdir();
                if (ti.getMap() != null) {
                    Set<String> keyList;
                    keyList = ti.getMap().keySet();
                    for (String keyFind : keyList) {
                        Integer dirToWrite = new Integer(abs(keyFind.hashCode()
                                % totalSubDir));
                        Path localPath = Paths.get(tp.dir, ti.getName(),
                                dirToWrite.toString() + dirct);
                        if (!this.checkDir(localPath.toString())) {
                            this.createDir(localPath.toString());
                        }
                        dirToWrite = new Integer((ti.getMap().get(keyFind)
                                .hashCode()
                                % totalSubDir % totalSubDir));
                        Path localFile = Paths.get(localPath.toString(),
                                dirToWrite.toString());
                        File checkIfExist = new File(localFile.toString());
                        if (!this.checkDir(localFile.toString())
                                && !checkIfExist.exists()) {

                            this.createFile(localFile.toString());
                        }
                        DataOutputStream outStream = new DataOutputStream(
                                new FileOutputStream(localFile.toString(), true));
                        outStream.writeInt(keyFind.length());
                        outStream.writeChars(keyFind);
                        outStream.writeInt(ti.getMap().get(keyFind).toString()
                                .length());
                        outStream.writeChars(ti.getMap().get(keyFind)
                                .toString());
                        outStream.close();
                    }

                }
            }
        }
        return;
    }
}
