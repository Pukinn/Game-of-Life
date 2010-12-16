

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
			try { Thread.sleep(myGUI.myArea.speed());}
			catch (InterruptedException e) {}
			
			if (myGUI.myArea.run())
			{
				myGUI.myArea.nextGeneration(myGUI.myArea.ruleset());	
			}
		}
		
	}
}