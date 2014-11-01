package ru.fizteh.fivt.students.AndrewTimokhin.MultiDataBase;

import java.util.*;

class ModeWork {
    String dir;
    TableWork tw = new TableWork();

    public ModeWork(Map<String, Object>[] time, String direct) {
        dir = direct;
    }

    int stepMode(String[] array, Functional f, int index) {

        switch (array[0 + index]) {
        case "put":
            f.put(f.map, tw.setDir, array[1 + index], array[2 + index]);
            return 2;
        case "get":
            f.get(f.map, tw.setDir, array[1 + index]);
            return 1;
        case "remove":
            f.remove(f.map, tw.setDir, array[1 + index]);
            return 1;
        case "create":
            tw.create(f.map, dir, array[1 + index]);
            tw.createforUser(f.map, Integer.parseInt(array[1 + index]));
            return 1;
        case "drop":
            tw.drop(dir, array[1 + index]);
            tw.dropForUser(f.map, Integer.parseInt(array[1 + index]));
            return 1;
        case "use":
            if (tw.userUse(dir, array[1 + index]) == 0) {
                System.out.println("not exist");
            } else {
                System.out.println("using " + array[1 + index]);
            }
            tw.setDir = Integer.parseInt(array[1 + index]
                    .replaceAll(".dat", ""));
            return 1;

        case "showtables":
            f.showtables();
            return 1;
        case "exit":
            return -1;
        default:
            System.out.println("Unknow operation. Fail.");
            return -1;
        }
    }

    void usermode(Functional func) {

        String str = new String();
        String[] array;
        Scanner rd = new Scanner(System.in);
        while (true) {
            System.out.print("$ ");
            str = rd.nextLine().toString();
            array = str.trim().split(" ");

            if (stepMode(array, func, 0) == -1) {
                break;
            }
        }
        rd.close();

    }

    /*
     * void interactive( Map<String, Object>[] time , String[] mass) { int
     * offset = 0; Functional func = new Functional(time); int i = 0; while
     * (true) { if (i < mass.length) { offset = stepMode(time,mass, func, i); i
     * += offset; if (offset == -1) { break; } i++; } else { break; } }
     * 
     * }
     */

}

