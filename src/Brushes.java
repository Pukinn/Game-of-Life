import java.awt.Point;
import java.util.ArrayList;


public class Brushes {

	public ArrayList<Point> brush;
	public ArrayList<Point> glider1;
	
	private int iSelBrush;
	private boolean bDrawmode;
	
	public Brushes()
	{
// SET DEFAULTS
	// BRUSH
		iSelBrush = 0;
		bDrawmode = true; // true = draw, false = erase
	
	// BRUSH 1x1
		brush = new ArrayList<Point>();
		brush.add(new Point(0,0));
		
	// GLIDER SMALL	
		glider1 = new ArrayList<Point>();
		glider1.add(new Point(-1,-2));
		glider1.add(new Point(0,-1));
		glider1.add(new Point(0,0));
		glider1.add(new Point(-1,0));
		glider1.add(new Point(-2,0));
	}
	
	public void setDrawmode(boolean _mode) {
		bDrawmode = _mode;
	}
	
	public void setBrush(int _brush) {
		iSelBrush = _brush;
	}
	
	public void setBrushSize(int _size) {
		
		brush.clear();
		
		for (int iY = 0; _size < iY; iY++){
			for (int iX = 0; _size < iX; iX++){
				brush.add(new Point(iX,iY));
			}
		}
	}
	
	public void rotate() {
		
		ArrayList<Point> newFigure = new ArrayList<Point>();
		ArrayList<Point> loadFigure;
	
	// LOAD FIGURE
		if (iSelBrush == 1){
			loadFigure = glider1;
		}
		else {
			System.err.println("kein gültiger Pinsel!");
			loadFigure = brush;
		}
		
	// GET MAX LENGTH
		int iMaxX = 0;
		
		for (Point curPoint : loadFigure){
			if (iMaxX < curPoint.x){
				iMaxX = curPoint.x;
			}
		}
		
		
	// REWRITE
		for (Point curPoint : loadFigure){
			
			int iX = iMaxX - curPoint.y;
			int iY = curPoint.x;
			newFigure.add(new Point(iX,iY));
		}
		
		
		if (iSelBrush == 1){
			glider1.clear();
			glider1 = packBrush(newFigure);
		}
	}
	
	public ArrayList<Point> packBrush(ArrayList<Point> _brush){
		ArrayList<Point> newBrush = new ArrayList<Point>();
		
		int iMaxX = 0;
		int iMaxY = 0;
		
		for (Point curPoint : _brush){
			if (iMaxX < curPoint.x){
				iMaxX = curPoint.x;
			}
			if (iMaxY < curPoint.y){
				iMaxY = curPoint.y;
			}
		}
		
		for (Point curPoint : _brush){
			int iX = curPoint.x-iMaxX;
			int iY = curPoint.y-iMaxY;
			newBrush.add(new Point(iX,iY));
		}
		
		
		return newBrush;
	}
	
	public ArrayList<Point> currentBrush() {
		ArrayList<Point> retBrush = new ArrayList<Point>();
		retBrush.add(new Point(0,0));
		
		if (iSelBrush == 0) {
			retBrush.clear();
			retBrush = brush;
		}
		else if (iSelBrush == 1) {
			retBrush.clear();
			retBrush = glider1;
		}
		else {
			System.err.println("Kein gültiger Pinsel!");
		}
		
		return retBrush;
	}
	
	public boolean drawmode() {
		return bDrawmode;
	}
	
}
