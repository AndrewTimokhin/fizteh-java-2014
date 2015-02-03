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
public class RemoveCommand extends Commands {

    public RemoveCommand() {
        super(2);
    }

    @Override
    public boolean execute(String[] commands, TableProviderImplements tableProvider) {
        if (currentTable == null) {
            System.out.println("No table");
            return true;
        } else {
            if (commands[1] == null) {
                System.out.println("*wrong key");
                return true;
            }
            String value = currentTable.remove(commands[1]);
            if (value != null) {
                System.out.println("*value* -> " + value);
            } else {
                System.out.println("*not removed, because not exist*");
            }
        }
        return true;
    }

}
