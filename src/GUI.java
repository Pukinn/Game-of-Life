/*
	Game Of Life
    Copyright (C) 2010  Thomas Högner

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
	private JLabel labelSpace4;
	
	private JLabel labelGenerations;
	
	// PAGE_CENTER
	private JPanel panelArea;
	public  Area myArea;
	
	// PAGE_END
	private JPanel panelDraw;
	
	private JButton buttonDrawmode;
	
	private JButton buttonRotate;
	private JLabel labelSpace5;
	
	private JTextField fieldSize;
	
	private ArrayList<JButton> buttonsBrushes;
	
	
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
		int y = (int)buttonStart.getPreferredSize().getHeight();
		buttonStart.setPreferredSize(new Dimension(80,y));
		buttonStart.addActionListener(this);
		panelMenu.add(buttonStart);
		
		labelSpace3 = new JLabel();
		labelSpace3.setPreferredSize(dimSpace30);
		panelMenu.add(labelSpace3);
		
		// SPEED
		labelSpeed = new JLabel("ms/Generation:");
		panelMenu.add(labelSpeed);
		
		fieldSpeed = new JTextField(4);
		fieldSpeed.addActionListener(this);
		fieldSpeed.setText("100");
		panelMenu.add(fieldSpeed);
		
		buttonSetSpeed = new JButton("Setzen");
		buttonSetSpeed.addActionListener(this);
		panelMenu.add(buttonSetSpeed);
		
		labelSpace4 = new JLabel();
		labelSpace4.setPreferredSize(dimSpace30);
		panelMenu.add(labelSpace4);
		
		labelGenerations = new JLabel("Generationen: 0");
		y = (int)labelGenerations.getPreferredSize().getHeight();
		labelGenerations.setPreferredSize(new Dimension(150,y));
		panelMenu.add(labelGenerations);
		
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
		
		labelSpace5 = new JLabel();
		labelSpace5.setPreferredSize(dimSpace30);
		panelDraw.add(labelSpace5);
		
		fieldSize = new JTextField(3);
		fieldSize.addActionListener(this);
		fieldSize.setText("1");
		panelDraw.add(fieldSize);
		
		// BRUSH BUTTONS
		
		buttonsBrushes = new ArrayList<JButton>();

		
		for (Figure curFigure : myArea.myBrushes.brushes){
			buttonsBrushes.add(new JButton(curFigure.name()));;
		}
		
		for (JButton curButton : buttonsBrushes){
			curButton.addActionListener(this);
			panelDraw.add(curButton);
		}	
		
		frame.add(panelDraw, BorderLayout.PAGE_END);
			
	}
	
	public void show() {
		frame.pack();
		frame.setVisible(true);
	}

	public void drawGenerations(int _gen){
		labelGenerations.setText("Generationen: "+_gen);
	}

	
// BUTTONS
	public void actionPerformed(ActionEvent event){
		
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
				int speed = Integer.parseInt(fieldSpeed.getText());
				myArea.setSpeed(speed);
			}
			catch (Exception e)
			{
				System.err.println("keine Gültige Geschwindigkeit!");
				fieldSpeed.setText("error");
			}
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
			myArea.myBrushes.brushes.get(myArea.myBrushes.iSelBrush).rotate();
		}
		// SET BRUSH
		else {
			
			int iSize = (int)(Double.parseDouble(fieldSize.getText()));
			myArea.myBrushes.setBrushSize(iSize,iSize);
			
			int iCount = 0;
			for (Figure curFigure : myArea.myBrushes.brushes){
				if (event.getActionCommand().equals(curFigure.name())){
					myArea.myBrushes.setBrush(iCount);
				}
				iCount++;
			}
		}
		
	}

}
