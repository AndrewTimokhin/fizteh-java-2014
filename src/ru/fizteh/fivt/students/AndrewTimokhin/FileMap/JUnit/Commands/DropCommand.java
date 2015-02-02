/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Commands;

import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.TableProviderImplements;

/**
 *
 * @author Андрей
 */
public class DropCommand extends Commands{

    static public boolean execute(String[] commands, TableProviderImplements tableProvider) {
    if (commands.length != 2) {
                Commands.invitationToRepeat();
                return true;
            }
            try {
                tableProvider.removeTable(commands[1]);
                System.out.println("dropped");
            } catch (IllegalStateException error) {
                System.out.println("Not exists");
            } catch (IllegalArgumentException error) {
                System.out.println(error);
            }
            return true;
}
}