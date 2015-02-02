/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Commands;

import java.util.ArrayList;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.KeyNullAndNotFound;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.TableProviderImplements;

/**
 *
 * @author Андрей
 */
public class ListCommand extends Commands {

    static public void execute(String[] commands, TableProviderImplements tableProvider) {
        if (commands.length != 1) {
            Commands.invitationToRepeat();
            return;
        } else if (currentTable == null) {
            System.out.println("No table");
            return;
        } else {
            ArrayList<String> set;
            set = (ArrayList<String>) currentTable.list();
            for (String key : set) {
                try {
                    System.out.println(key + " " + currentTable.get(key));
                } catch (KeyNullAndNotFound ex) {
                    System.out.println("Null key is wrong!");
                }
            }
        }
        return;
    }
}
