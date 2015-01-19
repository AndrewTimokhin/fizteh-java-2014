package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Класс @class Writer отвечает за физическую запись информации с на жесткий
 * диск или другой носитель информации. Логика работы устойчива к новым папкам и
 * т.д.
 *
 * @author Timokhin Andrew
 */

public class Writer {

    boolean checkDir(String name) {
        File time = new File(name);
        return time.exists();

    }

    void createDir(String path) {
        File time = new File(path);
        time.mkdir();
        //System.out.println(path);
        return;
    }

    void createFile(String path) throws IOException {

        File time = new File(path);

        File prepareToMakeDir = new File(time.getParent().toString());
        prepareToMakeDir.mkdirs();
        //System.out.println(prepareToMakeDir.getAbsolutePath());
        time.createNewFile();
        return;
    }

    public void deleteDirectory(File dir) {

        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                File f = new File(dir, children[i]);
                deleteDirectory(f);
            }
            dir.delete();
        } else
            dir.delete();
    }

    public int abs(int digital) {
        if (digital < 0)
            return -digital;
        return digital;
    }

    public void write(TableProviderImplements tp) throws IOException {

        this.deleteDirectory(new File(tp.dir));
        File fl = new File(tp.dir);
        fl.mkdir();
        if (tp.t != null)
            for (TableImplement ti : tp.t) {
                File db = new File(tp.dir + "\\" + ti.name);
                db.mkdir();
                if (ti.map != null) {
                    Set<String> keyList;
                    keyList = ti.map.keySet();
                    for (String keyFind : keyList) {
                        Integer dirToWrite = new Integer(
                                abs(keyFind.hashCode() % 16)); // номер
                                                               // директории для
                                                               // записи
                        String localPath = tp.dir + "\\" + ti.name + "\\"
                                + dirToWrite.toString() + ".dir";
                        if (this.checkDir(localPath) == false)
                            this.createDir(localPath);
                        dirToWrite = new Integer(abs(ti.map.get(keyFind)
                                .hashCode() % 16 % 16)); // номер файла для
                                                         // записи
                        String localfile = localPath + "\\"
                                + dirToWrite.toString();
                        if (this.checkDir(localfile) == false) {
                            this.createFile(localfile);
                        }
                        DataOutputStream out = new DataOutputStream(
                                new FileOutputStream(localfile));
                        out.writeInt(keyFind.length());
                        out.writeChars(keyFind);
                        out.writeInt(ti.map.get(keyFind).length());
                        out.writeChars(ti.map.get(keyFind));
                        out.close();
                    }

                }
            }
        return;
    }

}
