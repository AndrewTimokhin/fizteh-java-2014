/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Interpretator;

import java.util.Map;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.DataBase.KeyNullAndNotFound;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Main.UnknowCommand;

/**
 *
 * @author Timokhin Andrew
 *
 */
public class InteractiveMode {

    private final Map<String, Commands> allCommand;
    private final Commands fail;

    public InteractiveMode(Map<String, Commands> allCommand) {
        this.allCommand = allCommand;
        fail = new UnknowCommand();
    }

    public Commands runCommand(String textToParser) throws UnknownCommandException,
            IllegalArgumentException, KeyNullAndNotFound {
        String[] commands = textToParser.trim().split(" ");
        if (commands.length < 1 || !allCommand.containsKey(commands[0])) {
            System.out.println("Details: Wrong command!");
            return fail;
        }
        return allCommand.get(commands[0]);

    }
}
