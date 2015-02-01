/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit;

import java.util.Set;

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
    final TableProviderImplements provider;
    final FactoryImplements factory;
    String currentDir;

    InteractiveMode(String path) {
        factory = new FactoryImplements();
        provider = (TableProviderImplements) factory.create(path);
        currentDir = null;
    }

    private void invitationToRepeat() {
        System.out
                .println("Current command was not recognized or not complete. Please, try again");
    }

    boolean command(String textToParser) throws UnknownCommand,
            IllegalArgumentException, KeyNullAndNotFound {
        String[] commands = textToParser.trim().split(" ");
        if (commands.length < 1)
            throw new UnknownCommand(
                    "Current command was not recognized. Programm fail");
        switch (commands[0]) {
        case CREATE_COMMAND:
            if (commands.length != 2) {
                this.invitationToRepeat();
                break;
            }
            if (provider.createTable(commands[1]) == null)
                System.out.println("Exist!");
            else
                System.out.println("created");
            break;
        case PUT_COMMAND:
            if (commands.length != 3) {
                this.invitationToRepeat();
                break;
            } else if (table == null) {
                System.out.println("No table");
                break;
            } else {
                String oldValue = table.put(commands[1], commands[2]);
                if (oldValue != null)
                    System.out.println("*old* -> " + oldValue);
                else
                    System.out.println("*new*");
                break;
            }
        case GET_COMMAND:
            if (commands.length != 2) {
                this.invitationToRepeat();
                break;
            } else if (table == null) {
                System.out.println("No table");
                break;
            } else {
                String value = table.get(commands[1]);
                if (value != null)
                    System.out.println("*value* -> " + value);
                else
                    System.out.println("*not exist*");
                break;
            }
        case REMOVE_COMMAND:
            if (commands.length != 2) {
                this.invitationToRepeat();
                break;
            } else if (table == null) {
                System.out.println("No table");
                break;
            } else {
                if (commands[1] == null) {
                    System.out.println("*wrong key");
                    break;
                }
                String value = table.remove(commands[1]);
                if (value != null)
                    System.out.println("*value* -> " + value);
                else
                    System.out.println("*not removed, because not exist*");
                break;
            }
        case DROP_COMMAND:
            if (commands.length != 2) {
                this.invitationToRepeat();
                break;
            }
            try {
                provider.removeTable(commands[1]);
                System.out.println("dropped");
            } catch (IllegalStateException error) {
                System.out.println("Not exists");
            } catch (IllegalArgumentException error) {
                System.out.println(error);
            }
            break;
        case COMMIT_COMMAND:
            if (commands.length != 1) {
                this.invitationToRepeat();
                break;
            }
            if (table != null)
                System.out.println("Total changes -> " + table.commit());
            else
                System.out.println("No table");
            break;

        case ROLLBACK_COMMAND:
            if (commands.length != 1) {
                this.invitationToRepeat();
                break;
            }
            if (table != null)
                System.out.println("Total changes -> " + table.rollback());
            else
                System.out.println("No table");
            break;
        case USE_COMMAND:
            if (commands.length != 2) {
                this.invitationToRepeat();
                break;
            }
            table = (TableImplement) provider.getTable(commands[1]);
            if (table == null)
                System.out.println("Not exists");
            else {
                if (table.totalChanges() > 0)
                    System.out.println("Need commit->");
                else
                    System.out.println("Using");
            }
            break;
        case  SIZE_COMMAND:
            if (commands.length != 1) {
                this.invitationToRepeat();
                break;
            }
            {
                int totalSize = 0;
                Set<String> set = provider.collection.keySet();
                for (String key : set) {
                    totalSize += provider.collection.get(key).size();
                }
                System.out.println(totalSize);
            }
            break;
        case  LIST_COMMAND:
            if (commands.length != 1) {
                this.invitationToRepeat();
                break;
            } else if (table == null) {
                System.out.println("No table");
                break;
            } else {
                Set<String> set = table.getMap().keySet();
                for (String key : set) {
                    System.out.println(key + " " + table.get(key));
                }
                break;
            }
        case EXIT_COMMAND: {
            if (table != null)
                table.commit();
            return false;
        }
        default:
            this.invitationToRepeat();
            break;
        }
        return true;
    }
}
