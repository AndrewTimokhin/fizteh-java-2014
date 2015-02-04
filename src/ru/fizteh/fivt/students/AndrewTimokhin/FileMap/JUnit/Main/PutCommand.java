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
public class PutCommand extends Commands {

    public PutCommand() {
        super(3);
    }

    @Override
    public TableImplement execute(String[] commands, TableProviderImplements provider, TableImplement currentTable) {
        if (invitationToRepeat(commands)) {
            return currentTable;
        }
        if (currentTable == null) {
            System.out.println("No table");
            return null;
        } else {
            String oldValue = currentTable.put(commands[1], commands[2]);
            if (oldValue != null) {
                System.out.println("*old* -> " + oldValue);
            } else {
                System.out.println("*new*");
            }
        }
        return currentTable;
    }

}
