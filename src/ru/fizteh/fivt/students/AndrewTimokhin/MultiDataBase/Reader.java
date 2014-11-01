package ru.fizteh.fivt.students.AndrewTimokhin.MultiDataBase;

import java.util.*;
import java.io.*;

public class Reader {
    private TableWork tw = new TableWork();
    private String filepath;

    private Map<String, Object>[] map;

    public Reader(String path) {
        filepath = path;

        map = (Map<String, Object>[]) new Map[16];

    }

    public Map<String, Object>[] read() {
        for (Integer i = 0; i < 16; i++) {
            if (tw.useForReader(filepath, i.toString()) == 1) {
                map[i] = new HashMap<String, Object>();
                StringBuilder keyBuilder = new StringBuilder();
                StringBuilder valueBuilder = new StringBuilder();
                File localbase = new File(filepath + "/" + i.toString());
                String[] list = localbase.list();
                if (list != null) {
                    for (String currentDat : list) {
                        int length = 0;
                        try (DataInputStream rd = new DataInputStream(
                                new FileInputStream(filepath + "/"
                                        + i.toString() + "/" + currentDat))) {
                            while (true) {
                                try {
                                    length = rd.readInt();
                                    for (int k = 0; k < length; k++) {
                                        keyBuilder.append(rd.readChar());

                                    }
                                    length = rd.readInt();
                                    for (int k = 0; k < length; k++) {
                                        valueBuilder.append(rd.readChar());

                                    }
                                    map[i].put(keyBuilder.toString(),
                                            valueBuilder.toString());
                                    keyBuilder.replace(0, keyBuilder.length(),
                                            "");
                                    valueBuilder.replace(0,
                                            valueBuilder.length(), "");
                                    if (i == 15) {
                                        tw.setDir = -1;
                                    }
                                } catch (EOFException e) {
                                    break;
                                }

                            }

                        } catch (FileNotFoundException e) {
                            try {
                                File newdb = new File(filepath);
                                newdb.createNewFile();
                            } catch (IOException err) {
                                System.err.print(err.toString());
                            }
                        } catch (IOException e) {
                            System.err.print(e.toString());
                        }
                    }
                }
            }
        }

        return map;
    }

}

