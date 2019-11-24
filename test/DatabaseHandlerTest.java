/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import sddcourseworkalternate.DatabaseHandler;
import java.sql.*;
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
public class DatabaseHandlerTest extends junit.framework.TestCase {

    DatabaseHandler testConnect; //Instance variable of DatabaseHandler class
    
    public DatabaseHandlerTest() { //default test constructor
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        testConnect = new DatabaseHandler(); //Instantiate an instance of DatabaseHandler class
    }

    @After
    public void tearDown() {
    }

    /**
     * Test the handleDbConnection() of DatabaseHandler class. This test method
     * tests if the the Connection object returned by handleDbConnection() has a
     * connection session with my database called 'hraoof'.
     */
    @Test
    public void testHandleDbConnection() {
        Connection testCon = testConnect.handleDbConnection();
        try {
            String dataBaseName = testCon.getCatalog();
            assertEquals("hraoof", dataBaseName);
        } catch (SQLException sql) {
            System.out.println(sql.getMessage());
            sql.printStackTrace();
        }
    }
}
