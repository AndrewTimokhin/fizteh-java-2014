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
public class ExitCommand extends Commands {

    public ExitCommand() {
        super(1);
    }

    @Override
    public boolean execute(String[] commands, TableProviderImplements tableProvider) {
        return false;
    }

}
