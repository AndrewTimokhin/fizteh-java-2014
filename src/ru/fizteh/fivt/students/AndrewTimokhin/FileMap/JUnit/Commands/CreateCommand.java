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
public class CreateCommand extends Commands{

    static public boolean execute(String[] commands, TableProviderImplements tableProvider) {
        if (commands.length != 2) {
                Commands.invitationToRepeat();
                return true;
            }
            if (tableProvider.createTable(commands[1]) == null) 
                System.out.println("Exist!");
            else
                System.out.println("created");
            return true;
    }
}
