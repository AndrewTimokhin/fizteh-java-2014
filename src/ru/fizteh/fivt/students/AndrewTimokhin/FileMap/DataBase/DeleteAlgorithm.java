/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.DataBase;

import java.io.File;

/**
 *
 * @author Андрей
 */
public interface DeleteAlgorithm {

    default public void deleteDirectory(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (String child : children) {
                File tmpFile = new File(dir, child);
                deleteDirectory(tmpFile);
            }
            dir.delete();
        } else {
            dir.delete();
        }
    }
}
