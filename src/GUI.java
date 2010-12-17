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
	
	private JButton buttonDrawmode;
	
	private JButton buttonRotate;
	private JLabel labelSpace4;
	
	private JTextField fieldSize;
	private JButton buttonBrush;
	private JButton buttonGlider;
	private JButton buttonLWSS;
	private JButton buttonMWSS;
	private JButton buttonHWSS;
	
	public GUI()
	{
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
		
		buttonDrawmode = new JButton("Löschen");
		buttonDrawmode.addActionListener(this);
		panelDraw.add(buttonDrawmode);
		
		buttonRotate = new JButton("Rotieren 90°");
		buttonRotate.addActionListener(this);
		panelDraw.add(buttonRotate);
		
		labelSpace4 = new JLabel();
		labelSpace4.setPreferredSize(dimSpace30);
		panelDraw.add(labelSpace4);
		
		fieldSize = new JTextField(3);
		fieldSize.addActionListener(this);
		fieldSize.setText("1");
		panelDraw.add(fieldSize);
		
		buttonBrush = new JButton("Pinsel");
		buttonBrush.addActionListener(this);
		panelDraw.add(buttonBrush);
		
		buttonGlider = new JButton("Gleiter");
		buttonGlider.addActionListener(this);
		panelDraw.add(buttonGlider);
		
		buttonLWSS = new JButton("LWSS");
		buttonLWSS.addActionListener(this);
		panelDraw.add(buttonLWSS);
		
		buttonMWSS = new JButton("MWSS");
		buttonMWSS.addActionListener(this);
		panelDraw.add(buttonMWSS);
		
		buttonHWSS = new JButton("HWSS");
		buttonHWSS.addActionListener(this);
		panelDraw.add(buttonHWSS);
		
		
		frame.add(panelDraw, BorderLayout.PAGE_END);
			
	}
	
	public void show() {
		frame.pack();
		frame.setVisible(true);
	}


	
// BUTTONS
	public void actionPerformed(ActionEvent event)
	{
		// SET RULESET 1
		if (event.getActionCommand().equals("Fester Rand"))
		{
			myArea.setRuleset(1);
			buttonRuleset.setText("Toruswelt");
			labelRuleset.setText("Regelsatz: Fester Rand");
		}
		// SET RULESET 0
		else if (event.getActionCommand().equals("Toruswelt"))
		{
			myArea.setRuleset(0);
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
			myArea.bRun = true;
			buttonStart.setText("Stop");
			buttonStart.setBackground(Color.green);
		}
		// STOP GAME
		else if (event.getActionCommand().equals("Stop"))
		{
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
					myArea.setSpeed(speed);
				}
			}
			catch (Exception e)
			{
				System.err.println("keine Gültige Geschwindigkeit!");
				fieldSpeed.setText("error");
			}
		}
		// SET BRUSH
		else if (event.getActionCommand().equals("Pinsel"))
		{
			int iSize = (int)(Double.parseDouble(fieldSize.getText()));
			myArea.myBrushes.setBrush(0);
			myArea.myBrushes.setBrushSize(iSize);
		}
		else if (event.getActionCommand().equals("Gleiter"))
		{
			myArea.myBrushes.setBrush(1);
		}
		else if (event.getActionCommand().equals("LWSS"))
		{
			myArea.myBrushes.setBrush(2);
		}
		else if (event.getActionCommand().equals("MWSS"))
		{
			myArea.myBrushes.setBrush(3);
		}
		else if (event.getActionCommand().equals("HWSS"))
		{
			myArea.myBrushes.setBrush(4);
		}
		// DRAWMODE
		else if (event.getActionCommand().equals("Löschen")) {
			myArea.myBrushes.setDrawmode(false);
			buttonDrawmode.setText("Zeichnen");
		}
		else if (event.getActionCommand().equals("Zeichnen")) {
			myArea.myBrushes.setDrawmode(true);
			buttonDrawmode.setText("Löschen");
		}
		// ROTATE
		else if (event.getActionCommand().equals("Rotieren 90°")) {
			myArea.myBrushes.rotate();
		}
		
	}

}
