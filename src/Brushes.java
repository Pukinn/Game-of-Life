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

import java.awt.Point;
import java.util.ArrayList;



public class Brushes {
	
	public ArrayList<Figure> brushes;
	
	public int iSelBrush;
	private boolean bDrawmode;
	
	public Brushes()
	{
// SET DEFAULTS
	// BRUSH
		iSelBrush = 0;
		bDrawmode = true; // true = draw, false = erase

		brushes = new ArrayList<Figure>();
		
	// BRUSH 1x1	
		brushes.add(new Figure(0));
		Figure fig = brushes.get(0);
		fig.setName("Pinsel");
		fig.addField(0, 0);
	
	// GLIDER
		brushes.add(new Figure(0));
		fig = brushes.get(1);
		fig.setName("Gleiter");
		fig.addField(-1,-2);
		fig.addField(0,-1);
		fig.addField(0,0);
		fig.addField(-1,0);
		fig.addField(-2,0);
		
	// LWSS
		brushes.add(new Figure(0));
		fig = brushes.get(2);
		fig.setName("LWSS");
		fig.addField(-3,-3);
		fig.addField(-2,-3);
		fig.addField(-1,-3);
		fig.addField(0,-3);
		fig.addField(-4,-2);
		fig.addField(0,-2);
		fig.addField(0,-1);
		fig.addField(-1,0);
		fig.addField(-4,0);
	
	// MWSS
		brushes.add(new Figure(0));
		fig = brushes.get(3);
		fig.setName("MWSS");
		fig.addField(-5,-3);
		fig.addField(-5,-1);
		fig.addField(-4,-4);
		fig.addField(-3,-4);
		fig.addField(-3,0);
		fig.addField(-2,-4);
		fig.addField(-1,-1);
		fig.addField(-1,-4);
		fig.addField(0,-4);
		fig.addField(0,-3);
		fig.addField(0,-2);
		
	// HWSS
		brushes.add(new Figure(0));
		fig = brushes.get(4);
		fig.setName("HWSS");
		fig.addField(-6,-3);
		fig.addField(-6,-1);
		fig.addField(-5,-4);
		fig.addField(-4,-4);
		fig.addField(-3,-4);
		fig.addField(-2,-4);
		fig.addField(-1,-4);
		fig.addField(0,-4);
		fig.addField(0,-3);
		fig.addField(0,-2);
		fig.addField(-1,-1);
		fig.addField(-4,0);
		fig.addField(-3,0);
		
	}
	
	
	
	public void setDrawmode(boolean _mode) {
		bDrawmode = _mode;
	}
	
	public void setBrush(int _brush) {
		iSelBrush = _brush;
	}
	
	public void setBrushSize(int _x, int _y) {
		
		brushes.get(0).clear();
		
		for (int iY = 0; iY < _y; iY++){
			for (int iX = 0; iX < _x; iX++){
				brushes.get(0).addField(iX, iY);
			}
		}
		
		brushes.get(0).pack();
	}

	
	
	public ArrayList<Point> currentBrush() {
		return brushes.get(iSelBrush).points();
	}
	
	public boolean drawmode() {
		return bDrawmode;
	}
	
}
