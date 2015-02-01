/*
 * Модульные тесты базы данных.
 * 
 */
package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import java.lang.IllegalArgumentException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Timokhin Andrew
 */
public class TableImplementTest {

    @Rule
    public TemporaryFolder tmp = new TemporaryFolder();
    public TableProvider tableProvider;
    public String path;
    public Table table;

    @Before
    public void setUp() {
        TableProviderFactory tpv = new FactoryImplements();
        path = tmp.newFolder("time").getAbsolutePath();
        tableProvider = tpv.create(path);
        table = tableProvider.createTable("testing");

        FactoryImplements factory = new FactoryImplements();
        TableProviderImplements tableProvider = (TableProviderImplements) factory
                .create("test");
        Table time = tableProvider.createTable("new");
        time = tableProvider.getTable("new");

    }

    /**
     * Тестирование getName (большо одного нет смысла)
     */
    @Test
    public void testGetName() {
        assertTrue("testing".equals(table.getName()));
    }

    /**
     * Тесты на size method
     */
    @Test
    public void testSizeByDefault() {
        assertEquals(0, table.size());
    }

    @Test
    public void testSizeAfterAddedAndDel() {
        table.put("1", "11");
        table.put("2", "22");
        int result = table.size();
        assertEquals(2, result);
        table.remove("2");
        result = table.size();
        assertEquals(1, result);
        table.remove("1");
        result = table.size();
        assertEquals(0, result);
    }

    /**
     * Тестирование put метода
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPutIfKeyIsNull() {
        String value = "test_value";
        table.put(null, value);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPutIfValueIsNull() {
        String key = "test_key";
        table.put(key, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPutIfValueAndKeyIsNull() {
        table.put(null, null);
    }

    @Test
    public void testPutIfKeyDnHaveInDataBase() {
        String key = "test_key";
        String value = "test_value";
        assertNull(table.put(key, value));
    }

    @Test
    public void testPutIfKeyHaveInDataBase() {
        String key = "test_key";
        String valueOld = "test_value_old";
        String valueNew = "test_value_new";

        assertNull(table.put(key, valueOld));
        assertEquals(valueOld, table.put(key, valueNew));
    }

    /**
     * Тестирование remove метода
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveIfKeyNull() {
        table.remove(null);
    }

    @Test
    public void testRemoveIfKeyExist() {
        String key = "key_test";
        String value = "value_test";
        table.put(key, value);

        assertEquals(value, table.remove(key));
        try {
            assertNull(table.get(key));
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TableImplementTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyNullAndNotFound ex) {
            Logger.getLogger(TableImplementTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testRemoveIfKeyDnExist() {
        String key = "key_test";

        assertNull(table.remove(key));
    }

    /**
     * Тестирование метода commit
     */
    @Test
    public void testCommitIfEmpty() {
        assertEquals(0, table.commit());
    }

    @Test
    public void testCommitIfFullAnything() {
        table.put("id564", "989");
        table.put("id988", "123");
        table.put("id123", "766");
        assertEquals(3, table.commit());
    }

    public void testCommitIfWasRollback() {
        table.put("id564", "989");
        table.put("id988", "123");
        table.put("id123", "766");
        table.rollback();

        assertEquals(0, table.commit());
    }
    
    public void testCommitAndCommit() {
        table.put("id1", "1");
        table.put("id2", "2");
        table.put("id3", "3");
        table.commit();
        table.commit();
        assertEquals(0, table.commit());
    }

    /**
     * Тестирование rollback метода
     */
    @Test
    public void testRollbackIfStateEmpty() {
        assertEquals(0, table.rollback());
    }

    @Test(expected = NullPointerException.class)
    public void testRollbackNullPointer() {
        table.put("id564", "989");
        table.put("id988", "123");
        table.put("id123", "766");
        table.commit();
        table.rollback();
    }

    @Test
    public void testRollbackSomeChainChanges() {
        table.put("1", "1");
        table.put("2", "2");
        table.put("3", "3");
        table.commit();
        table.put("4", "4");
        table.put("54", "54");
        table.commit();
        assertEquals(2, table.rollback());
    }
    
     @Test
    public void testCommitCheckRollbackCheck() throws IllegalArgumentException, KeyNullAndNotFound {
        table.put("1", "11");
        table.put("2", "22");
        table.put("3", "33");
        table.commit();
        assertEquals("11", table.get("1"));
        assertEquals("22", table.get("2"));
        assertEquals("33", table.get("3"));
        table.put("4", "44");
        table.put("5", "55");
        table.commit();
        assertEquals("44", table.get("4"));
        assertEquals("55", table.get("5"));
        table.rollback();
        assertEquals(null, table.get("4"));
        assertEquals(null, table.get("5"));
        assertEquals("33", table.get("3"));
        assertEquals(3, table.size());

    }

     @Test
    public void testCommitCheckChangesRollbackCheck() throws IllegalArgumentException, KeyNullAndNotFound {
        table.put("1", "11");
        table.put("2", "22");
        table.put("3", "33");
        table.commit();
        assertEquals("11", table.get("1"));
        assertEquals("22", table.get("2"));
        assertEquals("33", table.get("3"));
        table.put("4", "44");
        table.put("5", "55");
        assertEquals("11", table.remove("1"));
        assertEquals("22", table.remove("2"));
        assertEquals(null, table.remove("66"));
        table.commit();
        table.rollback();
        assertEquals("11", table.get("1"));
        assertEquals(3, table.size());

    }

}
