package ru.fizteh.fivt.students.AndrewTimokhin.MultiDataBase;

import java.io.*;
import java.util.*;

public class TableWork {
    public int setDir = -1; // by default

    public int getset() {
        return setDir;
    }

    public void createforUser(Map<String, Object>[] agr, int numdir) {
        if (agr[numdir] != null) {

            return;
        }
        agr[numdir] = new HashMap<String, Object>();
        return;
    }

    public int create(Map<String, Object>[] agr, String homeDir,
            String tableToAdd) {

        File local = new File(homeDir);
        String[] list;
        list = local.list();
        if (list != null) {
            for (int i = 0; i < list.length; i++) {
                if (list[i].equals(tableToAdd)) {
                    System.out.println(tableToAdd + " exists");
                    return 1; // if exist
                }

            }
        }
        File newtable = new File(homeDir + "/" + tableToAdd);
        newtable.mkdirs();
        if (agr[Integer.parseInt(tableToAdd)] == null) {
            agr[Integer.parseInt(tableToAdd)] = new HashMap<String, Object>();
        }
        System.out.println("created");
        return 0; // if added
    }

    public void dropForUser(Map<String, Object>[] agr, int numdir) {
        if (agr[numdir] == null) {
            return;
        }
        agr[numdir] = null;
        return;

    }

    public int drop(String homeDir, String tableToDel) {
        int haveDir = 0;
        File local = new File(homeDir);
        String[] list;
        list = local.list();
        if (list != null) {
            for (int i = 0; i < list.length; i++) {
                if (list[i].equals(tableToDel)) {
                    haveDir = 1; // if exist
                }
            }
        }
        if (haveDir == 0) {
            System.out.println("not exists");
            return 0;
        }

        File delTable = new File(homeDir + "/" + tableToDel);
        for (File file : new File(homeDir + "/" + tableToDel).listFiles()) {
            if (file.isFile()) {
                file.delete();
            }
        }
        delTable.delete();
        System.out.println(tableToDel + " dropped");
        return 1; // if was deleted
    }

    public int useForReader(String homeDir, String numDir) {
        File local = new File(homeDir);
        String[] list;
        list = local.list();
        if (list != null) {
            for (int i = 0; i < list.length; i++) {
                if (list[i].equals(numDir)) {
                    return 1; // if exist
                }
            }
        }
        return 0; // if n
    }

    public int userUse(String homeDir, String table) {
        File local = new File(homeDir);
        String[] list;
        list = local.list();
        if (list != null) {
            for (int i = 0; i < list.length; i++) {
                if (list[i].equals(table)) {
                    return 1; // if exist
                }
            }
        }
        return 0; // if added
    }
}

