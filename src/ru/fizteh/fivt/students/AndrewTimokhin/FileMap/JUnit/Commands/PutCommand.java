/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Commands;

import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.TableProviderImplements;

/**
 *
 * @author Андрей
 */
public class PutCommand extends Commands {

    static public void execute(String[] commands, TableProviderImplements tableProvider) {
        if (commands.length != 3) {
            Commands.invitationToRepeat();
            return;
        } else if (currentTable == null) {
            System.out.println("No table");
            return;
        } else {
            String oldValue = currentTable.put(commands[1], commands[2]);
            if (oldValue != null) {
                System.out.println("*old* -> " + oldValue);
            } else {
                System.out.println("*new*");
            }
        }
        return;
    }
}
