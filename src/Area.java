import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;



public class Area extends JPanel
{
	private static final long serialVersionUID = -3943138899203200607L;

	public Field[][] fieldArea;
	
	public int iBorderX;
	public int iBorderY;
	
	public int iFieldsX;
	public int iFieldsY;
	public int iFieldsize;
	

	
	public Area(int _posx, int _posy, int _fieldsx, int _fieldsy, int _fieldsize)
	{	
		iBorderX = _posx;
		iBorderY = _posy;
		
		iFieldsX = _fieldsx;
		iFieldsY = _fieldsy;
		iFieldsize = _fieldsize;
		
		fieldArea = new Field[iFieldsX][iFieldsY];
		
		for (int iY=0; iY < iFieldsY; iY++)
		{
			for (int iX=0; iX < iFieldsX; iX++)
			{
				fieldArea[iX][iY] = new Field(0);
			}
		}
	}
	
	

	public void paint(Graphics g)
	{	
		
		for (int iY = 0; iY < iFieldsY; iY++)
		{
			for (int iX = 0; iX < iFieldsX; iX++)
			{
				int iLifestate = fieldArea[iX][iY].lifestate();
				if (iLifestate == 0)
				{
					g.setColor(Color.lightGray);
				}
				else if (iLifestate == 1)
				{
					g.setColor(Color.red);
				}
				else
				{
					System.out.println("Fehler! Feld hat keinen definierten Wert");
				}
				
				
				int iPosX = (iX-1)*iFieldsize+iBorderX;
				int iPosY = (iY-1)*iFieldsize+iBorderY;
				
				g.fillRect(iPosX, iPosY, iFieldsize, iFieldsize);
				
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
		
	//	System.out.println("set random");
		
		for (int iX = (int)(iFieldsX*_xMin); iX < (int)(iFieldsX*_xMax); iX++)
		{
			for (int iY = (int)(iFieldsY*_yMin); iY < (int)(iFieldsY*_yMax); iY++)
			{
				
				fieldArea[iX][iY].setRandom(_lifeValue);
				
			}	
		}
		
		this.repaint();
	}
	
	public void drawGlider()
	{
	
		
	//	fieldArea[5][4].grow();
	//	fieldArea[5][5].grow();
	//	fieldArea[5][6].grow();
		
		fieldArea[3][2].grow();
		fieldArea[4][3].grow();
		fieldArea[2][4].grow();
		fieldArea[3][4].grow();
		fieldArea[4][4].grow();
		
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
			
	
		for (int iY=0; iY < iFieldsY; iY++)
		{
			for (int iX=0; iX < iFieldsX; iX++)
			{
				fieldArea[iX][iY].killbirth(2, 3, 3, 3);
			}
		}
	   
		
		this.repaint();
	}
	
	public void addNeighbors(int _rule, int _x, int _y, int _add)
	{
		if (_rule == 0) // torus
		{
			int iX = (_x+iFieldsX)%iFieldsX;
			int iY = (_y+iFieldsY)%iFieldsY;
			
			fieldArea[iX][iY].addNeighbor(_add);
		}
		else if (_rule == 1) // solid
		{
			if ((0 < _x && _x < iFieldsX) && (0 < _y && _y < iFieldsY))
			{
				fieldArea[_x][_y].addNeighbor(_add);
			}
		}
		else
		{
			System.err.println("Fehler: Falscher Regelsatz!");
		}
	}
	
}
