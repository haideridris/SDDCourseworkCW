/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sddcourseworkalternate;

import java.sql.*;

/**
 * This class handles establishes a connection session with a database. This
 * class makes a connection to the database and assigns the connection session
 * to a Connection object. This connection session is made available to other
 * classes and methods via this classes only method.
 *
 * @author haider
 */
public class DatabaseHandler {

    /**
     * A Connection object reference which can hold the current connection session to a
     * database.
     */
    private Connection con;

    /**
     * Constructs a default DatabaseHandler that is initialised with the default
     * value for the Connect object.
     */
    public DatabaseHandler() {
        con = null;
    }

    /**
     * Method to allow other classes and methods to connect to a database.
     * This method uses sql credentials to make a connection with the mySQL
     * database and assigns the connection session to a Connection object. This
     * connection object is returned to where this method is invoked.
     *
     * @return a Connection object that holds a connection session to a
     * database.
     */
    public Connection handleDbConnection() {
        try {
            String username = "";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://lamp.scim.brad.ac.uk:3306/hraoof", username, password); //Connection for LAMP database 
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hraoof", "", ""); //Connection for my local database
        } catch (ClassNotFoundException cnfe) {
            //ClassNotFoundException thrown trying to load in the Driver class but no definition for the Driver class could be found
            System.out.println(cnfe.getMessage());
            cnfe.printStackTrace();
        } catch (SQLException sql) {
            //SQLException thrown if there an error has occurred when accessing database 
            System.out.println(sql.getMessage());
            sql.printStackTrace();
        } catch (Exception e) {
            //Exception thrown to catch any other Exceptions that may possibly be thrown
            System.out.println(e.getMessage());;
            e.printStackTrace();
        } //Note the Connection object con is not closed here as it prevents the connection session being used by other classes if it was closed here
        return con;
    }
}
