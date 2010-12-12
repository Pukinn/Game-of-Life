

public class gameOfLive
{

	public static GUI myGUI;

	
	public static void main(String args[])
	{
		
		myGUI = new GUI();
		
		
	// CREATE FIELD
		myGUI.createFrame();		
		myGUI.show();
		
		/*
		if (iSetrandom == 0)
		{
			myGUI.myArea.setRandom(0.45, 0.55, 0.45, 0.55, 0.3);
		}
		else if (iSetrandom == 1)
		{
			myGUI.myArea.drawGlider(5,5);
			myGUI.myArea.drawGlider(15,5);
			myGUI.myArea.drawGlider(25,5);
			myGUI.myArea.drawGlider(35,5);
			myGUI.myArea.drawGlider(45,5);
		}
		else
		{
			System.out.println("Fehler: Keine gültige Spielfläche!");
		}
		*/
		
		
		
		
		
		
/*
		try { Thread.sleep(10000); }
		catch (InterruptedException e) {}
*/		
		
		while (true)
		{
			try { Thread.sleep(30);}
			catch (InterruptedException e) {}
			
			if (myGUI.run())
			{
				myGUI.myArea.nextGeneration(myGUI.ruleset());	
			}
		}
	
		
		
		
	}
	


}