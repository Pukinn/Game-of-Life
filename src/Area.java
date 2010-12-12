import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;



public class Area extends JPanel
{
	private static final long serialVersionUID = 1L;

	public Field[][] fieldArea;
	
	public int iBorderX;
	public int iBorderY;
	
	public int iFieldsX;
	public int iFieldsY;
	public int iFieldsize;
	

	
	public Area(int _posx, int _posy, int _fieldsx, int _fieldsy, int _fieldsize)
	{
		this.setLayout(null);
		
		iBorderX = _posx;
		iBorderY = _posy;
		
		iFieldsX = _fieldsx+2;
		iFieldsY = _fieldsy+2;
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
		
		
		for (int iY = 1; iY < iFieldsY-1; iY++)
		{
			for (int iX = 1; iX < iFieldsX-1; iX++)
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
		
		for (int iX = (int)((iFieldsX+1)*_xMin); iX < (int)((iFieldsX+1)*_xMax); iX++)
		{
			for (int iY = (int)((iFieldsY+1)*_yMin); iY < (int)((iFieldsY+1)*_yMax); iY++)
			{
				
				fieldArea[iX][iY].setRandom(_lifeValue);
				
			}	
		}
		
		this.repaint();
	}
	
	public void drawGlider()
	{
	
		
		fieldArea[5][4].grow();
		fieldArea[5][5].grow();
		fieldArea[5][6].grow();
		
	//	fieldArea[3][2].grow();
	//	fieldArea[4][3].grow();
	//	fieldArea[2][4].grow();
	//	fieldArea[3][4].grow();
	//	fieldArea[4][4].grow();
		
		this.repaint();
	}
	
	public void nextGeneration(int _gameRule)
	{

		/*
		game Rule 0 = border-neighborship
		game Rule 1 = solid-border
		*/

	// SET BORDER / GAMERULES
		if (_gameRule == 1)
		{
			// TOP & BOTTOM
			for (int iX=0; iX < iFieldsX; iX++)
			{
				fieldArea[iX][0].kill();
				fieldArea[iX][iFieldsY-1].kill();
			}
			// LEFT & RIGHT
			for (int iY=1; iY < iFieldsY-1; iY++)
			{
				fieldArea[0][iY].kill();
				fieldArea[iFieldsX-1][iY].kill();
			}
		}
		else if (_gameRule == 0)
		{
			// TOP & BOTTOM
			for (int iX=1; iX < iFieldsX-1; iX++)
			{
				fieldArea[iX][0].setState(fieldArea[iX][iFieldsY-2].lifestate());
				fieldArea[iX][iFieldsY-1].setState(fieldArea[iX][1].lifestate());
			}
			// LEFT & RIGHT
			for (int iY=1; iY < iFieldsY-1; iY++)
			{
				fieldArea[0][iY].setState(fieldArea[iFieldsX-2][iY].lifestate());
				fieldArea[iFieldsX-1][iY].setState(fieldArea[1][iY].lifestate());
			}

			fieldArea[0][0].setState(fieldArea[iFieldsX-2][iFieldsY-2].lifestate());
			fieldArea[iFieldsX-1][0].setState(fieldArea[1][iFieldsY-2].lifestate());
			fieldArea[iFieldsX-1][iFieldsY-1].setState(fieldArea[1][1].lifestate());
			fieldArea[0][iFieldsY-1].setState(fieldArea[iFieldsX-2][1].lifestate());
		}	
		
	// CLEAR NEIGHBORS
		for (int iY=0; iY < iFieldsY; iY++)
		{
			for (int iX=0; iX < iFieldsX; iX++)
			{
				fieldArea[iX][iY].setNeighbors(0);
			}
		}

	// SET NEIGHBORS
		for (int iY=1; iY < iFieldsY-1; iY++)
		{	
			for (int iX=1; iX < iFieldsX-1; iX++)
			{

				if (fieldArea[iX][iY].lifestate() == 1)
				{	
		        	fieldArea[iX-1][iY-1].addNeighbor(1);	//left-top
		        	fieldArea[iX][iY-1].addNeighbor(1);		//center-top
		        	fieldArea[iX+1][iY-1].addNeighbor(1);	//right-top
		        	fieldArea[iX-1][iY].addNeighbor(1);		//left
		        	fieldArea[iX+1][iY].addNeighbor(1);		//right
		        	fieldArea[iX-1][iY+1].addNeighbor(1);	//left-bottom
		        	fieldArea[iX][iY+1].addNeighbor(1);		//center-bottom
		        	fieldArea[iX+1][iY+1].addNeighbor(1);	//right-bottom
				}	
			}
		}
				
	
		for (int iY=1; iY < iFieldsY-1; iY++)
		{
			for (int iX=1; iX < iFieldsX-1; iX++)
			{
				fieldArea[iX][iY].killbirth(2, 3, 3, 3);
			}
		}
	   
		
		this.repaint();
	}
	
	
}
