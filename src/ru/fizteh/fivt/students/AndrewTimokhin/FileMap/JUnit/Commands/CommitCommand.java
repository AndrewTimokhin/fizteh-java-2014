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
public class CommitCommand extends Commands {

    static public void execute(String[] commands, TableProviderImplements tableProvider) {
        if (commands.length != 1) {
            Commands.invitationToRepeat();
            return;
        }
        if (currentTable != null) {
            System.out.println("Total changes -> " + currentTable.commit());
        } else {
            System.out.println("No table");
        }
        return;
    }
}
