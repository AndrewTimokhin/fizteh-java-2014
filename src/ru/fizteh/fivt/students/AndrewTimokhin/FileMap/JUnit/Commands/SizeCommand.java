/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.Commands;

import java.util.Set;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit.TableProviderImplements;

/**
 *
 * @author Андрей
 */
public class SizeCommand extends Commands{

    static public boolean execute(String[] commands, TableProviderImplements tableProvider) {
    if (commands.length != 1) {
                Commands.invitationToRepeat();
                return true;
            }
            {
                int totalSize = 0;
                Set<String> set = tableProvider.collection.keySet();
                for (String key : set) {
                    totalSize += tableProvider.collection.get(key).size();
                }
                System.out.println(totalSize);
            }
            return true;
}
}
