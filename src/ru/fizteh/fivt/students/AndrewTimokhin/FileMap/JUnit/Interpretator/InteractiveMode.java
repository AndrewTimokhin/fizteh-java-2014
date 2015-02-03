/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Interpretator;

import java.util.Map;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.DataBase.FactoryImplements;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.DataBase.KeyNullAndNotFound;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.DataBase.TableImplement;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.DataBase.TableProviderImplements;

/**
 *
 * @author Timokhin Andrew
 *
 */
public class InteractiveMode {
    
    private final TableProviderImplements provider;
    private final FactoryImplements factory;
    private TableImplement table;
    private String currentDir;
    private Map<String, Commands> allCommand;

    public InteractiveMode(String path, Commands command) {
        allCommand = command.getAllCommand();
        factory = new FactoryImplements();
        provider = (TableProviderImplements) factory.create(path);
    }

    public boolean command(String textToParser) throws UnknownCommand,
            IllegalArgumentException, KeyNullAndNotFound {
        String[] commands = textToParser.trim().split(" ");
        if (commands.length < 1 || !allCommand.containsKey(commands[0])) {
            System.out.println("Wrong command!");
            return true;
        }
        return allCommand.get(commands[0]).checkAndRun(commands, provider);

    }
}
