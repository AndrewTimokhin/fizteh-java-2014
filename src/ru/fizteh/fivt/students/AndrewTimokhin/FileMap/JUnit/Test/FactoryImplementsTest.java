/*
 * Создание тестов для класса FactoryImplements
 * Данные тесты в полном объеме проверяют функциональность
 * соответствующего класса, включая граничные случаи
 */
package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Андрей
 */
public class FactoryImplementsTest {
    private final Map agregaret = new HashMap();

    /**
     * Тест работает на проверку генерации исключения IllegalArgumentException
     * при попытке создать провайдера базы данных с пустой корневой директорией.
     * Также тестируеться создание провайдера базы данных с корректной
     * директорией.
     * 
     * @throws java.io.IOException
     */

    @Test
    public void testCreate() throws IOException {
        TableProviderImplements resultOne = (TableProviderImplements) new FactoryImplements()
                .create("C:\\DataBase");
        assertEquals("C:\\DataBase", resultOne.dir);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateCorrect() {
        String dir = null;
        TableProvider result = new FactoryImplements().create(dir);
    }

}
