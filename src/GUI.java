import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class GUI implements ActionListener
{
	
	private static final int I_FIELDSIZE = 5;
	private static final int I_FIELDS_X = 220;
	private static final int I_FIELDS_Y = 100;

	private boolean bRun;
	private int iRuleset;
	
	private JFrame frame;
	

	public  Area myArea;
	
	private JButton buttonRuleset;
	private JButton buttonRandom;
	private JButton buttonClear;
	private JButton buttonStart;
	
	private JLabel labelRuleset;

	private JPanel panel;
	

	
	public GUI()
	{
		bRun = false;
		iRuleset = 0; // Default: torus
	}
	
	
	public void createFrame()
	{
	// CREATE FRAME
		frame = new JFrame();
		frame.setLayout(new BorderLayout(0,0));
		
		frame.setTitle("Game of Live");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
	// AREA	
		myArea = new Area(I_FIELDS_X,I_FIELDS_Y,I_FIELDSIZE);
		
		Dimension dimArea = new Dimension(I_FIELDS_X*I_FIELDSIZE,I_FIELDS_Y*I_FIELDSIZE);
		myArea.setPreferredSize(dimArea);
		
		frame.add(myArea, BorderLayout.CENTER);
		
		
	// MENU	
		panel = new JPanel();
		
		labelRuleset = new JLabel("Regelsatz: Toruswelt");
		panel.add(labelRuleset);
		
		buttonRuleset = new JButton("Fester Rand");
		buttonRuleset.addActionListener(this);
		panel.add(buttonRuleset);
		
		buttonRandom = new JButton("Zufällig");
		buttonRandom.addActionListener(this);
		panel.add(buttonRandom);
		
		buttonClear = new JButton("Leeren");
		buttonClear.addActionListener(this);
		panel.add(buttonClear);
	
		buttonStart = new JButton("Start");
		buttonStart.addActionListener(this);
		panel.add(buttonStart);
		
		frame.add(panel, BorderLayout.PAGE_END);
	    
		frame.pack();
	}
	
	public void show()
	{
		frame.setVisible(true);
	}


	public boolean run()
	{
		return bRun;
	}
	
	public int ruleset()
	{
		return iRuleset;
	}
	
	

	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals("Fester Rand"))
		{
			iRuleset = 1;
			buttonRuleset.setText("Toruswelt");
			labelRuleset.setText("Regelsatz: Fester Rand");
		}
		else if (event.getActionCommand().equals("Toruswelt"))
		{
			iRuleset = 0;
			buttonRuleset.setText("Fester Rand");
			labelRuleset.setText("Regelsatz: Toruswelt");
		}
		else if (event.getActionCommand().equals("Zufällig"))
		{
			myArea.setRandom(0.45, 0.55, 0.45, 0.55, 0.3);
		}
		else if (event.getActionCommand().equals("Leeren"))
		{
			myArea.clear();
		}
		else if (event.getActionCommand().equals("Start"))
		{
			bRun = true;
			buttonStart.setText("Stop");
		}
		else if (event.getActionCommand().equals("Stop"))
		{
			bRun = false;
			buttonStart.setText("Start");
		}
		
		
	}

}
