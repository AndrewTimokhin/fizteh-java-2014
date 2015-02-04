/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit;

import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.DataBase.FactoryImplements;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.DataBase.TableProvider;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.DataBase.TableProviderFactory;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author Андрей
 */
public class TableProviderImplementsTest {

    @Rule
    public TemporaryFolder tmp = new TemporaryFolder();
    public TableProvider tp;

    @Before
    public void setUp() {
        TableProviderFactory tpv = new FactoryImplements();
        tp = tpv.create(tmp.newFolder("time").getAbsolutePath());
    }

    /**
     * Следующие тесты полностью тестируют реализацию интерфейса
     */
    /**
     * Тесты getTable метода
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetTable() {
        tp.getTable(null);
    }

    @Test
    public void testGetTableNormal() {
        tp.createTable("test");
        assertNotNull(tp.getTable("test"));
    }

    @Test
    public void testGetTableIfDnExist() {
        tp.createTable("test");
        assertNull(tp.getTable("blablabla"));
    }

    /**
     * Тесты createTable метода
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateTable() {
        tp.createTable(null);
    }

    @Test
    // в данном тесте логично объединено два теста
    public void testCreateTableIfExist() {
        assertNotNull(tp.createTable("test"));
        assertNull(tp.createTable("test"));
    }

    /**
     * Тесты removeTable метода
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveTable() {
        tp.removeTable(null);

    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveTableDnExist() {
        tp.removeTable("notexist");
    }

    @Test
    public void testRemoveTableIfExist() {
        tp.createTable("testdb");
        tp.removeTable("testdb");
        assertNull(tp.getTable("testdb"));
    }
}
