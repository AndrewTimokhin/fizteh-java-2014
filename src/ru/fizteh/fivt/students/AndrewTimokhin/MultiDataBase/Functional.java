package ru;
   
import java.io.IOException;
import java.util.*;

public class Functional {

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

   void list(Map<String, Object>[] map) {
      if (map == null || map.length == 0) {
         System.out.println("DataBase empty!");
         return;
      }
      for (int i = 0; i < 16; i++) {
         if (map[i] != null) {
            Set<String> time = map[i].keySet();
            for (String local : time) {
               System.out.println(time);
            }
         }
      }
      return;
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
            System.out.println("deleted");
         } else {
            System.out.println("not found");
         }
      }
   }

   void showtables(TableDriver[] td) throws IOException, NullPointerException {
      if (td != null) {
         for (int i = 0; i < td.length; i++) {
            if (td[i] != null) {

               System.out.println("in table we have " + td[i].getName()
                     + " " + td[i].size());
            }
         }
   }
   }

}
