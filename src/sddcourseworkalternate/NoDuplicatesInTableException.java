/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sddcourseworkalternate;

/**
 * Signals that the table in the database does not have any records with
 * duplicate accident index values. Once a query has been executed it's result
 * including an empty result set is always assigned to a ResultSet object
 * variable. It is only worth handling this ResultSet object if it has rows of
 * data. If there is no first row in this ResultSet object, the ResultSet object
 * has no rows of data and will cause later errors such as displaying an empty
 * table or exporting to a text file no values.
 *
 * @author haider
 */
public class NoDuplicatesInTableException extends Exception {

    /**
     * Constructs a NoDuplicatesInTableException with a message about the cause
     * of this exception.
     */
    public NoDuplicatesInTableException() {
        System.out.println("The ResultSet object does not hold a table of data.");
    }

    /**
     * Constructs a NoDuplicatesInTableException with a programmer passed string
     * and a message about the cause of this exception.
     *
     * @param passedString A programmer passed string to be displayed along with
     * the message about the cause of this exception
     */
    public NoDuplicatesInTableException(String passedString) {
        System.out.println(passedString + " The ResultSet object does not hold a table of data.");
    }

    /**
     * Returns a message string of what further action should be done.
     *
     * @return A string highlighting what further action the software developer
     * should undergo to fix this error.
     */
    @Override
    public String getMessage() {
        return "Try connecting to a database with a table that has records with duplicated Accident Index values.";
    }

}
