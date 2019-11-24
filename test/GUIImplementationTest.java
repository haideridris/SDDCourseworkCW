/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import sddcourseworkalternate.NoDuplicatesInTableException;
import sddcourseworkalternate.GUIImplementation;
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
public class GUIImplementationTest extends junit.framework.TestCase {

    DuplicateAccidentIndexFinder test; //Instance variable of DuplicateAccidentIndexFinder class
    GUIImplementation tester; //Instance of GUIImplemtation class

    public GUIImplementationTest() { //Default test constructor
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
        try {
            test.findDuplicateRecords();//Assure that the instance 'test' has a rs attribute that holds a table of data
        } catch (NoDuplicatesInTableException ndite) {
            System.out.println(ndite.getMessage());
            ndite.printStackTrace();
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Tests the populateTableModel() method. Assures that a table can be
     * populated using a rs attribute containing a table of data and
     * subsequently displayed to the screen in a popup JFrame. The method
     * populateTableModel() returns true if the code for this method runs
     * without throwing an SQLException.
     */
    @Test
    public void testPopulateTableWithRecords() {
        tester = new GUIImplementation();
        assertTrue(tester.populateTableWithRecords(test.getRs()));
    }

    /**
     * Tests the buildTableModel() method. Assures that the new instance of
     * DefaultTableModel returned by populateTableWithRecords() is not null.
     * Therefore we can use this instance to populate a JTable for display on
     * screen.
     */
    @Test
    public void testBuildTableModel() {
        try {
            tester = new GUIImplementation();
            assertNotNull(tester.buildTableModel(test.getRs()));
        } catch (SQLException sql) {
            System.out.println(sql.getMessage());
            sql.printStackTrace();
        }
    }
}
