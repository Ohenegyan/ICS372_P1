/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 



import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;

public class Controller extends JPanel implements ActionListener {
    static private final String newline = "\n";
    JButton openButton, saveButton, BeginStudyButton, 
    		EndStudyButton, AddReadingButton, ExitButton;
    JTextArea log;
    JFileChooser fc;

	// Commented line refers to functions or classes not yet implemented.
	// static private Trial thisTrial = new Trial();
    
    public Controller() {
        super(new BorderLayout());

        //Create the log first, because the action listeners
        //need to refer to it.
        log = new JTextArea(5,20);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);

        //Create a file chooser
        fc = new JFileChooser();

        openButton = new JButton("Load JSON...");
        openButton.addActionListener(this);

        saveButton = new JButton("Export JSON...");
        saveButton.addActionListener(this);
        
        BeginStudyButton = new JButton("Begin Study...");
        BeginStudyButton.addActionListener(this);
        
        EndStudyButton = new JButton("End Study...");
        EndStudyButton.addActionListener(this);
        
        AddReadingButton = new JButton("Add Reading...");
        AddReadingButton.addActionListener(this);
        
        ExitButton = new JButton("Exit");
        ExitButton.addActionListener(this);
        
        //For layout purposes, put the buttons in a separate panel
        JPanel buttonPanel = new JPanel(); //use FlowLayout
        buttonPanel.add(openButton);
        buttonPanel.add(BeginStudyButton);
        buttonPanel.add(EndStudyButton);
        buttonPanel.add(AddReadingButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(ExitButton);

        //Add the buttons and the log to this panel.
        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {

        //Handle open button action.
        if (e.getSource() == openButton) {
            int returnVal = fc.showOpenDialog(Controller.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                log.append("Opening: " + file.getAbsolutePath() + "." + newline);

                // Commented line refers to functions or classes not yet implemented.
                // log.append(thisTrial.LoadFile(file.getAbsolutePath())+newline);
                
            } else {
                log.append("Open command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());

        //Handle save button action.
        } else if (e.getSource() == saveButton) {
            int returnVal = fc.showSaveDialog(Controller.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                log.append("Saving: " + file.getAbsolutePath() + "." + newline);

                // Commented line refers to functions or classes not yet implemented.
                // log.append(thisTrial.saveFile(file.getAbsolutePath())+newline);
            } else {
                log.append("Save command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());

        // Handle begin study action (set patient to active.)
        } else if (e.getSource() == BeginStudyButton) {
        	String PatientID = JOptionPane.showInputDialog("Please input a Patient ID to begin a study.");
            if ( ( PatientID != null ) && ( PatientID.length() > 0 ) )
            { 
            	log.append("Attempting to set patient ID: " + PatientID + " active for Trial." + newline);
                // Commented line refers to functions or classes not yet implemented.
                // log.append(thisTrial.beginStudy(PatientID) + newline);
           	} else {
           		log.append("User cancelled command." + newline);
           	}
            log.setCaretPosition(log.getDocument().getLength());

        // Handle end study action (set patient to inactive.
        } else if (e.getSource() == EndStudyButton) {
        	String PatientID = JOptionPane.showInputDialog("Please input a Patient ID to end participation.");
            if ( ( PatientID != null ) && ( PatientID.length() > 0 ) )
            { 
            	log.append("Attempting to set patient ID: " + PatientID + " inactive for Trial." + newline);
                // Commented line refers to functions or classes not yet implemented.
                // log.append(thisTrial.endStudy(PatientID) + newline);
           	} else {
           		log.append("User cancelled command." + newline);
           	}
            log.setCaretPosition(log.getDocument().getLength());

        // Handle addReading action
        } else if (e.getSource() == AddReadingButton) {
        	
        	// Prompt user for record data.
        	JTextField patient_id = new JTextField(24);
        	JTextField reading_type = new JTextField(24);
        	JTextField reading_id = new JTextField(24);
        	JTextField reading_value = new JTextField(24);
        	
        	JPanel myPanel = new JPanel();
        	myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
            myPanel.add(new JLabel("Patient ID:"));
            myPanel.add(patient_id);
            myPanel.add(new JLabel("Reading Type: "));
            myPanel.add(reading_type);
            myPanel.add(new JLabel("Reading ID: "));
            myPanel.add(reading_id);
            myPanel.add(new JLabel("Reading Value: "));
            myPanel.add(reading_value);

            int result = JOptionPane.showConfirmDialog(null, myPanel, 
                    "Please Enter Reading Values", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
            	String PatientIDText = patient_id.getText();
            	String ReadingTypeText = reading_type.getText();
            	String ReadingIDText = reading_id.getText();
            	String ReadingValue = reading_value.getText();
            	
            	if ( ( PatientIDText.length() < 1 ) | (PatientIDText == null ) | 
            			( ReadingTypeText.length() < 1 ) | (ReadingTypeText == null ) |
            			( ReadingIDText.length() < 1 ) | (ReadingIDText == null ) |
            			( ReadingValue.length() < 1 ) | (ReadingValue == null ) )
            	{
            		log.append("Could not add Reading - user left required input blank." + newline);
            	} else {
                	log.append("Patient ID   : " + PatientIDText + newline);
                	log.append("Reading Type : " + ReadingTypeText + newline);
                	log.append("Reading ID   : " + ReadingIDText + newline);
                	log.append("Reading Value: " + ReadingValue + newline);
                    // Commented line refers to functions or classes not yet implemented.
                	// log.append(thisTrial.addReading({PatientIDText, ReadingTypeText, ReadingIDText, ReadingValue}) + newline);
            	}
            } else {
            	log.append("User cancelled Add Reading." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
        // Exit program
        } else if (e.getSource() == ExitButton) {
        	String[] options = { "Save first...", "Don't save.", "Cancel." };
        	
        	int choice = JOptionPane.showOptionDialog(null, "Do you want to save before closing?",
                    "Exit Program", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);
        	if ( choice == 0 )
        	{
            	int returnVal = fc.showSaveDialog(Controller.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    log.append("Saving: " + file.getName() + "." + newline);
                    // Commented line refers to functions or classes not yet implemented.
                    // log.append(thisTrial.saveFile(file.getName())+newline);        		
                }
                System.exit(0);
        	}
        	
            if ( choice == 1 )
            {
            	System.exit(0);
            }
            if ( choice == 2 )
            {
                log.append("Exit command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
        }
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Trial Controller");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new Controller());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
    	
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE); 
                createAndShowGUI();
            }
        });
    }
}
