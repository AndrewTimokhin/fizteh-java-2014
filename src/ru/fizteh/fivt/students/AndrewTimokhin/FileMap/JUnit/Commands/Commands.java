/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Commands;

import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.TableImplement;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.TableProviderImplements;

/**
 *
 * @author Андрей
 */
public class Commands {
 static TableImplement currentTable;
 static public boolean execute(String[] command, TableProviderImplements table) {
 return false; //be-default    
 };
  static void invitationToRepeat() {
        System.out
                .println("Current command was not recognized or not complete. Please, try again");
    }
}
