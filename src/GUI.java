import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class GUI implements ActionListener
{
	
	private static final int I_FIELDSIZE = 10;
	private static final int I_FIELDS_X = 100;
	private static final int I_FIELDS_Y = 50;

	private boolean bRun;
	private int iRuleset;
	private int iSpeed;
	
	private JFrame frame;
	
	private JPanel panelArea;
	public  Area myArea;
	
	private JPanel panelMenu;
	


	
	private JButton buttonRuleset;
	private JLabel labelSpace1;
	
	private JButton buttonRandom;
	private JButton buttonClear;
	private JLabel labelSpace2;
	
	private JButton buttonStart;
	private JLabel labelSpace3;
	
	private JButton buttonSetSpeed;
	
	private JLabel labelRuleset;
	
	private JLabel labelSpeed;
	private JTextField fieldSpeed;

	
	

	
	public GUI()
	{
		bRun = false;
		iRuleset = 0; // Default: torus
		iSpeed = 100; // Default speed
	}
	
	
	public void createFrame()
	{
	// CREATE FRAME
		frame = new JFrame();
		frame.setLayout(new BorderLayout(0,0));
		
		frame.setTitle("Game of Live");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		
	// AREA	
		panelArea = new JPanel();
		myArea = new Area(I_FIELDS_X,I_FIELDS_Y,I_FIELDSIZE);
		
		Dimension dimArea = new Dimension(I_FIELDS_X*I_FIELDSIZE+1,I_FIELDS_Y*I_FIELDSIZE+1);
		myArea.setPreferredSize(dimArea);
		
		panelArea.add(myArea);
		frame.add(panelArea, BorderLayout.CENTER);
		
	// MENU	
		panelMenu = new JPanel();
		
		Dimension dimSpace30 = new Dimension(30,0);
		
		
		// ruleset
		labelRuleset = new JLabel("Regelsatz: Toruswelt");
		panelMenu.add(labelRuleset);
		
		buttonRuleset = new JButton("Fester Rand");
		buttonRuleset.addActionListener(this);
		panelMenu.add(buttonRuleset);
		
		labelSpace1 = new JLabel();
		labelSpace1.setPreferredSize(dimSpace30);
		panelMenu.add(labelSpace1);
		
		// random
		buttonRandom = new JButton("Zufällig");
		buttonRandom.addActionListener(this);
		panelMenu.add(buttonRandom);
		
		// clear
		buttonClear = new JButton("Leeren");
		buttonClear.addActionListener(this);
		panelMenu.add(buttonClear);
		
		labelSpace2 = new JLabel();
		labelSpace2.setPreferredSize(dimSpace30);
		panelMenu.add(labelSpace2);
	
		// start / stop
		buttonStart = new JButton("Start");
		buttonStart.addActionListener(this);
		panelMenu.add(buttonStart);
		
		labelSpace3 = new JLabel();
		labelSpace3.setPreferredSize(dimSpace30);
		panelMenu.add(labelSpace3);
		
		// speed
		labelSpeed = new JLabel("Generationen/Sekunde:");
		panelMenu.add(labelSpeed);
		
		fieldSpeed = new JTextField(4);
		fieldSpeed.addActionListener(this);
		fieldSpeed.setText("10");
		panelMenu.add(fieldSpeed);
		
		buttonSetSpeed = new JButton("Setzen");
		buttonSetSpeed.addActionListener(this);
		panelMenu.add(buttonSetSpeed);
		
		
		frame.add(panelMenu, BorderLayout.PAGE_END);
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
	
	public int speed()
	{
		return iSpeed;
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
			myArea.setRandom(0.4, 0.6, 0.4, 0.6, 0.3);
		}
		else if (event.getActionCommand().equals("Leeren"))
		{
			myArea.clear();
		}
		else if (event.getActionCommand().equals("Start"))
		{
			bRun = true;
			buttonStart.setText("Stop");
			buttonStart.setBackground(Color.green);
		}
		else if (event.getActionCommand().equals("Stop"))
		{
			bRun = false;
			buttonStart.setText("Start");
			buttonStart.setBackground(null);
		}
		else if (event.getActionCommand().equals("Setzen"))
		{
			try
			{
				int speed = (int)(1000/Double.parseDouble(fieldSpeed.getText()));
				if (speed < 1)
				{
					System.err.println("Fehler: Geschwindigkeit zu hoch!");
					fieldSpeed.setText("error");
				}
				else
				{
					iSpeed = speed;
				}
			}
			catch (Exception e)
			{
				System.err.println("Fehler: keine Gültige Geschwindigkeit!");
				fieldSpeed.setText("error");
			}
		}
		
		
	}

}
