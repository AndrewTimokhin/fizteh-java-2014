/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Main;

import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Interpretator.Commands;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.DataBase.TableImplement;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.DataBase.TableProviderImplements;

/**
 *
 * @author Андрей
 */
public class UseCommand extends Commands {

    public UseCommand() {
        super(2);
    }

    @Override
    public TableImplement execute(String[] commands, TableProviderImplements tableProvider, TableImplement currentTable) {
        if (invitationToRepeat(commands)) {
            return currentTable;
        }

        currentTable = (TableImplement) tableProvider.getTable(commands[1]);

        if (currentTable == null) {
            System.out.println("Not exists");
        } else {
            if (currentTable.totalChanges() > 0) {
                System.out.println("Need commit->" + currentTable.totalChanges());
            } else {
                System.out.println("Using");
            }
        }
        return currentTable;
    }

}
