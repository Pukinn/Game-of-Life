

public class gameOfLive
{

	public static GUI myGUI;

	
	public static void main(String args[])
	{
		
		myGUI = new GUI();
		myGUI.createGUI();		
		myGUI.show();
		
	// MAIN LOOP
		while (true)
		{
			try { Thread.sleep(myGUI.speed());}
			catch (InterruptedException e) {}
			
			if (myGUI.run())
			{
				myGUI.myArea.nextGeneration(myGUI.ruleset());	
			}
		}
		
	}
}