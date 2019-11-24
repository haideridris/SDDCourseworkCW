/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sddcourseworkalternate;

import java.awt.*;
import javax.swing.*;

/**
 * The GUIHandler class is responsible for rendering the GUI Statics for the
 * User Interface to the screen. The User Interface rendered to the screen
 * displays a Car Accident Search Engine menu which displays what
 * functionalities the car accident search engine will perform.
 *
 * @author haider
 */
public class GUIHandler extends JFrame {

    /**
     * Text on screen which states the system can display all records with
     * duplicated Accident Index values.
     */
    JLabel labelDisplayDuplicates;
    /**
     * Text on screen states the system can export all records with duplicated
     * Accident Index values.
     */
    JLabel labelExportDuplicates;
    /**
     * Button on screen which states 'Display Records' for displaying all
     * records with duplicated Accident Index values.
     */
    JButton buttonDisplayDuplicates;
    /**
     * Button on screen which states 'Export Records' for exporting all records
     * with duplicated Accident Index values into a text file called
     * 'duplicateaccidentid.txt'.
     */
    JButton buttonExportDuplicates;

    /**
     * Default GUIHandler constructor that once initialised renders the Graphical User Interface on screen.
     */
    public GUIHandler() {

        setTitle("Car Accident Search Engine Menu"); //Set title of JFrame

        //A label for displaying records
        labelDisplayDuplicates = new JLabel(); //Instantiate a JLabel component
        labelDisplayDuplicates.setText("Display all records with duplicated Accident Index values on screen"); //Set the text of this JLabel
        labelDisplayDuplicates.setBounds(10, 10, 700, 30); //positions component using x, y co-ordinates on JFrame then sets the width and height of JFrame that component occupies
        getContentPane().add(labelDisplayDuplicates); //Add this label component to the content pane

        //A button for displaying records
        buttonDisplayDuplicates = new JButton(); //Instantiate a JButton component
        buttonDisplayDuplicates.setText("Display Records"); //Set the text of this JButton
        buttonDisplayDuplicates.setBounds(600, 10, 150, 30); //positions component using x, y co-ordinates on JFrame then sets the width and height of JFrame that component occupies
        getContentPane().add(buttonDisplayDuplicates); //Add this button component to the content pane

        //A label for exporting records to text file
        labelExportDuplicates = new JLabel();
        labelExportDuplicates.setText("Export all records with duplicated Accident Index values into a text file named 'duplicateaccidentid.txt'");//Set the text of this JLabel
        labelExportDuplicates.setBounds(10, 60, 700, 30);
        getContentPane().add(labelExportDuplicates);

        //A button for exporting records to text file
        buttonExportDuplicates = new JButton();
        buttonExportDuplicates.setText("Export Records");
        buttonExportDuplicates.setBounds(600, 60, 150, 30);
        getContentPane().add(buttonExportDuplicates);

        setSize(775, 130); //Sets the size of the JFrame, width by height in pixels
        getContentPane().setBackground(Color.WHITE); //Set content pane of JFrame to colour white
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Ends the program once the JFrame is closed
        setLocationRelativeTo(null); //sets JFrame in the middle of the screen on start up
        setResizable(false); //Disables resizing of JFrame 
        setLayout(null); //Allows me to place components on screen through the use of setBounds
        setVisible(true); //Allows the JFrame to be visible     
    }
}
