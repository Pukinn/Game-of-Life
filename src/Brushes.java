import java.awt.Point;
import java.util.ArrayList;


public class Brushes {

	public ArrayList<Point> brush;
	public ArrayList<Point> glider;
	public ArrayList<Point> LWSS;
	public ArrayList<Point> MWSS;
	public ArrayList<Point> HWSS;
	
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
		
	// GLIDER	
		glider = new ArrayList<Point>();
		glider.add(new Point(-1,-2));
		glider.add(new Point(0,-1));
		glider.add(new Point(0,0));
		glider.add(new Point(-1,0));
		glider.add(new Point(-2,0));
		
	// LWSS	
		LWSS = new ArrayList<Point>();
		LWSS.add(new Point(-3,-3));
		LWSS.add(new Point(-2,-3));
		LWSS.add(new Point(-1,-3));
		LWSS.add(new Point(0,-3));
		LWSS.add(new Point(-4,-2));
		LWSS.add(new Point(0,-2));
		LWSS.add(new Point(0,-1));
		LWSS.add(new Point(-1,0));
		LWSS.add(new Point(-4,0));
		
	// MWSS	
		MWSS = new ArrayList<Point>();
		MWSS.add(new Point(-5,-3));
		MWSS.add(new Point(-5,-1));
		MWSS.add(new Point(-4,-4));
		MWSS.add(new Point(-3,-4));
		MWSS.add(new Point(-3,0));
		MWSS.add(new Point(-2,-4));
		MWSS.add(new Point(-1,-1));
		MWSS.add(new Point(-1,-4));
		MWSS.add(new Point(0,-4));
		MWSS.add(new Point(0,-3));
		MWSS.add(new Point(0,-2));
		
		// HWSS	
		HWSS = new ArrayList<Point>();
		HWSS.add(new Point(-6,-3));
		HWSS.add(new Point(-6,-1));
		HWSS.add(new Point(-5,-4));
		HWSS.add(new Point(-4,-4));
		HWSS.add(new Point(-3,-4));
		HWSS.add(new Point(-2,-4));
		HWSS.add(new Point(-1,-4));
		HWSS.add(new Point(0,-4));
		HWSS.add(new Point(0,-3));
		HWSS.add(new Point(0,-2));
		HWSS.add(new Point(-1,-1));
		HWSS.add(new Point(-4,0));
		HWSS.add(new Point(-3,0));
		
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
		ArrayList<Point> loadFigure = currentBrush();
	
	/*
		
	// LOAD FIGURE
		if (iSelBrush == 1){
			loadFigure = glider;
		}
		else if (iSelBrush == 2){
			loadFigure = LWSS;
		}
		else if (iSelBrush == 3){
			loadFigure = MWSS;
		}
		else if (iSelBrush == 4){
			loadFigure = HWSS;
		}
		else {
			System.err.println("kein gültiger Pinsel!");
			loadFigure = brush;
		}
		
		*/
		
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
			glider.clear();
			glider = packBrush(newFigure);
		}
		else if (iSelBrush == 2){
			LWSS.clear();
			LWSS = packBrush(newFigure);
		}
		else if (iSelBrush == 3){
			MWSS.clear();
			MWSS = packBrush(newFigure);
		}
		else if (iSelBrush == 4){
			HWSS.clear();
			HWSS = packBrush(newFigure);
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
			retBrush = glider;
		}
		else if (iSelBrush == 2) {
			retBrush.clear();
			retBrush = LWSS;
		}
		else if (iSelBrush == 3) {
			retBrush.clear();
			retBrush = MWSS;
		}
		else if (iSelBrush == 4) {
			retBrush.clear();
			retBrush = MWSS;
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
