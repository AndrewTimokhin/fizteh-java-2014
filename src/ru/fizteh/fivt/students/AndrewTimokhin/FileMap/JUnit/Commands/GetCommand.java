/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Commands;

import java.util.logging.Level;
import java.util.logging.Logger;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.KeyNullAndNotFound;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.TableProviderImplements;

/**
 *
 * @author Андрей
 */
public class GetCommand extends Commands{
    
    static public boolean execute(String[] commands, TableProviderImplements tableProvider) { 
        if (commands.length != 2) {
                Commands.invitationToRepeat();
                return true;
            } else if (currentTable == null) {
                System.out.println("No table");
                return true;
            } else {
                String value;
            try {
                value = currentTable.get(commands[1]);
                if (value != null)
                    System.out.println("*value* -> " + value);
                else
                    System.out.println("*not exist*");
            } catch (KeyNullAndNotFound ex) {
                System.out.println("Key is Null. Enter correct key");
            }
                 
    }
    return true;
 }
}
