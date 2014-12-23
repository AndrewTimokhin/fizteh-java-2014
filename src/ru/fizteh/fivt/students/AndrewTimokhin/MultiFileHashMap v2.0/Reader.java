package ru;

import java.util.*;
import java.io.*;

public class Reader {

   private Map<String, Object>[] map;

   public Reader() {

   }

   public Map<String, Object>[] read(String filepath) {

      map = (Map<String, Object>[]) new Map[16];
      for (int ind = 0; ind < 16; ind++) {
         map[ind] = new HashMap<String, Object>();
      }

      int flag = 0;
      File alldir = new File(filepath);
      String [] file = alldir.list();

      for (Integer i = 0; i < 16; i++) {
         for (int j = 0; j < file.length; j++) {
            if (i.toString().equals(file[j].toString())) {
               flag = 1;
               break;
            }
         }
         if (flag == 1) {
                     StringBuilder keyBuilder = new StringBuilder();
            StringBuilder valueBuilder = new StringBuilder();
            File localbase = new File(filepath + "\\" + i.toString());

            String[] list = localbase.list();

            if (list != null) {
               for (String currentDat : list) {

                  int length = 0;
                  try (DataInputStream rd = new DataInputStream(
                        new FileInputStream(filepath + "\\"
                              + i.toString() + "\\" + currentDat))) {
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
         flag = 0;
      }

      return map;
   }

}
