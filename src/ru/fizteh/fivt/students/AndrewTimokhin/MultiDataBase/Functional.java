package ru.fizteh.fivt.students.AndrewTimokhin.MultiDataBase;

import java.util.*;

class Functional {
    public Map<String, Object>[] map;

    public Functional(Map<String, Object>[] map) {
        this.map = map;
    }

    void put(Map<String, Object>[] map, int i, String key, String value) {
        if (i == -1) {
            System.out.println("NotDefBD#");
            return;
        }

        if (map[i].containsKey(key)) {
            System.out.println("overwrite");
            System.out.println(map[i].get(key));
        } else {
            System.out.println("new");
        }
        map[i].put(key, value);
         }

    void get(Map<String, Object>[] map, int i, String key) {
        if (i == -1) {
            System.out.println("NotDefBD#");
            return;
        }
        if (map[i] != null) {
            if (map[i].containsKey(key)) {
                System.out.println("found");
                System.out.println(map[i].get(key));
            } else {
                System.out.println("not found");
            }
        }
               }

    void remove(Map<String, Object>[] map, int i, String key) {
        if (i == -1) {
            System.out.println("NotDefBD#");
            return;
        }
        if (map[i] != null) {
            if (map[i].containsKey(key)) {
                map[i].remove(key);
            } else {
                System.out.println("not found");
            }
        }
                }

    void showtables() {
        for (int i = 0; i < 16; i++) {
            if (map[i] != null) {
                if (map[i].size() == 0) {
                    map[i] = null;
                }
            }
    }
                       for (int i = 0; i < 16; i++) {
            if (map[i] != null) {

                System.out.println("table " + i + " " + map[i].size());
            }
        }

    }
}

