package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;
import static java.lang.Math.abs;

/**
 * Класс @class Writer отвечает за физическую запись информации с на жесткий
 * диск или другой носитель информации. Логика работы устойчива к новым папкам и
 * т.д.
 *
 * @author Timokhin Andrew
 */

public class Writer {

    boolean checkDir(String name) {
        File tmpFile = new File(name);
        return tmpFile.exists();

    }

    void createDir(String path) {
        File tmpDir = new File(path);
        tmpDir.mkdir();
        // System.out.println(path);
        return;
    }

    void createFile(String path) throws IOException {

        File tmpFile = new File(path);

        File prepareToMakeDir = new File(tmpFile.getParent().toString());
        prepareToMakeDir.mkdirs();
        // System.out.println(prepareToMakeDir.getAbsolutePath());
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
            for (TableImplement ti : tp.collection) {
                File dataBaseDir = new File(tp.dir + "\\" + ti.getName());
                dataBaseDir.mkdir();
                if (ti.getMap() != null) {
                    Set<String> keyList;
                    keyList = ti.getMap().keySet();
                    for (String keyFind : keyList) {
                        Integer dirToWrite = new Integer(
                                abs(keyFind.hashCode() % 16));
                        String localPath = tp.dir + "\\" + ti.getName() + "\\"
                                + dirToWrite.toString() + ".dir";
                        if (!this.checkDir(localPath)) {
                            this.createDir(localPath);
                        }
                        dirToWrite = new Integer(abs(ti.getMap().get(keyFind)
                                .hashCode() % 16 % 16));
                        String localFile = localPath + "\\"
                                + dirToWrite.toString();
                        if (!this.checkDir(localFile)) {
                            this.createFile(localFile);
                        }
                        DataOutputStream outStream = new DataOutputStream(
                                new FileOutputStream(localFile));
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
