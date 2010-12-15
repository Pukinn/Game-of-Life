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

// AREA VALUES
	public boolean bRun;
	public int iBrush;	// 0 = point, 1 = glider small
	
	private Field[][] fieldArea;
	private ArrayList<Point> glider1;
	
	
	public Area(int _fieldsx, int _fieldsy, int _fieldsize)
	{	
		iFieldsX = _fieldsx;
		iFieldsY = _fieldsy;
		iFieldsize = _fieldsize;
		
	// SET DEFAULTS
		iBrush = 0;
		bRun = false;
		
		fieldArea = new Field[iFieldsX][iFieldsY];
		for (int iY=0; iY < iFieldsY; iY++)
		{
			for (int iX=0; iX < iFieldsX; iX++)
			{
				fieldArea[iX][iY] = new Field(0);
			}
		}
		
	// GLIDER SMALL	
		glider1 = new ArrayList<Point>();
		Point p0 = new Point(1,0);
		Point p1 = new Point(2,1);
		Point p2 = new Point(2,2);
		Point p3 = new Point(1,2);
		Point p4 = new Point(0,2);
		
		glider1.add(p0);
		glider1.add(p1);
		glider1.add(p2);
		glider1.add(p3);
		glider1.add(p4);

		
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
	
	public void drawFigure(int _posx, int _posy)
	{
		
		for (int iCount = 0; iCount < glider1.size(); iCount++)
		{
			int iX = _posx+glider1.get(iCount).x;
			int iY = _posy+glider1.get(iCount).y;
			
			fieldArea[iX][iY].grow();
		}
		
		this.repaint();
	}
	
	public void nextGeneration(int _gameRule)
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
				
				addNeighbors(_gameRule, iX-1, iY-1, lifestate);	// left-top
				addNeighbors(_gameRule, iX, iY-1, lifestate);	// top
				addNeighbors(_gameRule, iX+1, iY-1, lifestate);	// right-top
				addNeighbors(_gameRule, iX-1, iY, lifestate);	// left
				addNeighbors(_gameRule, iX+1, iY, lifestate);	// right
				addNeighbors(_gameRule, iX-1, iY+1, lifestate);	// left-bottom
				addNeighbors(_gameRule, iX, iY+1, lifestate);	// bottom
				addNeighbors(_gameRule, iX+1, iY+1, lifestate);	// right-bottom
					
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
	
	
	public void addNeighbors(int _rule, int _x, int _y, int _add)
	{
		if (_rule == 0) // TORUS
		{
			int iX = (_x+iFieldsX)%iFieldsX;
			int iY = (_y+iFieldsY)%iFieldsY;
			
			fieldArea[iX][iY].addNeighbor(_add);
		}
		else if (_rule == 1) // SOLID
		{
			if ((0 < _x && _x < iFieldsX) && (0 < _y && _y < iFieldsY))
			{
				fieldArea[_x][_y].addNeighbor(_add);
			}
		}
		else
		{
			System.err.println("Falscher Regelsatz!");
		}
	}

	public ArrayList<Point> rotate(ArrayList<Point> _toRotate)
	{
		// TODO: rotate right
		
		ArrayList<Point> rotated = new ArrayList<Point>();
		/*
		for (Point curPoint : _toRotate)
		{
			Point newPoint;
			int iX = curPoint.
		}
		*/
		
		return rotated;
	}
	
	public void mouseClicked(MouseEvent event)
	{
		if (!bRun)
		{
			int fieldX = (event.getX()-1)/iFieldsize;
			int fieldY = (event.getY()-1)/iFieldsize;
			
			if (iBrush == 0) // BRUSH
			{
				fieldArea[fieldX][fieldY].invert();
			}
			else if (iBrush == 1) // GLIDER 1
			{
				drawFigure(fieldX,fieldY);
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
			
			if (true /*iBrush == 0*/)
			{
				if (fieldX != iMouseX || fieldY != iMouseY)
				{
					fieldArea[iMouseX][iMouseY].setHighlight(false);
					fieldArea[fieldX][fieldY].setHighlight(true);
					
					iMouseX = fieldX;
					iMouseY = fieldY;
					
					this.repaint();
				}
			}
			
			// TODO Highlight with brush = 1
			/*
			else if (iBrush == 1)
			{
				
				if (fieldX != iMouseX || fieldY != iMouseY)
				{
					
					for (int iCount = 0; iCount < glider1.size(); iCount++)
					{
						
						int iX = iMouseX+glider1.get(iCount)[0];
						int iY = iMouseY+glider1.get(iCount)[1];
						fieldArea[iX][iY].setHighlight(false);
						
						iX = fieldX+glider1.get(iCount)[0];
						iY = fieldY+glider1.get(iCount)[1];
						fieldArea[iX][iY].setHighlight(true);
					};
					
					iMouseX = fieldX;
					iMouseY = fieldY;
					
					this.repaint();
					
				}
				
			}
			*/
			
		}
	}

	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	public void mouseDragged(MouseEvent arg0) {}
	
}
