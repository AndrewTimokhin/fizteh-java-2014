/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Main;

import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.DataBase.TableImplement;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Interpretator.Commands;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.DataBase.TableProviderImplements;

/**
 *
 * @author Андрей
 */
public class CommitCommand extends Commands {

    public CommitCommand() {
        super(1);
    }

    @Override
    public TableImplement execute(String[] commands, TableProviderImplements tableProvider, TableImplement currentTable) {

        if (invitationToRepeat(commands)) {
            return currentTable;
        }
        if (currentTable != null) {
            System.out.println("Total changes -> " + currentTable.commit());
        } else {
            System.out.println("No table");
        }
        return currentTable;
    }
}
