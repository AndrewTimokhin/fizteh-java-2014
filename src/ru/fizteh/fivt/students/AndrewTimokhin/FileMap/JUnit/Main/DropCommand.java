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
public class DropCommand extends Commands {

    public DropCommand() {
        super(2);
    }

    @Override
    public TableImplement execute(String[] commands, TableProviderImplements tableProvider, TableImplement currentTable) throws IllegalStateException {
        if (invitationToRepeat(commands)) {
            return currentTable;
        }
        try {
            tableProvider.removeTable(commands[1]);
            System.out.println("dropped");
        } catch (IllegalStateException | IllegalArgumentException error) {
            throw error;
        }
        return currentTable;
    }

}
