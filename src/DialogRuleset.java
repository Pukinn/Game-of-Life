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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class DialogRuleset implements ActionListener,WindowListener
{
	
	
	// GUI
	JFrame frame;
	
	private JLabel labelArea;
		
	private JPanel panelArea;
		private JLabel labelSize;
		private JTextField fieldSize;
		private JLabel labelWidth;
		private JTextField fieldWidth;
		private JLabel labelHeight;
		private JTextField fieldHeight;
		
	private JLabel labelRules;
		
	private JPanel panelRules;
		private JLabel labelRuleset;
		private JButton buttonRuleset;
		private JLabel labelSpeed;
		private JTextField fieldSpeed;
	
	private JPanel panelSet;
		private JButton buttonSet;
		
		
		
		
		
	
	private Ruleset ruleset;
	private GUI gui;
	
	public DialogRuleset(Ruleset _r, GUI _gui){
		ruleset = _r;
		gui = _gui;
	}
	

	public void createDialog(){
		frame = new JFrame();
		frame.setLayout(new GridLayout(0,1));
		
		frame.setTitle("Regeln");
	}
	
	public void addAreaDims(){
		
		// AREA SIZE
		labelArea = new JLabel("Spielfeldgröße:");
		frame.add(labelArea);
	
		
		panelArea = new JPanel(new GridLayout(0,2));
			labelSize = new JLabel("Feldgröße:");
			panelArea.add(labelSize);
			fieldSize = new JTextField((new Integer(ruleset.iFieldsize)).toString());
			panelArea.add(fieldSize);
			labelWidth = new JLabel("Breite:");
			panelArea.add(labelWidth);
			fieldWidth = new JTextField((new Integer(ruleset.iAreaWidth)).toString());
			panelArea.add(fieldWidth);
			labelHeight = new JLabel("Höhe:");
			panelArea.add(labelHeight);
			fieldHeight = new JTextField((new Integer(ruleset.iAreaHeight)).toString());
			panelArea.add(fieldHeight);	
		frame.add(panelArea);
	}
	
	public void addRules(){
		
		labelRules = new JLabel("Spielregeln:");
		frame.add(labelRules);
		
		panelRules = new JPanel(new GridLayout(0,2));
			labelRuleset = new JLabel("Randregel:");
			panelRules.add(labelRuleset);
			buttonRuleset = new JButton(getBorderString());
			buttonRuleset.setBackground(Color.lightGray);
			buttonRuleset.addActionListener(this);
			int height = buttonRuleset.getPreferredSize().height;
			buttonRuleset.setPreferredSize(new Dimension(130,height));
			panelRules.add(buttonRuleset);
			labelSpeed = new JLabel("ms/Generation:");
			panelRules.add(labelSpeed);
			fieldSpeed = new JTextField((new Integer(ruleset.iSpeed)).toString());
			panelRules.add(fieldSpeed);
		frame.add(panelRules);
	}
	
	public void showDialog(){
		
		panelSet = new JPanel();
		buttonSet = new JButton("Regeln Setzen");
		buttonSet.addActionListener(this);
		
		panelSet.add(buttonSet);
		
		frame.add(panelSet);
		
		
		frame.setVisible(true);
		frame.pack();
	}
	
	public String getBorderString(){
		String rule;
		
		if (ruleset.iRule == 0){
			rule = "Toruswelt";
		}
		else if (ruleset.iRule == 1){
			rule = "Fester Rand";
		}
		else {
			System.err.println("Rand-Regelsatz nicht definiert");
			rule = "err";
		}
		
		return rule;
	}
	
	public int getBorderInt(){
		int rule = 0;
		
		if (buttonRuleset.getText().equals("Toruswelt")){
			rule = 0;
		}
		else if (buttonRuleset.getText().equals("Fester Rand")){
			rule = 1;
		}
		else {
			System.err.println("Rand-Regelsatz nich definiert");
		}
		
		return rule;
	}
	
	public void actionPerformed(ActionEvent event) {
	
		if (event.getActionCommand().equals("Toruswelt")){
			buttonRuleset.setText("Fester Rand");
		}
		else if (event.getActionCommand().equals("Fester Rand")){
			buttonRuleset.setText("Toruswelt");
		}
		else if (event.getActionCommand().equals("Regeln Setzen")){
			
			ruleset.iAreaHeight = Integer.parseInt(fieldHeight.getText());
			ruleset.iAreaWidth = Integer.parseInt(fieldWidth.getText());
			ruleset.iFieldsize = Integer.parseInt(fieldSize.getText());
			ruleset.iRule = getBorderInt();
			ruleset.iSpeed = Integer.parseInt(fieldSpeed.getText());

			frame.dispose();
			
			gui.createGUI();
			gui.show();
		}
		
	}


	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}
	public void windowClosing(WindowEvent arg0) {
		System.exit(0);
	}
}
