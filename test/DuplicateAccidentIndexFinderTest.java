/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import sddcourseworkalternate.NoDuplicatesInTableException;
import sddcourseworkalternate.DuplicateAccidentIndexFinder;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author haider
 */
public class DuplicateAccidentIndexFinderTest extends junit.framework.TestCase {

    DuplicateAccidentIndexFinder test; //Instance variable of DuplicateAccidentIndexFinder class

    public DuplicateAccidentIndexFinderTest() { //Default Test constructor
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        test = new DuplicateAccidentIndexFinder();
    }

    @After
    public void tearDown() {
    }

    /**
     * Tests the findDuplicateRecords() method. Assures that a sql query can be
     * successfully ran and that the result of this sql query is returned and
     * assigned to the rs attribute of an DuplicateAccidentIndexFinder instance.
     * If the rs attribute of the test instance has a value that is not null,
     * the method queryDatabase() was successful.
     */
    @Test
    public void testFindDuplicateRecords() {
        try {
            test.findDuplicateRecords();
        } catch (NoDuplicatesInTableException ndite) {
            System.out.println(ndite.getMessage());
        }
        assertNotNull(test.getRs());
    }

    /**
     * Tests the exportDuplicateRecords() method. Assures that that the
     * ResultSet attribute rs has been closed after this method has been run.
     * Therefore this method has been run successfully.
     */
    @Test
    public void testExportDuplicateRecords() {
        try {
            test.findDuplicateRecords();
        } catch (NoDuplicatesInTableException ndite) {
            System.out.println(ndite.getMessage());
            ndite.printStackTrace();
        }
        test.exportDuplicateRecords();
        try {
            assertTrue(test.getRs().isClosed());
        } catch (SQLException sql) {
            System.out.println(sql.getMessage());
            sql.printStackTrace();
        }
    }
}
