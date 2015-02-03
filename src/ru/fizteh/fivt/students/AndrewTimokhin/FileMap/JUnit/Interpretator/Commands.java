/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Interpretator;

import java.util.HashMap;
import java.util.Map;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.DataBase.TableImplement;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.DataBase.TableProviderImplements;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Main.CommitCommand;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Main.CreateCommand;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Main.DropCommand;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Main.ExitCommand;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Main.GetCommand;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Main.ListCommand;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Main.PutCommand;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Main.RemoveCommand;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Main.RollbackCommand;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Main.SizeCommand;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Main.UseCommand;

/**
 *
 * @author Андрей
 */
public class Commands {

    int digital;
    public static TableImplement currentTable;
    private Map<String, Commands> totalCommands;

    public Commands(int digital) {
        this.digital = digital;

    }

    public Commands() {
    }

    public Map<String, Commands> getAllCommand() {
        totalCommands = new HashMap<>();
        totalCommands.put("create", new CreateCommand());
        totalCommands.put("put", new PutCommand());
        totalCommands.put("get", new GetCommand());
        totalCommands.put("remove", new RemoveCommand());
        totalCommands.put("drop", new DropCommand());
        totalCommands.put("commit", new CommitCommand());
        totalCommands.put("rollback", new RollbackCommand());
        totalCommands.put("use", new UseCommand());
        totalCommands.put("size", new SizeCommand());
        totalCommands.put("list", new ListCommand());
        totalCommands.put("exit", new ExitCommand());
        return totalCommands;
    }

    public boolean checkAndRun(String[] command, TableProviderImplements table) {
        if (this.invitationToRepeat(command, digital)) {
            return true;
        }
        return this.execute(command, table);

    }

    public boolean execute(String[] command, TableProviderImplements table) {
        return true; 
    }

    public boolean invitationToRepeat(String[] args, int checkValue) {

        if (args.length != checkValue) {
            System.out
                    .println("Current command was not recognized or not complete. Please, try again");
            return true;
        }
        return false;
    }

}
