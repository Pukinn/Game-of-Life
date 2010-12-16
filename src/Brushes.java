import java.awt.Point;
import java.util.ArrayList;


public class Brushes {

	public ArrayList<Point> brush;
	public ArrayList<Point> glider1;
	
	public Brushes()
	{
// SET DEFAULTS
	
	// BRUSH 1x1
		brush = new ArrayList<Point>();
		brush.add(new Point(0,0));
		
	// GLIDER SMALL	
		glider1 = new ArrayList<Point>();
		glider1.add(new Point(1,0));
		glider1.add(new Point(2,1));
		glider1.add(new Point(2,2));
		glider1.add(new Point(1,2));
		glider1.add(new Point(0,2));
	}
	
	public void setBrushSize(int _size)
	{
		brush.clear();
		
		for (int iY = 0; _size < iY; iY++){
			for (int iX = 0; _size < iX; iX++){
				brush.add(new Point(iX,iY));
			}
		}
	}
	
	public void rotate()
	{
		// TODO rotate
	}
	
}
