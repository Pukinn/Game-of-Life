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
		
	public static Ruleset ruleset;
	public static GUI myGUI;
	public static DialogRuleset myDiaRuleset;
	
	public static void main(String args[])
	{
		ruleset = new Ruleset();
		myGUI = new GUI(ruleset);
		
		myDiaRuleset = new DialogRuleset(ruleset, myGUI);
		myDiaRuleset.createDialog();
		myDiaRuleset.addAreaDims();
		myDiaRuleset.addRules();
		myDiaRuleset.showDialog();
		
		myGUI.mainLoop();
		
	// MAIN LOOP
/*		int iCounter = 0;
		while (true)
		{
			try { Thread.sleep(ruleset.iSpeed);}
			catch (InterruptedException e) {}
			
			
			if (ruleset.bRun)
			{
				myGUI.myArea.nextGeneration();	
				iCounter++;
				myGUI.drawGenerations(iCounter);
			}
			
		}
		*/
	}
	
}