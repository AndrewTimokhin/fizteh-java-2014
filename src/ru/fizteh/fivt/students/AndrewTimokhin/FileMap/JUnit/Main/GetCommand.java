/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Main;

import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Interpretator.Commands;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.DataBase.KeyNullAndNotFound;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.DataBase.TableProviderImplements;

/**
 *
 * @author Андрей
 */
public class GetCommand extends Commands {

    public GetCommand() {
        super(2);
    }

    @Override
    public boolean execute(String[] commands, TableProviderImplements tableProvider) {
        if (currentTable == null) {
            System.out.println("No table");
            return true;
        } else {
            String value;
            try {
                value = currentTable.get(commands[1]);
                if (value != null) {
                    System.out.println("*value* -> " + value);
                } else {
                    System.out.println("*not exist*");
                }
            } catch (KeyNullAndNotFound ex) {
                System.out.println("Key is Null. Enter correct key");
            }

        }
        return true;
    }

}
