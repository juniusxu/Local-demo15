package computer;

import java.awt.*;
import java.awt.event.*;

public class CalculatorFrame extends Frame {
    
	private static final long serialVersionUID = 1L;

     CalcPanel panel;
     public CalculatorFrame() {
                
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu();
        MenuItem menuFileExit = new MenuItem();
        
        menuFile.setLabel("File");
        menuFileExit.setLabel("Exit");
        
        
        // Add action listener.for the menu button
        menuFileExit.addActionListener
        (
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    CalculatorFrame.this.windowClosed();
                }
            }
        ); 
        menuFile.add(menuFileExit);
        menuBar.add(menuFile);
        
        setTitle("Calculator");
        setMenuBar(menuBar);
        setSize(new Dimension(400, 400));
        
        // Add window listener.
        this.addWindowListener
        (
            new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    CalculatorFrame.this.windowClosed();
                }
            }
        );
      panel=new CalcPanel();
      add(panel,BorderLayout.CENTER);
    }
    
    
    /**
     * Shutdown procedure when run as an application.
     */
    protected void windowClosed() 
    {
    	// Exit application.
        System.exit(0);
    }
}

