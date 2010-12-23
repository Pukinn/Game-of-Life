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


public class gameOfLive
{

	public static GUI myGUI;
	public static DialogRuleset myDiaRuleset;
	
	public static void main(String args[])
	{
		
		myDiaRuleset = new DialogRuleset();
		myDiaRuleset.create();
		
		if (!myDiaRuleset.visible()){
			
			myGUI = new GUI();
			myGUI.createGUI();		
			myGUI.show();
			
			
		// MAIN LOOP
			int iCounter = 0;
			while (true)
			{
				try { Thread.sleep(myGUI.myArea.speed());}
				catch (InterruptedException e) {}
				
				
				if (myGUI.myArea.run())
				{
					myGUI.myArea.nextGeneration();	
					iCounter++;
					myGUI.drawGenerations(iCounter);
				}
				
			}
			
		}
	}
}