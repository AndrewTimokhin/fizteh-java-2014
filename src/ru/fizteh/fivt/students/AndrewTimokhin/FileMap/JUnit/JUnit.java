package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class JUnit {
    public static void main(String[] args) throws IOException {
        FactoryImplements tb = new FactoryImplements();
        TableProviderImplements tp = (TableProviderImplements) tb
                .create("C:\\DataBase");
        Table time  = tp.createTable("new");
        time=tp.getTable("new");

      time.put("1", "1" );
      time.put("2", "2" );
      time.put("3", "3" );
      time.commit();
      time.put("4", "4");
      time.put("5", "5");
      time.put("6", "6");
      time.put("7", "7");
      time.commit();
      time.rollback();
      

    }
}
