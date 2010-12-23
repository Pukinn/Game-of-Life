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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;


public class DialogRuleset  implements ActionListener
{
	private boolean bVisible;
	
	// GUI
	private JFrame frame;
	
	private JLabel labelArea;
		
	private JPanel panelArea;
		private JLabel labelWidth;
		private JTextField fieldWidth;
		private JLabel labelHeight;
		private JTextField fieldHeight;
		
	private JLabel labelRules;
		
	private JPanel panelRules;
		private JLabel labelRuleset;
		private JButton buttonRuleset;
	
	private JPanel panelSet;
		private JButton buttonSet;
	
	public DialogRuleset(){
		bVisible = true;
	}
	
	public void create(){
		frame = new JFrame();
		frame.setLayout(new GridLayout(0,1));
		
		frame.setTitle("Regeln");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// AREA SIZE
		labelArea = new JLabel("Spielfeldgröße:");
		frame.add(labelArea);
		
		panelArea = new JPanel(new GridLayout(0,2));
			labelWidth = new JLabel("Breite:");
			panelArea.add(labelWidth);
			fieldWidth = new JTextField("100");
			panelArea.add(fieldWidth);
			labelHeight = new JLabel("Höhe:");
			panelArea.add(labelHeight);
			fieldHeight = new JTextField("50");
			panelArea.add(fieldHeight);	
		frame.add(panelArea);
		
		labelRules = new JLabel("Spielregeln:");
		frame.add(labelRules);

	
		panelRules = new JPanel();
			labelRuleset = new JLabel("Randregel:");
			panelRules.add(labelRuleset);
			buttonRuleset = new JButton("Toruswelt");
			buttonRuleset.setBackground(Color.lightGray);
			buttonRuleset.addActionListener(this);
			int height = buttonRuleset.getPreferredSize().height;
			buttonRuleset.setPreferredSize(new Dimension(130,height));
			panelRules.add(buttonRuleset);
		frame.add(panelRules);
		
		
		panelSet = new JPanel();
			buttonSet = new JButton("Regeln Setzen");
			buttonSet.addActionListener(this);
		panelSet.add(buttonSet);
		
		frame.add(panelSet);
		
		
		frame.setVisible(true);
		frame.pack();
	}
	
	public boolean visible(){
		return bVisible;
	}
	
	public void actionPerformed(ActionEvent event) {
	
		if (event.getActionCommand().equals("Toruswelt")){
			buttonRuleset.setText("Fester Rand");
		}
		else if (event.getActionCommand().equals("Fester Rand")){
			buttonRuleset.setText("Toruswelt");
		}
		
	}

}
