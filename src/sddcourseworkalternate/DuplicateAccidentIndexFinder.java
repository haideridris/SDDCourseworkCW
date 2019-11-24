/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sddcourseworkalternate;

import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;
import java.io.*;
import java.sql.*;

/**
 * The DuplicateAccidentIndexFinder class is responsible for finding and
 * exporting records with duplicated Accident Index values in the database. Once
 * records with duplicated accident index values are found they are stored in
 * the class attribute rs.
 *
 * @author haider
 */
public class DuplicateAccidentIndexFinder {

    /**
     * A table of data that contains all the records with duplicated accident
     * index values in the database. This attribute has a protected access
     * modifier to allow other classes to manipulate and handle the rs
     * attribute.
     */
    protected ResultSet rs;

    /**
     * A DatabaseHandler which is used to access the connection session with the
     * database. Once the declaration is initialised, the connect object
     * reference can call a method to establish a connection session with the
     * database.
     */
    private DatabaseHandler connect;

    /**
     * Constructs a default DuplicateAccidentIndexFinder that is initialised
     * with a null value for the ResultSet attribute and sets the
     * DatabaseHandler object reference to null.
     */
    public DuplicateAccidentIndexFinder() { //default constructor for an object of the DuplicateAccidentIndexFinder type
        rs = null;
        connect = null;
    }

    /**
     * This method returns the current value for the ResultSet instance
     * attribute of a DuplicateAccidentIndexFinder.
     *
     * @return a ResultSet object
     */
    public ResultSet getRs() {
        return rs;
    }

    /**
     * This method sets the current value for the ResultSet instance attribute
     * of a DuplicateAccidentIndexFinder.
     *
     * @param inputRs The ResultSet object needed to set the class ResultSet
     * instance attribute.
     */
    public void setRs(ResultSet inputRs) {
        rs = inputRs;
    }

    /**
     * Find records with duplicated Accident Index values. The method connects
     * to the database and runs a sql query to find records with duplicated
     * accident index values. If a valid result set is returned i.e. not zero
     * rows of data, the database result set is assigned to the ResultSet
     * instance attribute of this class as a table of data.
     *
     * @throws NoDuplicatesInTableException
     */
    public void findDuplicateRecords() throws NoDuplicatesInTableException {
        try {
            connect = new DatabaseHandler(); //Instatiate the DatabaseHandler object 
            Connection con = connect.handleDbConnection();
            Statement st = con.createStatement();
            String query = "SELECT a.`Accident_Index`,`Vehicle_Type`,`Vehicle_Manoeuvre`,`Sex_of_Driver`,`Age_Band_of_Driver`,`Engine_Capacity_(CC)`,`Age_of_Vehicle`,`make`,`model` FROM make_model a JOIN(SELECT Accident_Index, COUNT(*) FROM make_model GROUP BY Accident_Index HAVING count(*) > 1) b ON a.Accident_Index = b.Accident_Index ORDER BY a.Accident_Index";
            rs = st.executeQuery(query); //the method Statement.executeQuery() never returns null - so a ResultSet object can have no rows
            //It's only worth handling this ResultSet object if it has rows/records
            if (rs.first()) { // moves the cursor to the first row of this ResultSet object, returns true if there is a row 
                rs.beforeFirst(); //moves the cursor to just before the first row of this ResultSet object
            } else {
                //If there is no first row in this ResultSet object, the ResultSet object has no rows/records
                //So we throw the NoDuplicatesInTableException
                throw new NoDuplicatesInTableException("NoDuplicatesInTableException thrown.");
            }
        } catch (MySQLSyntaxErrorException mysql) {
            //MYSQLSyntaxtErrorException thrown when there is an error in the SQL query's syntax
            System.out.println(mysql.getMessage());
            mysql.printStackTrace();
        } catch (SQLException sql) {
            //SQLException thrown if there an error has occurred when accessing database
            System.out.println(sql.getMessage());
            sql.printStackTrace();
        }
    }

    /**
     * Exports records with duplicated Accident Index values to text file. This
     * method utilises the ResultSet attribute rs of this class and iterates through
     * each row stored in the rs attributes table of data. For each row, it
     * prints the value for each column of a row to a text file named
     * 'duplicateaccident.txt'.
     */
    public void exportDuplicateRecords() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileOutputStream("duplicateaccidentid.txt"));
            int recordCount = 0;
            while (rs.next()) {
                writer.print(rs.getString("Accident_Index"));
                writer.print(" ");
                writer.print(rs.getString("Vehicle_Type"));
                writer.print(" ");
                writer.print(rs.getString("Vehicle_Manoeuvre"));
                writer.print(" ");
                writer.print(rs.getString("Sex_of_Driver"));
                writer.print(" ");
                writer.print(rs.getString("Age_Band_of_Driver"));
                writer.print(" ");
                writer.print(rs.getString("Engine_Capacity_(CC)"));
                writer.print(" ");
                writer.print(rs.getString("Age_of_Vehicle"));
                writer.print(" ");
                writer.print(rs.getString("Make"));
                writer.print(" ");
                writer.print(rs.getString("Model"));
                writer.print(" ");
                writer.println();
                recordCount++;
            }
            writer.print("Number of records " + recordCount); //number of records in the txt file
            writer.close(); //won't through an IOException if closed in a finally block hence I chose to close the writer output stream here
            System.out.println("Successfully printed to text file");
        } catch (SQLException sql) {
            //SQLException thrown if an error has occurred when accessing database
            System.out.println(sql.getMessage());
            sql.printStackTrace();
        } catch (FileNotFoundException fnfe) {
            //FileNotFoundException thrown when an attempt to open the 'duplicateaccidentid.txt' failed if it exists and is a read-only file
            System.out.println(fnfe.getMessage());
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            //IOException thrown if an error occurs whilst utilising the FileOutputStream object
            System.out.println(ioe.getMessage());
            ioe.printStackTrace();
        } catch (Exception e) {
            //Exception thrown to catch any other Exceptions that may possibly be thrown
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if (rs != null) { //check if rs attribute is not null 
                try {
                    rs.close(); //close the rs resource as it does not need to utilised anymore - we have printed it's content to the text file
                } catch (SQLException sql) {
                    //SQLException thrown if there is an error closing the ResultSet object once it's been used
                    System.out.println(sql.getMessage());
                    sql.printStackTrace();
                }
            }

        }
    }
}
