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
public class RemoveCommand extends Commands {

    static public void execute(String[] commands, TableProviderImplements tableProvider) {
        if (commands.length != 2) {
            Commands.invitationToRepeat();
            return;
        } else if (currentTable == null) {
            System.out.println("No table");
            return;
        } else {
            if (commands[1] == null) {
                System.out.println("*wrong key");
                return;
            }
            String value = currentTable.remove(commands[1]);
            if (value != null) {
                System.out.println("*value* -> " + value);
            } else {
                System.out.println("*not removed, because not exist*");
            }
        }
        return;
    }
}
