import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class GUI implements ActionListener
{
	
	public static final int I_FIELDSIZE = 5;
	public static final int I_BORDER = 15;
	public static final int I_FIELDS_X = 220;
	public static final int I_FIELDS_Y = 100;
	public static final int I_MENU_HEIGHT = 80;

	public JFrame frame;
	
	public Field [][] fieldArea;
	public  Area myArea;
	
	JButton buttonRandom;
	JPanel panel;
	
	public int iFrameWidth;
	public int iFrameHeight;

	
	public GUI()
	{
		iFrameWidth = (I_FIELDS_X*I_FIELDSIZE + I_BORDER*2)+10;
		iFrameHeight = (I_FIELDS_Y*I_FIELDSIZE + I_BORDER*2)+30+I_MENU_HEIGHT;
	}
	
	
	
	public void createFrame()
	{
		frame = new JFrame();
		panel = new JPanel();
		
		
		frame.setTitle("Game of Live");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// center frame
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int iPosXFrame = (screenSize.width - iFrameWidth) / 2;
		int iPosYFrame = (screenSize.height - iFrameHeight) / 2;
	    frame.setSize(iFrameWidth, iFrameHeight);
	    frame.setLocation(iPosXFrame, iPosYFrame);
	    
	    frame.add(panel);
	    System.out.println("width: "+iFrameWidth);
	    System.out.println("frame: "+frame.getWidth());
	}
	
	
	
	public void createGameField()
	{
	
		myArea = new Area(I_BORDER, I_BORDER, I_FIELDS_X,I_FIELDS_Y,I_FIELDSIZE);
		
		frame.add(myArea);
		
		myArea.setBackground(Color.black);
	}
	
	
	
	public void menu()
	{
		buttonRandom = new JButton("Zufällig");

		
		
		buttonRandom.addActionListener(this);

		
		panel.add(buttonRandom);
	}
	
	public void show()
	{
		frame.setVisible(true);
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("button pressed");
		
	}

}
