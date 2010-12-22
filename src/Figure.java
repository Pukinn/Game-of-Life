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


public class Figure {

	private String name;
	private boolean bRelative;		// brush is relative to mouse
	
	private ArrayList<Point> figure;
	
	public Figure(int _abs){
		
	// SET RELATIVE / ABSOLUTE
		if (_abs == 0){
			bRelative = true;
		}
		else if (_abs == 1){
			bRelative = false;
		}
		else {
			System.err.println("Ungültiger Wert für Figur(Absolut/Relativ)");
		}
	
	// CREATE LIST
		figure = new ArrayList<Point>();
	}
	
	public boolean isRelative(){
		return bRelative;
	}
	
	public void setName(String _name){
		name = _name;
	}
	
	public String name(){
		return name;
	}
	
	public void addField(int _x, int _y){
		if (bRelative){
			figure.add(new Point(_x,_y));
		}
		else {
			if (0 < _x || 0 < _y){
				figure.add(new Point(_x,_y));
			}
			else {
				System.err.println("Ungültiger Absolutwert!");
			}
		}
	}
	
	public void clear(){
		figure.clear();
	}
	
	public void pack(){
		
		ArrayList<Point> newFigure = new ArrayList<Point>();
		
		int iMaxX = 0;
		int iMaxY = 0;
		
		for (Point curPoint : figure){
			if (iMaxX < curPoint.x){
				iMaxX = curPoint.x;
			}
			if (iMaxY < curPoint.y){
				iMaxY = curPoint.y;
			}
		}
		
		for (Point curPoint : figure){
			int iX = curPoint.x-iMaxX;
			int iY = curPoint.y-iMaxY;
			newFigure.add(new Point(iX,iY));
		}
		
		figure.clear();
		figure = newFigure;
	}
	
	public void rotate() {
		
		ArrayList<Point> newFigure = new ArrayList<Point>();
	//	ArrayList<Point> loadFigure = figure;
		
	// GET MAX LENGTH
		int iMaxX = 0;
		
		for (Point curPoint : figure){
			if (iMaxX < curPoint.x){
				iMaxX = curPoint.x;
			}
		}
		
	// REWRITE
		for (Point curPoint : figure){
			
			int iX = iMaxX - curPoint.y;
			int iY = curPoint.x;
			newFigure.add(new Point(iX,iY));
		}
		
	figure.clear();
	figure = newFigure;
	pack();
	}
	
	public ArrayList<Point> points(){
		return figure;
	}
	
}
