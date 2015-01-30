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
    public TableProvider tp;
    public String path;
    public Table tb;

    @Before
    public void setUp() {
        TableProviderFactory tpv = new FactoryImplements();
        path = tmp.newFolder("time").getAbsolutePath();
        tp = tpv.create(path);
        tb = tp.createTable("testing");

        FactoryImplements tb = new FactoryImplements();
        TableProviderImplements tp = (TableProviderImplements) tb
                .create("test");
        Table time = tp.createTable("new");
        time = tp.getTable("new");

    }

    /**
     * Тестирование getName (большо одного нет смысла)
     */
    @Test
    public void testGetName() {
        String result = tb.getName();
        assertTrue("testing".equals(result));
    }

    /**
     * Тесты на size method
     */
    @Test
    public void testSizeByDefault() {
        int result = tb.size();
        assertEquals(0, result);
    }

    @Test
    public void testSizeAfterAddedAndDel() {
        tb.put("1", "11");
        tb.put("2", "22");
        int result = tb.size();
        assertEquals(2, result);
        tb.remove("2");
        result = tb.size();
        assertEquals(1, result);
        tb.remove("1");
        result = tb.size();
        assertEquals(0, result);
    }

    /**
     * Тестирование put метода
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPutIfKeyIsNull() {
        String value = "test_value";
        tb.put(null, value);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPutIfValueIsNull() {
        String key = "test_key";
        tb.put(key, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPutIfValueAndKeyIsNull() {
        tb.put(null, null);
    }

    @Test
    public void testPutIfKeyDnHaveInDataBase() {
        String key = "test_key";
        String value = "test_value";
        assertNull(tb.put(key, value));
    }

    @Test
    public void testPutIfKeyHaveInDataBase() {
        String key = "test_key";
        String valueOld = "test_value_old";
        String valueNew = "test_value_new";

        assertNull(tb.put(key, valueOld));
        assertEquals(valueOld, tb.put(key, valueNew));
    }

    /**
     * Тестирование remove метода
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveIfKeyNull() {
        tb.remove(null);
    }

    @Test
    public void testRemoveIfKeyExist() {
        String key = "key_test";
        String value = "value_test";
        tb.put(key, value);

        assertEquals(value, tb.remove(key));
        try {
            assertNull(tb.get(key));
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TableImplementTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyNullAndNotFound ex) {
            Logger.getLogger(TableImplementTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testRemoveIfKeyDnExist() {
        String key = "key_test";

        assertNull(tb.remove(key));
    }

    /**
     * Тестирование метода commit
     */
    @Test
    public void testCommitIfEmpty() {
        assertEquals(0, tb.commit());
    }

    @Test
    public void testCommitIfFullAnything() {
        tb.put("id564", "989");
        tb.put("id988", "123");
        tb.put("id123", "766");
        assertEquals(3, tb.commit());
    }

    public void testCommitIfWasRollback() {
        tb.put("id564", "989");
        tb.put("id988", "123");
        tb.put("id123", "766");
        tb.rollback();

        assertEquals(0, tb.commit());
    }

    /**
     * Тестирование rollback метода
     */
    @Test
    public void testRollbackIfStateEmpty() {
        assertEquals(0, tb.rollback());
    }

    @Test(expected = NullPointerException.class)
    public void testRollbackNullPointer() {
        tb.put("id564", "989");
        tb.put("id988", "123");
        tb.put("id123", "766");
        tb.commit();
        tb.rollback();
    }

    @Test
    public void testRollbackSomeChainChanges() {
        tb.put("1", "1");
        tb.put("2", "2");
        tb.put("3", "3");
        tb.commit();
        tb.put("4", "4");
        tb.put("54", "54");
        tb.commit();
        assertEquals(2, tb.rollback());
    }
    
     @Test
    public void testCommitCheckRollbackCheck() throws IllegalArgumentException, KeyNullAndNotFound {
        tb.put("1", "11");
        tb.put("2", "22");
        tb.put("3", "33");
        tb.commit();
        assertEquals("11", tb.get("1"));
        assertEquals("22", tb.get("2"));
        assertEquals("33", tb.get("3"));
        tb.put("4", "44");
        tb.put("5", "55");
        tb.commit();
        assertEquals("44", tb.get("4"));
        assertEquals("55", tb.get("5"));
        tb.rollback();
        assertEquals(null, tb.get("4"));
        assertEquals(null, tb.get("5"));
        assertEquals("33", tb.get("3"));
        assertEquals(3, tb.size());

    }

     @Test
    public void testCommitCheckChangesRollbackCheck() throws IllegalArgumentException, KeyNullAndNotFound {
        tb.put("1", "11");
        tb.put("2", "22");
        tb.put("3", "33");
        tb.commit();
        assertEquals("11", tb.get("1"));
        assertEquals("22", tb.get("2"));
        assertEquals("33", tb.get("3"));
        tb.put("4", "44");
        tb.put("5", "55");
        assertEquals("11", tb.remove("1"));
        assertEquals("22", tb.remove("2"));
        assertEquals(null, tb.remove("66"));
        tb.commit();
        tb.rollback();
        assertEquals("11", tb.get("1"));
        assertEquals(3, tb.size());

    }

}
