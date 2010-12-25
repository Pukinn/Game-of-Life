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
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;


public class Area extends JPanel implements MouseListener, MouseMotionListener


{
	private static final long serialVersionUID = 646822554708769792L;

// GAME VALUES
	private Ruleset rule;
	
// PREVIOUS MOUSE POITION	
	private int iMouseX;
	private int iMouseY;


// AREA VALUES
	private Field[][] fieldArea;

	public Brushes myBrushes;
	
	public Area(Ruleset _rule)
	{	
		rule = _rule;
		
		myBrushes = new Brushes();

		
		fieldArea = new Field[rule.iAreaWidth][rule.iAreaHeight];
		for (int iY=0; iY < rule.iAreaHeight; iY++)
		{
			for (int iX=0; iX < rule.iAreaWidth; iX++)
			{
				fieldArea[iX][iY] = new Field(0);
			}
		}
		

		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	

	public void paint(Graphics g)
	{	
		
		for (int iY = 0; iY < rule.iAreaHeight; iY++){
			for (int iX = 0; iX < rule.iAreaWidth; iX++){
				
				int iLifestate = fieldArea[iX][iY].lifestate();
				int iPopulation = fieldArea[iX][iY].population();
				boolean bHighlight = fieldArea[iX][iY].highlighted() && !rule.bRun;
				
				
				if (iLifestate == 0){
					if (!bHighlight){
						g.setColor(Color.lightGray);
					}
					else {
						g.setColor(new Color(240,240,240));
					}
				}
				else if (iLifestate > 0 && iPopulation == 0) {
					
					if (!bHighlight) {
					g.setColor(Color.blue);
					}
					else {
						g.setColor(new Color(120,120,255));
					}
				}
				else if (iLifestate > 0 && iPopulation == 1) {
					
					if (!bHighlight) {
					g.setColor(new Color(255,162,0));
					}
					else {
						g.setColor(new Color(255,219,155));
					}
				}
				else {
					System.err.println("Feld "+iX+"/"+iY+" hat keinen definierten Wert!");
				}
				
				
				int iPosX = iX*rule.iFieldsize;
				int iPosY = iY*rule.iFieldsize;
				
				// DRAW FIELD
				g.fillRect(iPosX, iPosY, rule.iFieldsize, rule.iFieldsize);
				
				// DRAW BORDER
				g.setColor(Color.black);
				g.drawRect(iPosX, iPosY, rule.iFieldsize, rule.iFieldsize);
				
			}
		}
		

	}
	
	public void clear()
	{
		for (int iY=0; iY < rule.iAreaHeight; iY++)
		{
			for (int iX=0; iX < rule.iAreaWidth; iX++)
			{
				fieldArea[iX][iY].kill();
			}
		}
		
		this.repaint();
	}
	
	public void setRandom(double _xMin, double _xMax, double _yMin, double _yMax, double _lifeValue)
	{
		
		for (int iX = (int)(rule.iAreaWidth*_xMin); iX < (int)(rule.iAreaWidth*_xMax); iX++){
			for (int iY = (int)(rule.iAreaHeight*_yMin); iY < (int)(rule.iAreaHeight*_yMax); iY++){
				fieldArea[iX][iY].setRandom(_lifeValue);
			}	
		}
		this.repaint();
	}
	

	
	public void nextGeneration()
	{
		
	// CLEAR NEIGHBORS
		for (int iY=0; iY < rule.iAreaHeight; iY++)
		{
			for (int iX=0; iX < rule.iAreaWidth; iX++)
			{
				fieldArea[iX][iY].setNeighbors(0);
			}
		}

	// SET NEIGHBORS
		for (int iY=0; iY < rule.iAreaHeight; iY++)
		{	
			for (int iX=0; iX < rule.iAreaWidth; iX++)
			{
	
				int lifestate = fieldArea[iX][iY].lifestate();
				
				addNeighbors(iX-1, iY-1, lifestate);	// left-top
				addNeighbors(iX, iY-1, lifestate);		// top
				addNeighbors(iX+1, iY-1, lifestate);	// right-top
				addNeighbors(iX-1, iY, lifestate);		// left
				addNeighbors(iX+1, iY, lifestate);		// right
				addNeighbors(iX-1, iY+1, lifestate);	// left-bottom
				addNeighbors(iX, iY+1, lifestate);		// bottom
				addNeighbors(iX+1, iY+1, lifestate);	// right-bottom
					
			}
		}
			
	// KILL/BIRTH
		for (int iY=0; iY < rule.iAreaHeight; iY++)
		{
			for (int iX=0; iX < rule.iAreaWidth; iX++)
			{
				fieldArea[iX][iY].killbirth(2, 3, 3, 3);	// classical 23/3 rule
			}
		}
	   
		
		this.repaint();
	}
	
	
	public void addNeighbors(int _x, int _y, int _add)
	{
		if (rule.iRule == 0) // TORUS
		{
			int iX = (_x+rule.iAreaWidth)%rule.iAreaWidth;
			int iY = (_y+rule.iAreaHeight)%rule.iAreaHeight;
			
			fieldArea[iX][iY].addNeighbor(_add);
		}
		else if (rule.iRule == 1) // SOLID
		{
			if ((0 <= _x && _x < rule.iAreaWidth) && (0 <= _y && _y < rule.iAreaHeight)){
				fieldArea[_x][_y].addNeighbor(_add);
			}
		}
		else
		{
			System.err.println("Falscher Regelsatz!");
		}
	}
	
	public void clearHighlights()
	{
		for (int iY=0; iY < rule.iAreaHeight; iY++)
		{
			for (int iX=0; iX < rule.iAreaWidth; iX++)
			{
				fieldArea[iX][iY].setHighlight(false);
			}
		}
	}
	
	public void mouseClicked(MouseEvent event)
	{
		if (!rule.bRun)
		{
			int fieldX = (event.getX()-1)/rule.iFieldsize;
			int fieldY = (event.getY()-1)/rule.iFieldsize;
			
			ArrayList<Point> brush = myBrushes.currentBrush();
			
			for (Point curPoint : brush)
			{
				int iX = fieldX+curPoint.x;
				int iY = fieldY+curPoint.y;
				
				if (rule.iRule == 0){ // TORUS
				
					iX = (iX+rule.iAreaWidth)%rule.iAreaWidth;
					iY = (iY+rule.iAreaHeight)%rule.iAreaHeight;
					
					if (myBrushes.drawmode()) {
						fieldArea[iX][iY].grow();
					}
					else {
						fieldArea[iX][iY].kill();
					}
					
				}
				else if (rule.iRule == 1){ // SOLID
					
					if ((0 <= iX && iX < rule.iAreaWidth) && (0 <= iY && iY < rule.iAreaHeight)){
						if (myBrushes.drawmode()) {
							fieldArea[iX][iY].grow();
						}
						else {
							fieldArea[iX][iY].kill();
						}
					}
				}

			}
			
			this.repaint();
		}
	}




	public void mouseMoved(MouseEvent event)
	{
		if (!rule.bRun)
		{
			int fieldX = (event.getX()-1)/rule.iFieldsize;
			int fieldY = (event.getY()-1)/rule.iFieldsize;

			
			
			if (fieldX != iMouseX || fieldY != iMouseY)
			{
				clearHighlights();
				
				ArrayList<Point> brush = myBrushes.currentBrush();
				for (Point curPoint : brush)
				{					
					int iX = fieldX+curPoint.x;
					int iY = fieldY+curPoint.y;
					
					if (rule.iRule == 0){ // TORUS
						iX = (iX+rule.iAreaWidth)%rule.iAreaWidth;
						iY = (iY+rule.iAreaHeight)%rule.iAreaHeight;
						
						fieldArea[iX][iY].setHighlight(true);
					}
					else if (rule.iRule == 1){ // SOLID
						if ((0 <= iX && iX < rule.iAreaWidth) && (0 <= iY && iY < rule.iAreaHeight))
						{
							fieldArea[iX][iY].setHighlight(true);
						}
					}

				}
				iMouseX = fieldX;
				iMouseY = fieldY;
				
				this.repaint();	
			}
			
		}
	}

	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	public void mouseDragged(MouseEvent arg0) {}
	
}
