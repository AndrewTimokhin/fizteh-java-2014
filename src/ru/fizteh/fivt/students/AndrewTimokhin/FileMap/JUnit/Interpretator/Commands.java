/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Interpretator;

import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.DataBase.TableImplement;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.DataBase.TableProviderImplements;

/**
 *
 * @author Андрей
 */
public abstract class Commands {

    int numberOfArguments;

    public Commands(int numberOfArgumentsSet) {
        numberOfArguments = numberOfArgumentsSet;

    }

    public abstract TableImplement execute(String[] command, TableProviderImplements table, TableImplement nextTable) throws ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Main.ExitException;

    public boolean invitationToRepeat(String[] args) {

        if (args.length != numberOfArguments) {
            System.out
                    .println("Current command was not recognized or not complete. Please, try again");
            return true;
        }
        return false;
    }

}
