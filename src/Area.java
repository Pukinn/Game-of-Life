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

// AREA DIMENSION
	private int iFieldsX;
	private int iFieldsY;
	private int iFieldsize;
	
// PREVIOUS MOUSE POITION	
	private int iMouseX;
	private int iMouseY;
	
// CURRENT GAME VALUES
	public boolean bRun;
	public int iRuleset;
	public int iSpeed;

// AREA VALUES
	private Field[][] fieldArea;

	public Brushes myBrushes;
	
	public Area(int _fieldsx, int _fieldsy, int _fieldsize)
	{	
		iFieldsX = _fieldsx;
		iFieldsY = _fieldsy;
		iFieldsize = _fieldsize;
		
		myBrushes = new Brushes();
		
	// SET DEFAULTS
		bRun = false;
		iRuleset = 0; // Default: torus
		iSpeed = 100; // Default speed (in ms/generation)

		
		fieldArea = new Field[iFieldsX][iFieldsY];
		for (int iY=0; iY < iFieldsY; iY++)
		{
			for (int iX=0; iX < iFieldsX; iX++)
			{
				fieldArea[iX][iY] = new Field(0);
			}
		}
		

		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	

	public void paint(Graphics g)
	{	
		
		for (int iY = 0; iY < iFieldsY; iY++)
		{
			for (int iX = 0; iX < iFieldsX; iX++)
			{
				int iLifestate = fieldArea[iX][iY].lifestate();
				boolean bHighlight = fieldArea[iX][iY].highlighted() && !bRun;
				
				
				if (iLifestate == 0)
				{
					if (!bHighlight)
					{
						g.setColor(Color.lightGray);
					}
					else
					{
						g.setColor(new Color(240,240,240));
					}
				}
				else if (iLifestate == 1)
				{
					if (!bHighlight)
					{
					g.setColor(Color.red);
					}
					else
					{
						g.setColor(Color.pink);
					}
				}
				else
				{
					System.err.println("Feld "+iX+"/"+iY+" hat keinen definierten Wert!");
				}
				
				
				int iPosX = iX*iFieldsize;
				int iPosY = iY*iFieldsize;
				
				// DRAW FIELD
				g.fillRect(iPosX, iPosY, iFieldsize, iFieldsize);
				
				// DRAW BORDER
				g.setColor(Color.black);
				g.drawRect(iPosX, iPosY, iFieldsize, iFieldsize);
				
			}
		}
		

	}
	
	public void clear()
	{
		for (int iY=0; iY < iFieldsY; iY++)
		{
			for (int iX=0; iX < iFieldsX; iX++)
			{
				fieldArea[iX][iY].kill();
			}
		}
		
		this.repaint();
	}
	
	public void setRandom(double _xMin, double _xMax, double _yMin, double _yMax, double _lifeValue)
	{
		
		for (int iX = (int)(iFieldsX*_xMin); iX < (int)(iFieldsX*_xMax); iX++){
			for (int iY = (int)(iFieldsY*_yMin); iY < (int)(iFieldsY*_yMax); iY++){
				fieldArea[iX][iY].setRandom(_lifeValue);
			}	
		}
		this.repaint();
	}
	

	
	public void nextGeneration()
	{
		
	// CLEAR NEIGHBORS
		for (int iY=0; iY < iFieldsY; iY++)
		{
			for (int iX=0; iX < iFieldsX; iX++)
			{
				fieldArea[iX][iY].setNeighbors(0);
			}
		}

	// SET NEIGHBORS
		for (int iY=0; iY < iFieldsY; iY++)
		{	
			for (int iX=0; iX < iFieldsX; iX++)
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
		for (int iY=0; iY < iFieldsY; iY++)
		{
			for (int iX=0; iX < iFieldsX; iX++)
			{
				fieldArea[iX][iY].killbirth(2, 3, 3, 3);	// classical 23/3 rule
			}
		}
	   
		
		this.repaint();
	}
	
	
	public void addNeighbors(int _x, int _y, int _add)
	{
		if (iRuleset == 0) // TORUS
		{
			int iX = (_x+iFieldsX)%iFieldsX;
			int iY = (_y+iFieldsY)%iFieldsY;
			
			fieldArea[iX][iY].addNeighbor(_add);
		}
		else if (iRuleset == 1) // SOLID
		{
			if ((0 <= _x && _x < iFieldsX) && (0 <= _y && _y < iFieldsY)){
				fieldArea[_x][_y].addNeighbor(_add);
			}
		}
		else
		{
			System.err.println("Falscher Regelsatz!");
		}
	}

	public void setRun(boolean _run){
		bRun = _run;
	}
	
	public boolean run(){
		return bRun;
	}
	
	public void setRuleset(int _rule){
		iRuleset = _rule;
	}
	
	public int ruleset(){
		return iRuleset;
	}
	
	public void setSpeed(int _speed){
		iSpeed = _speed;
	}
	
	public int speed(){
		return iSpeed;
	}
	
	public void clearHighlights()
	{
		for (int iY=0; iY < iFieldsY; iY++)
		{
			for (int iX=0; iX < iFieldsX; iX++)
			{
				fieldArea[iX][iY].setHighlight(false);
			}
		}
	}
	
	public void mouseClicked(MouseEvent event)
	{
		if (!bRun)
		{
			int fieldX = (event.getX()-1)/iFieldsize;
			int fieldY = (event.getY()-1)/iFieldsize;
			
			ArrayList<Point> brush = myBrushes.currentBrush();
			
			for (Point curPoint : brush)
			{
				int iX = fieldX+curPoint.x;
				int iY = fieldY+curPoint.y;
				
				if (iRuleset == 0){ // TORUS
				
					iX = (iX+iFieldsX)%iFieldsX;
					iY = (iY+iFieldsY)%iFieldsY;
					
					if (myBrushes.drawmode()) {
						fieldArea[iX][iY].grow();
					}
					else {
						fieldArea[iX][iY].kill();
					}
					
				}
				else if (iRuleset == 1){ // SOLID
					
					if ((0 <= iX && iX < iFieldsX) && (0 <= iY && iY < iFieldsY)){
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
		if (!bRun)
		{
			int fieldX = (event.getX()-1)/iFieldsize;
			int fieldY = (event.getY()-1)/iFieldsize;

			
			
			if (fieldX != iMouseX || fieldY != iMouseY)
			{
				clearHighlights();
				
				ArrayList<Point> brush = myBrushes.currentBrush();
				for (Point curPoint : brush)
				{					
					int iX = fieldX+curPoint.x;
					int iY = fieldY+curPoint.y;
					
					if (iRuleset == 0){ // TORUS
						iX = (iX+iFieldsX)%iFieldsX;
						iY = (iY+iFieldsY)%iFieldsY;
						
						fieldArea[iX][iY].setHighlight(true);
					}
					else if (iRuleset == 1){ // SOLID
						if ((0 <= iX && iX < iFieldsX) && (0 <= iY && iY < iFieldsY))
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
