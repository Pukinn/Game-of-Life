import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class GUI implements ActionListener
{
	
// FIELD VALUES
	private static final int I_FIELDSIZE = 10;
	private static final int I_FIELDS_X = 100;
	private static final int I_FIELDS_Y = 50;
	
// CURRENT GAME VALUES
	private boolean bRun;
	private int iRuleset;
	private int iSpeed;

// GUI
	private JFrame frame;
	
	// PAGE_START
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
	
	// PAGE_CENTER
	private JPanel panelArea;
	public  Area myArea;
	
	// PAGE_END
	private JPanel panelDraw;
	
	private JButton buttonBrush;
	private JButton buttonGlider1;
	
	public GUI()
	{
		bRun = false;
		iRuleset = 0; // Default: torus
		iSpeed = 100; // Default speed (in ms/genertion)
	}
	
	
	public void createGUI()
	{
	// FRAME
		frame = new JFrame();
		frame.setLayout(new BorderLayout(0,0));
		
		frame.setTitle("Conways Game of Live");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	// MENU	
		panelMenu = new JPanel();
		
		Dimension dimSpace30 = new Dimension(30,0);
		
		// RULESET
		labelRuleset = new JLabel("Regelsatz: Toruswelt");
		panelMenu.add(labelRuleset);
		
		buttonRuleset = new JButton("Fester Rand");
		buttonRuleset.addActionListener(this);
		panelMenu.add(buttonRuleset);
		
		labelSpace1 = new JLabel();
		labelSpace1.setPreferredSize(dimSpace30);
		panelMenu.add(labelSpace1);
		
		// RANDOM
		buttonRandom = new JButton("Zufällig");
		buttonRandom.addActionListener(this);
		panelMenu.add(buttonRandom);
		
		// CLEAR
		buttonClear = new JButton("Leeren");
		buttonClear.addActionListener(this);
		panelMenu.add(buttonClear);
		
		labelSpace2 = new JLabel();
		labelSpace2.setPreferredSize(dimSpace30);
		panelMenu.add(labelSpace2);
	
		// START/STOP
		buttonStart = new JButton("Start");
		buttonStart.addActionListener(this);
		panelMenu.add(buttonStart);
		
		labelSpace3 = new JLabel();
		labelSpace3.setPreferredSize(dimSpace30);
		panelMenu.add(labelSpace3);
		
		// SPEED
		labelSpeed = new JLabel("Generationen/Sekunde:");
		panelMenu.add(labelSpeed);
		
		fieldSpeed = new JTextField(4);
		fieldSpeed.addActionListener(this);
		fieldSpeed.setText("10");
		panelMenu.add(fieldSpeed);
		
		buttonSetSpeed = new JButton("Setzen");
		buttonSetSpeed.addActionListener(this);
		panelMenu.add(buttonSetSpeed);
		
		frame.add(panelMenu, BorderLayout.PAGE_START);

	// AREA
		panelArea = new JPanel();
		myArea = new Area(I_FIELDS_X,I_FIELDS_Y,I_FIELDSIZE);
		
		Dimension dimArea = new Dimension(I_FIELDS_X*I_FIELDSIZE+1,I_FIELDS_Y*I_FIELDSIZE+1);
		myArea.setPreferredSize(dimArea);
		
		panelArea.add(myArea);
		frame.add(panelArea, BorderLayout.CENTER);
		
	// DRAW
		panelDraw = new JPanel();
		
		buttonBrush = new JButton("Pinsel");
		buttonBrush.addActionListener(this);
		panelDraw.add(buttonBrush);
		
		buttonGlider1 = new JButton("Gleiter klein");
		buttonGlider1.addActionListener(this);
		panelDraw.add(buttonGlider1);
		
		frame.add(panelDraw, BorderLayout.PAGE_END);
			
	}
	
	public void show() {
		frame.pack();
		frame.setVisible(true);
	}


	public boolean run(){
		return bRun;	}
	
	public int ruleset(){
		return iRuleset;}
	
	public int speed(){
		return iSpeed;}
	
	
// BUTTONS
	public void actionPerformed(ActionEvent event)
	{
		// SET RULESET 1
		if (event.getActionCommand().equals("Fester Rand"))
		{
			iRuleset = 1;
			buttonRuleset.setText("Toruswelt");
			labelRuleset.setText("Regelsatz: Fester Rand");
		}
		// SET RULESET 0
		else if (event.getActionCommand().equals("Toruswelt"))
		{
			iRuleset = 0;
			buttonRuleset.setText("Fester Rand");
			labelRuleset.setText("Regelsatz: Toruswelt");
		}
		// SET RANDOM
		else if (event.getActionCommand().equals("Zufällig"))
		{
			myArea.setRandom(0.4, 0.6, 0.4, 0.6, 0.3);
		}
		// CLEAR AREA
		else if (event.getActionCommand().equals("Leeren"))
		{
			myArea.clear();
		}
		// START GAME
		else if (event.getActionCommand().equals("Start"))
		{
			bRun = true;
			myArea.bRun = true;
			buttonStart.setText("Stop");
			buttonStart.setBackground(Color.green);
		}
		// STOP GAME
		else if (event.getActionCommand().equals("Stop"))
		{
			bRun = false;
			myArea.bRun = false;
			buttonStart.setText("Start");
			buttonStart.setBackground(null);
		}
		// SET SPEED
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
				System.err.println("keine Gültige Geschwindigkeit!");
				fieldSpeed.setText("error");
			}
		}
		// SET BRUSH: GLIDER 1
		else if (event.getActionCommand().equals("Gleiter klein"))
		{
			myArea.iBrush = 1;
		}
		// SET BRUSH: BRUSH
		else if (event.getActionCommand().equals("Pinsel"))
		{
			myArea.iBrush = 0;
		}
		
		
	}

}
