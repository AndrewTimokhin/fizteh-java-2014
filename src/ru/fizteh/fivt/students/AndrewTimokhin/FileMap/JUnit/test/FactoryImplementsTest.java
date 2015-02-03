package ru.fizteh.fivt.students.AndrewTimokhin.FileMap.JUnit;

import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.DataBase.FactoryImplements;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.DataBase.TableProvider;
import ru.fizteh.fivt.students.AndrewTimokhin.FileMap.DataBase.TableProviderImplements;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Timokhin Andrew
 */
public class FactoryImplementsTest {

    @Test
    public void testCreate() throws IOException {
        TableProviderImplements resultOne = (TableProviderImplements) new FactoryImplements()
                .create("test");
        assertEquals("test", resultOne.getDir());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateIncorrect() {
        String dir = null;
        TableProvider result = new FactoryImplements().create(dir);
    }

}
