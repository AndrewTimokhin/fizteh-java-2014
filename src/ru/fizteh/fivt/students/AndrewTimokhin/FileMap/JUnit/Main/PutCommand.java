/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Main;

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
    public boolean execute(String[] commands, TableProviderImplements tableProvider) {
        if (currentTable == null) {
            System.out.println("No table");
            return true;
        } else {
            String oldValue = currentTable.put(commands[1], commands[2]);
            if (oldValue != null) {
                System.out.println("*old* -> " + oldValue);
            } else {
                System.out.println("*new*");
            }
        }
        return true;
    }

}
