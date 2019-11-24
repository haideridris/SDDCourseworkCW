/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sddcourseworkalternate;

import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * This class is responsible for making the GUI functional and responsive to user interaction
 * through on screen events (button presses on the GUI). The class is
 * responsible for calling methods in the DuplicateAccidentIndexFinder class
 * that will find and export records with duplicate accident index values. This
 * class is also responsible for displaying on screen to the user all records
 * with duplicate accident index values by calling it's internal methods.
 *
 * @author hraoof
 */
public class GUIImplementation extends GUIHandler implements ActionListener {

    /**
     * Default GUIImplementation constructor. Once a GUIImplementation is
     * initialised it renders the GUI on screen via a super call to the
     * GUIHandler class and also adds ActionListener methods to the two JButton
     * class attributes in the GUIHandler class.
     */
    public GUIImplementation() {
        super();
        buttonDisplayDuplicates.addActionListener(this);
        buttonExportDuplicates.addActionListener(this);
    }

    /**
     * Code to handle events. This method handles events such as button press to
     * provide the user a response. In response to pressing the 'Display
     * Records', the user will be displayed all records with duplicate accident
     * index values on screen. In response to pressing the 'Export Records'
     * button, all records with duplicate accident index values will be exported
     * into a text file named 'duplicateaccidentid.txt' and the user will be
     * notified this has occurred successfully on screen.
     *
     * @param e A ActionEvent object generated when a on screen button component
     * is pressed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        DuplicateAccidentIndexFinder find = new DuplicateAccidentIndexFinder();
        if (e.getSource() == buttonExportDuplicates) {
            try {
                find.findDuplicateRecords(); //If findDuplicateRecords() throws the NoDuplicatesInTableException the statements below in this try block will not run
                find.exportDuplicateRecords(); //export the records to the text file
                JOptionPane.showMessageDialog(null, "Records with duplicated accident index values have been successfully exported to the text file 'duplicateaccidentid.txt'.");
            } catch (NoDuplicatesInTableException ndite) {
                System.out.println(ndite.getMessage());
                JOptionPane.showMessageDialog(null, "No records with duplicated accident index values to export within the database");
            }
        } else if (e.getSource() == buttonDisplayDuplicates) {
            try {
                find.findDuplicateRecords(); //If queryDatabase() throws the NoDuplicatesInTableException the statements below in this try block will not run
                populateTableWithRecords(find.rs); //displays records with duplicate accident values in pop up JFrame
            } catch (NoDuplicatesInTableException ndite) {
                System.out.println(ndite.getMessage());
                JOptionPane.showMessageDialog(null, "There is nothing to display as there are no records with duplicated accident index values within the database");
            }
        }
    }

    /**
     * Populates a table with records in a new pop up JFrame on screen.
     *
     * @param inputRs A ResultSet object that has a valid table of data
     * detailing records with duplicate accident index values.
     * @return A boolean to check if the method has been successfully ran
     * without throwing an exception
     */
    public boolean populateTableWithRecords(ResultSet inputRs) {
        try {
            //Recieve ResultSet object passed from the site of this methods invocation and assign it to rs variable
            ResultSet rs = inputRs;
            // It creates and displays the table
            JTable table = new JTable(buildTableModel(rs)); //creates a table and populates it by using the result set acquired with queryDatabase() method           
            //Create a JFrame to place the populated table into
            JFrame jf = new JFrame();
            jf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); //allows the program to keep running when the JFrame displaying all the records is closed
            jf.setTitle("Records with duplicate Accident Index values");
            jf.setSize(900, 600);
            jf.setLocationRelativeTo(null); //Sets this JFrame in the middle of the screen
            jf.setResizable(false); //disable resizing
            JScrollPane jps = new JScrollPane(table); //Scrollpane so all the rows can be seen in the table
            jf.getContentPane().add(jps); //adds scrollpane holding the table onto the JPanel
            jf.setVisible(true);
        } catch (SQLException sql) {
            //Catches SQlException buildTable() may throw as it uses the ResultSet object to populate Vectors
            System.out.println(sql.getMessage());
            sql.printStackTrace();
        }
        return true;
    }

    /**
     * This method returns a data model used to construct a JTable.
     *
     * @param passedRs A valid ResultSet object containing a table of data
     * detailing records with duplicate accident index values
     * @return A DefaultTabelModel instance used to construct a JTable
     * @throws SQLException
     */
    public DefaultTableModel buildTableModel(ResultSet passedRs)
            throws SQLException {
        //rs.getMetaData() retrieves the number, types and properties of this ResultSet object's columns
        ResultSetMetaData metaData = passedRs.getMetaData();
        // columnName is the names of columns - retrieved from the database - stored in 1D vector
        Vector<String> columnNames = new Vector<String>(); //Instantiates a Vector called columnName which holds a dynamic array of String objects
        int columnCount = metaData.getColumnCount(); //Assigns the value for the number colomns the rs object holds to an int variable called columnCount
        //Using the int variable columnCount, it iterates through the column names stored in the ResultSetMetaData object metaData and adds String objects with each column name found in metaData to the Vector columnNames
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }
        // data of the table - stored in a 2D Vector 
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        //Using a while loop, iterate through each row in the rs object passed to this method
        while (passedRs.next()) {
            Vector<Object> vector = new Vector<Object>();
            //For each row add Objects to the Vector called vector, each object contains a value 
            //Using the int variable columnIndex, it iterates through all the columns of a row
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                //The rs.getObject() gets the value of the column as selected by columnIndex in the current row of this ResultSet object as an Object 
                //This value is added to the Vector 'vector' as an object of the type Object
                vector.add(passedRs.getObject(columnIndex));
            }
            //The one dimensional Vector called vector containing in our case 9 Objects for each column of a single row is added to the 2D Vector called data
            //This will be repeatedly done for all rows of the rs attribute
            data.add(vector);
        }
        //Returns a new instance of the class DefaultTableModel
        //data - the data of the table, a Vector of Vectors of Object values
        //columnNames - vector containing the names of the each column
        return new DefaultTableModel(data, columnNames); //returns an object of the type DefaultTableModel that is used to populate the table
    }

    public static void main(String[] args) {
        GUIImplementation programEntry = new GUIImplementation();
    }
}
