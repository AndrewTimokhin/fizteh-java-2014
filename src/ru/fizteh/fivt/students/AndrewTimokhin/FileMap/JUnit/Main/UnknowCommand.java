/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Main;

import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.DataBase.TableImplement;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.DataBase.TableProviderImplements;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Interpretator.Commands;

/**
 *
 * @author Андрей
 */
public class UnknowCommand extends Commands {

    public UnknowCommand() {
        super(0);
    }

    @Override
    public TableImplement execute(String[] command, TableProviderImplements table, TableImplement currentTable) throws ExitException {
        super.invitationToRepeat("not null".split(" "));
        return currentTable;

    }

}
