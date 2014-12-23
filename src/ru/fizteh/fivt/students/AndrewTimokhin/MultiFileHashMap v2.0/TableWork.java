package ru;

import java.io.*;
import java.security.AccessControlException;
import java.util.*;

public class TableWork {
   public TableDriver [] t ;
   String homeDir;
   int indicator;
   int size;

   TableWork(int size, String homeDir) {
      Reader rd = new Reader();
      this.size = 0;
      File input = new File(homeDir);
      String[] st;
      st = input.list();
      if (st != null) {
         for (String path : st) {

            File tm2 = new File(homeDir + "\\" + path);

            if (tm2.list() != null) {

               if (size == 0) {
                  t = new TableDriver[1];
                  t[0] = new TableDriver(path);
                  t[0].map = rd.read(homeDir + "\\" + path);

               } else {
                  TableDriver [] temp = new TableDriver[t.length + 1];
                  for (int k = 0; k < t.length; k++) {
                      temp[k] = t[k]; 
                     }
                  temp[t.length] = new TableDriver(path);
                  temp[t.length].map = rd.read(homeDir + "\\" + path);

                  t = temp;
               }
               size++;
            }

         }
      }

      this.homeDir = homeDir;

      indicator = -1;
   }

   public int setDir = -1; // by default

   public int getset() {
      return setDir;
   }

   public int create(String tableToAdd) throws NullPointerException {

      if (t != null) {
         for (int i = 0; i < t.length; i++) {
            if (tableToAdd.equals(t[i].getName())) {
               System.out.println("exist!");
               return 1;
            }
         }
                  {
            TableDriver [] temp  = new TableDriver[t.length + 1];
            for (int k = 0; k < t.length; k++) {
                 temp[k] = t[k]; 
               }

            temp[t.length] = new TableDriver(tableToAdd);
            t = temp;
            // System.out.println("created");
         }
      }

      if (t == null || size == 0) {
         t = new TableDriver[1];
         t[0] = new TableDriver(tableToAdd);
      }
      System.out.println("created");
      size++;
      return 0; // if added
   }

   public void remove(File anyfile) {
      if (!anyfile.exists()) {
         return; 
         }
      if (anyfile.isDirectory()) {
         for (File f : anyfile.listFiles()) {
            remove(f); }
         anyfile.delete();
      } else {
         anyfile.delete();
      }
   }

   public void drop(String tableToDel) throws NullPointerException,
         IOException {
      int haveDir = 0;
      for (int i = 0; i < t.length; i++) {
         if (t[i] != null && tableToDel.equals(t[i].getName())) {

            if (t.length != 1) {
               TableDriver tm = new TableDriver();
               t[i] = t[t.length - 1];
               t[t.length - 1] = null;

            } else {
               t[i] = null; 
               }
            size--;
            System.out.println(tableToDel + " dropped");
            File in = new File(homeDir);
            String [] st = in.list();

            if (st != null) {
               for (String time : st) {
                  if (time.equals(tableToDel)) {
                     try {
                        in = new File(homeDir + "\\" + tableToDel);

                        remove(in);
                     } catch (AccessControlException e) {
                        System.out.println("Access forbidden!");
                     }

                  }

               }
            }

            // if was deleted
            break;
         }

      }
      if (haveDir == 0) {
         System.out.println("not exists");

      }

   }

   public int userUse(String tablename) throws NullPointerException {
      if (t != null && t[0] != null) {
         for (int i = 0; i < t.length; i++) {
            if (t[i].getName().equals(tablename)) {
               indicator = i;
               System.out.println("use");
               return 0;
            }
         } 
         }
      System.out.println("not exist");
      return 1;

   }

}
