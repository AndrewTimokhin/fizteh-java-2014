/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit;

import java.util.Set;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Commands.CommitCommand;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Commands.CreateCommand;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Commands.DropCommand;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Commands.ExitCommand;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Commands.GetCommand;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Commands.ListCommand;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Commands.PutCommand;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Commands.RemoveCommand;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Commands.RollbackCommand;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Commands.SizeCommand;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Commands.UseCommand;

/**
 *
 * @author Timokhin Andrew
 * 
 */
public class InteractiveMode {
    
    static final String CREATE_COMMAND = "create";
    static final String PUT_COMMAND = "put";
    static final String GET_COMMAND = "get";
    static final String REMOVE_COMMAND = "remove";
    static final String DROP_COMMAND = "drop";
    static final String COMMIT_COMMAND = "commit";
    static final String ROLLBACK_COMMAND = "rollback";
    static final String USE_COMMAND = "use";
    static final String SIZE_COMMAND = "size";
    static final String LIST_COMMAND = "list";
    static final String EXIT_COMMAND = "exit";
    
    private TableImplement table;
    final private TableProviderImplements provider;
    final private FactoryImplements factory;
    private String currentDir;

    InteractiveMode(String path) {
        factory = new FactoryImplements();
        provider = (TableProviderImplements) factory.create(path);
    }

    boolean command(String textToParser) throws UnknownCommand,
            IllegalArgumentException, KeyNullAndNotFound {
        String[] commands = textToParser.trim().split(" ");
        if (commands.length < 1)
            throw new UnknownCommand(
                    "Current command was not recognized. Programm fail");
        switch (commands[0]) {
        case CREATE_COMMAND:
        CreateCommand.execute(commands, provider)    ;
            break;
        case PUT_COMMAND:
        PutCommand.execute(commands, provider);
                break;
        case GET_COMMAND:
        GetCommand.execute(commands, provider);     
                break;
        case REMOVE_COMMAND:
        RemoveCommand.execute(commands, provider);       
                break;
        case DROP_COMMAND:
        DropCommand.execute(commands, provider);        
            break;
        case COMMIT_COMMAND:
        CommitCommand.execute(commands, provider); 
            break;
         case ROLLBACK_COMMAND:
        RollbackCommand.execute(commands, provider);  
            break;
        case USE_COMMAND:
        UseCommand.execute(commands, provider);    
            break;
        case  SIZE_COMMAND:
        SizeCommand.execute(commands, provider);       
            break;
        case  LIST_COMMAND:
        ListCommand.execute(commands, provider);          
                break;
        case EXIT_COMMAND: {
        return ExitCommand.execute(commands, provider);
         }
        default:
            System.out.println("Wrong command!");
            break;
        }
        return true;
    }
}
