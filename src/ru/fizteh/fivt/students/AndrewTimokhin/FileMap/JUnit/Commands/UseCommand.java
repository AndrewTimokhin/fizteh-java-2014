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
public class UseCommand extends Commands{

    public static boolean execute(String[] commands, TableProviderImplements tableProvider) {
                    if (commands.length != 2) {
                Commands.invitationToRepeat();
                return true;
            }
            currentTable = (TableImplement) tableProvider.getTable(commands[1]);
            if (currentTable == null)
                System.out.println("Not exists");
            else {
                if (currentTable.totalChanges() > 0)
                    System.out.println("Need commit->");
                else
                    System.out.println("Using");
    }
  return false;  
 }
}