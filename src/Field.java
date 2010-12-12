import java.util.Random;


public class Field
{

	private int iLifestate;
	private int iNeighbors;
	
/*	public int iX1;
	public int iX2;
	public int iY1;
	public int iY2;
*/

// CUNSTRCTOR	
	public Field(int _state)
	{
		iLifestate = _state;
		iNeighbors = 0;
	}

// CHANGE LIFESTATE
	public void setState(int _state)
	{
		iLifestate = _state;
	}
	
	public void grow()
	{
		iLifestate = 1;
	}
	
	public void kill()
	{
		iLifestate = 0;
	}
	
	// RETURN
	public int lifestate()
	{
		return iLifestate;
	}
	
// NEIGHBORS
	public void addNeighbor(int _add)
	{
		iNeighbors += _add;	
	}

	public void setNeighbors(int _set)
	{
		iNeighbors = _set;
	}

	// RETURN
	public int neighbors()
	{
		return iNeighbors;
	}

// SET FIELDS BEFORE START
	public void setRandom(double _lifeValue)
	{
		Random r = new Random();
		if (r.nextFloat() < _lifeValue)
		{
			iLifestate = 1;
		}
		else
		{
			iLifestate = 0;
		}
	}
	
// GAME RULES
	public void killbirth(int _lifefrom, int _lifeto, int _birthfrom, int _birthto)
	{
		if (_birthfrom <= iNeighbors && iNeighbors <= _birthto)
		{
			iLifestate = 1;
		}
		else if (iNeighbors < _lifefrom || _lifeto < iNeighbors)
		{
			iLifestate = 0;
		}
	}

// SET FIELD POSITION
/*	public void setPosition(int _x1, int _x2, int _y1, int _y2)
	{
		iX1 = _x1;
		iX2 = _x2;
		iY1 = _y1;
		iY2 = _y2;
	}

	
// RETURN MOUSE HITTEN	
	public boolean hit(int _x, int _y)
	{
		boolean bHitten = false;
		
		if (iX1 < _x && _x < iX2 &&
			iY1 < _y && _y < iY2)
		{
			bHitten = true;
		}
		
		return bHitten;
	}
*/	
	
	
}
