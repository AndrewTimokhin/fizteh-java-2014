package ru.fizteh.fivt.students.AndrewTimokhin.MultiDataBase;

import java.util.*;
import java.io.*;
import java.util.regex.*;

public class Write {
    private String filepath;

    public Write(String path) {
        filepath = path;
    }

    public void write(Map<String, Object>[] map) throws IOException {
        for (Integer i = 0; i < 16; i++) {
            if (map[i] != null) {

                for (Integer j = 0; j < 16; j++) {
                    String local = j.toString() + ".dat";
                    File tmp = new File(filepath + "/" + i.toString() + "/"
                            + local);
                    tmp.createNewFile();
                    try (DataOutputStream out = new DataOutputStream(
                            new FileOutputStream(filepath + "/" + i.toString()
                                    + "/" + local))) {
                        Set<String> st = map[i].keySet();
                        for (String time : st) {

                            String tm = local.replaceAll(".dat", "");
                            if ((time.hashCode() / 16 % 16) == Integer
                                    .parseInt(tm)) {

                                out.writeInt(time.length());
                                out.writeChars(time);
                                out.writeInt(((String) map[i].get(time))
                                        .length());
                                out.writeChars((String) map[i].get(time));
                            }

                        }

                    } catch (FileNotFoundException e) {
                        System.err.print("Not Found " + e.toString());
                    } catch (IOException e) {
                        System.err.print("IOException " + e.toString());
                    }
                    if (tmp.length() == 0) {
                        tmp.delete();
                    }
                }
            }
            if (map[i] == null) {
                File delTable = new File(filepath + "/" + i.toString());

                delTable.delete();
            }
        }
    }
}
