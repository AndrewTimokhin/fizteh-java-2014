package ru;

import java.util.HashMap;
import java.util.Map;

public class TableDriver {
   private String name;
   public Map<String, Object>[] map;

   TableDriver() {

   }

   TableDriver(String name) {
      this.name = name;
      map = (Map<String, Object>[]) new Map[16];
      for (int i = 0; i < 16; i++) {
         map[i] = new HashMap<String, Object>();
      }

   }

   public int size() {
      int summ = 0;
      if (map != null) {
         for (int i = 0; i < 16; i++) {
            if (map[i] != null) {
               summ += map[i].size();
            }
         }
      }
      return summ;
   }

   public String getName() {
      return name;
   }
}
