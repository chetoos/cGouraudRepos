/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgouraudcolorbuttons;

/**
 *
 * @author cgour
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math;

public class Cgouraudcolorbuttons extends JPanel implements ActionListener{

    /**
     * @param args the command line arguments
     */
     protected JTextField textField;
    protected JTextArea textArea;
    private final static String newline = "\n";
    int colorA=0;
    int colorB=0;
    int colorC=0;
    protected JButton b1, b2, b3;
 

    public Cgouraudcolorbuttons() {
        super(new GridBagLayout());
 
        textField = new JTextField(20);
        textField.addActionListener(this);
        textField.setActionCommand("T");
        b1 = new JButton("Press to change the red");
        b1.setVerticalTextPosition(AbstractButton.CENTER);
        b1.setHorizontalTextPosition(AbstractButton.CENTER);
        b1.setActionCommand("A");
        b2 = new JButton("Press to change the green");
        b2.setVerticalTextPosition(AbstractButton.CENTER);
        b2.setHorizontalTextPosition(AbstractButton.CENTER);
        b2.setActionCommand("B");
        b3 = new JButton("Press to change the blue");
        b3.setVerticalTextPosition(AbstractButton.CENTER);
        b3.setHorizontalTextPosition(AbstractButton.CENTER);
        b3.setActionCommand("C");
        b1.addActionListener(this);
        b3.addActionListener(this);
        b2.addActionListener(this);
        b1.setBackground(Color.red);
        b2.setBackground(Color.green);
        b3.setBackground(Color.blue);
 
        textArea = new JTextArea(5, 20);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
 
        //Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
 
        c.fill = GridBagConstraints.HORIZONTAL;
        add(textField, c);
 
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(scrollPane, c);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(b1, c);
        add(b2, c);
        add(b3, c);
    }
 
    public void actionPerformed(ActionEvent evt) {
        if(evt.getActionCommand().equals("T")){
        String text = textField.getText();
        textArea.append(text + newline);
        
        
        textField.selectAll();
 
        //Make sure the new text is visible, even if there
        //was a selection in the text area.
        textArea.setCaretPosition(textArea.getDocument().getLength());}
        else if(evt.getActionCommand().equals("A")){
            colorA = (int)(Math.random()*255);
        textArea.setForeground(new Color(colorA, colorB, colorC));}
        else if(evt.getActionCommand().equals("B")){
            colorB = (int)(Math.random()*255);
        textArea.setForeground(new Color(colorA, colorB, colorC));}
        else if(evt.getActionCommand().equals("C")){
            colorC = (int)(Math.random()*255);
        textArea.setForeground(new Color(colorA, colorB, colorC));}
    }
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TextDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add contents to the window.
        frame.add(new Cgouraudcolorbuttons());
        
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
}
