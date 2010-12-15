import java.util.Random;


public class Field
{

	private int iLifestate;
	private int iNeighbors;
	private boolean bHighlight;
	
	
	public Field(int _state)
	{
		iLifestate = _state;
		iNeighbors = 0;
		bHighlight = false;
	}

// CHANGE LIFESTATE
	public void setState(int _state){
		iLifestate = _state;
	}
	
	public void grow(){
		iLifestate = 1;
	}
	
	public void kill(){
		iLifestate = 0;
	}
	
	public void invert(){
		if (iLifestate == 0)
		{
			iLifestate = 1;
		}
		else
		{
			iLifestate = 0;
		}
	}
	
	// RETURN
	public int lifestate(){
		return iLifestate;
	}
	
// HEIGHLIGHT
	public void setHighlight(boolean _b){
		bHighlight = _b;
	}
	
	// RETURN
	public boolean highlighted(){
		return bHighlight;
	}
	
// NEIGHBORS
	public void addNeighbor(int _add){
		iNeighbors += _add;	
	}

	public void setNeighbors(int _set){
		iNeighbors = _set;
	}

	// RETURN
	public int neighbors(){
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
	
	
}
