package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit;

import java.io.IOException;

public class JUnit {
    public static void main(String[] args) throws IOException,
            IllegalArgumentException, KeyNullAndNotFound {
        FactoryImplements tb = new FactoryImplements();
        TableProviderImplements tp = (TableProviderImplements) tb
                .create("C:\\DataBase");
        Table tableA1 = tp.createTable("A1");
        tableA1 = tp.getTable("A1");

        /*
         * Uncomment to see, that we have, when we run this programm twice. A1
         * must be : (1,1); (2,2); (3,3); A2 must be : all changes
         */
        /*
         * System.out.println("<===twice===>\n"); for (String tm :
         * tableA1.list()) { System.out.println("key -> " + tm + " value -> " +
         * tableA1.get(tm) ); } System.out.println("\n<===twice===>\n");
         */

        tableA1.put("1", "1");
        tableA1.put("2", "2");
        tableA1.put("3", "3");
        tableA1.commit();
        tableA1.put("4", "4");
        tableA1.put("5", "5");
        tableA1.put("6", "6");
        tableA1.put("7", "7");
        tableA1.commit();
        tableA1.rollback();

        for (String tm : tableA1.list()) {
            System.out.println("key -> " + tm + " value -> " + tableA1.get(tm));
        }

        Table tableA2 = tp.createTable("A2");
        tableA2 = tp.getTable("A2");

        /*
         * System.out.println("<===twice===> \n"); for (String time:
         * tableA2.list()){ System.out.println("key -> " + time + " value -> " +
         * tableA2.get(time) ); } System.out.println("\n<===twice===> \n");
         */

        tableA2.put("1", "green");
        tableA2.put("2", "blue");

        System.out.println("<========NEXT========>\n");
        tableA2.remove("1");

        for (String time : tableA2.list()) {
            System.out.println("key -> " + time + " value -> "
                    + tableA2.get(time));
        }

        tableA2.commit();
        tableA2.put("3", "green");
        tableA2.put("4", "black");
        tableA2.put("5", "red");
        tableA2.commit();
        try {
            tableA2.get(null);
        } catch (IllegalArgumentException e) {
            System.out.println("\n" + e + "\n" + e.getCause() + "\n");
        }
        System.out.println(tableA2.get("4"));

    }
}
