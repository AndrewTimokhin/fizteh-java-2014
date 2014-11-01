package ru.fizteh.fivt.students.AndrewTimokhin.MultiDataBase;

import java.io.*;
import java.util.*;

public class MainClass {

    public static void main(String[] args) {
        @SuppressWarnings("unchecked")
        Map<String, Object>[] map = (Map<String, Object>[]) new Map[16];
        for (int i = 0; i < 16; i++) {
            map[i] = new HashMap<String, Object>();
        }

        Reader rd = new Reader(System.getProperty("fizteh.db.dir").toString());
        map = rd.read();
        Write wr = new Write(System.getProperty("fizteh.db.dir").toString());
        Functional fn = new Functional(map);
        ModeWork mw = new ModeWork(map, System.getProperty("fizteh.db.dir")
                .toString());
        mw.usermode(fn);

        try {
            wr.write(map);

        } catch (IOException e) {
            System.out.println(e.toString());
        }

    }
}

