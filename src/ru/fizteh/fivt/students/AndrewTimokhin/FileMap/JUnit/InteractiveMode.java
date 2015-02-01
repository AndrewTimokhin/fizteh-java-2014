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
        case "create":
            if (commands.length != 2) {
                this.invitationToRepeat();
                break;
            }
            if (provider.createTable(commands[1]) == null)
                System.out.println("Exist!");
            else
                System.out.println("created");
            break;
        case "put":
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
        case "get":
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
        case "remove":
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
        case "drop":
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
        case "commit":
            if (commands.length != 1) {
                this.invitationToRepeat();
                break;
            }
            if (table != null)
                System.out.println("Total changes -> " + table.commit());
            else
                System.out.println("No table");
            break;

        case "rollback":
            if (commands.length != 1) {
                this.invitationToRepeat();
                break;
            }
            if (table != null)
                System.out.println("Total changes -> " + table.rollback());
            else
                System.out.println("No table");
            break;
        case "use":
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
        case "size":
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
        case "list":
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
        case "exit": {
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
