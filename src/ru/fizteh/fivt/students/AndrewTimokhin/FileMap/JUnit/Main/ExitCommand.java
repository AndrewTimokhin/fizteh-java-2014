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
public class ExitCommand extends Commands {

    public ExitCommand() {
        super(1);
    }

    /**
     * This command needed to exit.
     *
     * @param commands parsed user input
     * @param tableProvider object representing current database
     * @param currentTable current Table
     * @return always return Exception to close, because customer wish is law
     * @throws
     * ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Main.ExitException
     */
    @Override
    public TableImplement execute(String[] commands, TableProviderImplements tableProvider, TableImplement currentTable) throws ExitException {
        if (invitationToRepeat(commands)) {
            return currentTable;
        }
        throw new ExitException("Programm ended");
    }

}
